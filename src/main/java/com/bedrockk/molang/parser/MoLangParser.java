package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.parselet.*;
import com.bedrockk.molang.parser.tokenizer.Token;
import com.bedrockk.molang.parser.tokenizer.TokenIterator;
import com.bedrockk.molang.parser.tokenizer.TokenType;
import lombok.extern.log4j.Log4j2;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public final class MoLangParser {

    private final static Map<TokenType, PrefixParselet> prefixParselets = new HashMap<>();
    private final static Map<TokenType, InfixParselet> infixParselets = new HashMap<>();

    private final TokenIterator tokenIterator;
    private final List<Token> readTokens = new ArrayList<>();
    private Token lastConsumed;

    static {
        prefixParselets.put(TokenType.NAME, new NameParselet());
        prefixParselets.put(TokenType.STRING, new StringParselet());
        prefixParselets.put(TokenType.NUMBER, new NumberParselet());
        prefixParselets.put(TokenType.TRUE, new BooleanParselet(Precedence.PREFIX));
        prefixParselets.put(TokenType.FALSE, new BooleanParselet(Precedence.PREFIX));
        prefixParselets.put(TokenType.RETURN, new ReturnParselet());
        prefixParselets.put(TokenType.CONTINUE, new ContinueParselet());
        prefixParselets.put(TokenType.BREAK, new BreakParselet());
        prefixParselets.put(TokenType.LOOP, new LoopParselet());
        prefixParselets.put(TokenType.FOR_EACH, new ForEachParselet());
        prefixParselets.put(TokenType.THIS, new ThisParselet());
        prefixParselets.put(TokenType.BRACKET_LEFT, new GroupParselet());
        prefixParselets.put(TokenType.CURLY_BRACKET_LEFT, new BracketScopeParselet(Precedence.SCOPE));
        prefixParselets.put(TokenType.MINUS, new UnaryMinusParselet(Precedence.PREFIX));
        prefixParselets.put(TokenType.PLUS, new UnaryPlusParselet(Precedence.PREFIX));
        prefixParselets.put(TokenType.BANG, new BooleanNotParselet(Precedence.PREFIX));

        infixParselets.put(TokenType.QUESTION, new TernaryParselet(Precedence.CONDITIONAL));
        infixParselets.put(TokenType.ARRAY_LEFT, new ArrayAccessParselet(Precedence.ARRAY_ACCESS));
        infixParselets.put(TokenType.PLUS, new GenericBinaryOpParselet(Precedence.SUM));
        infixParselets.put(TokenType.MINUS, new GenericBinaryOpParselet(Precedence.SUM));
        infixParselets.put(TokenType.SLASH, new GenericBinaryOpParselet(Precedence.PRODUCT));
        infixParselets.put(TokenType.ASTERISK, new GenericBinaryOpParselet(Precedence.PRODUCT));
        infixParselets.put(TokenType.EQUALS, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.NOT_EQUALS, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.GREATER, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.GREATER_OR_EQUALS, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.SMALLER, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.SMALLER_OR_EQUALS, new GenericBinaryOpParselet(Precedence.COMPARE));
        infixParselets.put(TokenType.AND, new GenericBinaryOpParselet(Precedence.AND));
        infixParselets.put(TokenType.OR, new GenericBinaryOpParselet(Precedence.OR));
        infixParselets.put(TokenType.COALESCE, new GenericBinaryOpParselet(Precedence.COALESCE));
        infixParselets.put(TokenType.ARROW, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.ASSIGN, new AssignParselet(Precedence.ASSIGNMENT));
    }

    public MoLangParser(TokenIterator iterator) {
        this.tokenIterator = iterator;
    }

    public List<Expression> parse() {
        List<Expression> exprs = new ArrayList<>();

        do {
            Expression expr = parseExpression();
            if (expr != null) {
                exprs.add(expr);
            } else {
                break;
            }
        } while (matchToken(TokenType.SEMICOLON));

        return exprs;
    }

    public Expression parseExpression() {
        return parseExpression(Precedence.ANYTHING);
    }

    public Expression parseExpression(Precedence precedence) {
        Token token = consumeToken();

        if (token.getType().equals(TokenType.EOF)) {
            return null;
        }

        PrefixParselet parselet = prefixParselets.get(token.getType());

        if (parselet == null) {
            throw new RuntimeException("Cannot parse " + token.getType().name() + " expression");
        }

        return parseInfixExpression(parselet.parse(this, token), precedence);
    }

    private Expression parseInfixExpression(Expression left, Precedence precedence) {
        Token token;

        while (precedence.ordinal() < getPrecedence().ordinal()) {
            token = consumeToken();
            left = infixParselets.get(token.getType()).parse(this, token, left);
        }

        return left;
    }

    private Precedence getPrecedence() {
        Token token = readToken();

        if (token != null) {
            InfixParselet parselet = infixParselets.get(token.getType());

            if (parselet != null) {
                return parselet.getPrecedence();
            }
        }

        return Precedence.ANYTHING;
    }

    public List<Expression> parseArgs() {
        List<Expression> args = new ArrayList<>();

        if (matchToken(TokenType.BRACKET_LEFT)) {
            if (!matchToken(TokenType.BRACKET_RIGHT)) { // check for empty groups
                do {
                    args.add(parseExpression());
                } while (matchToken(TokenType.COMMA));

                consumeToken(TokenType.BRACKET_RIGHT);
            }
        }

        return args;
    }

    public String fixNameShortcut(String name) {
        String[] splits = name.split("\\.");

        switch (splits[0]) {
            case "q":
                splits[0] = "query";
                break;
            case "v":
                splits[0] = "variable";
                break;
            case "t":
                splits[0] = "temp";
                break;
            case "c":
                splits[0] = "context";
                break;
        }

        return String.join(".", splits);
    }

    public String getNameHead(String name) {
        return name.split("\\.")[0];
    }

    public boolean checkName(String name) {
        String head = getNameHead(name);

        return head.equals("query") || head.equals("variable") || head.equals("temp") || head.equals("context");
    }

    public Token consumeToken() {
        return consumeToken(null);
    }

    public Token consumeToken(TokenType expectedType) {
        tokenIterator.step();
        Token token = readToken();

        if (expectedType != null) {
            if (!token.getType().equals(expectedType)) {
                throw new RuntimeException("Expected token " + expectedType.name() + " and " + token.getType().name() + " given");
            }
        }

        lastConsumed = readTokens.remove(0);

        return lastConsumed;
    }

    public boolean matchToken(TokenType expectedType) {
        return matchToken(expectedType, true);
    }

    public boolean matchToken(TokenType expectedType, boolean consume) {
        Token token = readToken();

        if (token == null || !token.getType().equals(expectedType)) {
            return false;
        } else {
            if (consume) {
                consumeToken();
            }

            return true;
        }
    }

    private Token readToken() {
        return readToken(0);
    }

    private Token readToken(int distance) {
        while (distance >= readTokens.size()) {
            readTokens.add(tokenIterator.next());
        }

        return readTokens.get(distance);
    }
}
