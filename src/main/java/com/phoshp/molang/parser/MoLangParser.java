package com.phoshp.molang.parser;

import com.phoshp.molang.parser.expression.*;
import com.phoshp.molang.parser.parselet.*;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenIterator;
import com.phoshp.molang.parser.tokenizer.TokenType;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public final class MoLangParser {

    private final static Map<TokenType, Parselet> prefixParselets = new HashMap<>();
    private final static Map<TokenType, InfixParselet> infixParselets = new HashMap<>();

    private final TokenIterator tokenIterator;
    private final List<Token> readTokens = new ArrayList<>();
    private Token lastConsumed;
    private Expression lastExpression;

    static {
        prefixParselets.put(TokenType.NAME, new NameParselet());
        prefixParselets.put(TokenType.STRING, new StringParselet());
        prefixParselets.put(TokenType.NUMBER, new NumberParselet());
        prefixParselets.put(TokenType.TRUE, new BooleanParselet());
        prefixParselets.put(TokenType.FALSE, new BooleanParselet());
        prefixParselets.put(TokenType.RETURN, new ReturnParselet());
        prefixParselets.put(TokenType.CONTINUE, new ContinueParselet());
        prefixParselets.put(TokenType.BREAK, new BreakParselet());
        prefixParselets.put(TokenType.LOOP, new LoopParselet());
        prefixParselets.put(TokenType.FOR_EACH, new ForEachParselet());
        prefixParselets.put(TokenType.THIS, new ThisParselet());
        prefixParselets.put(TokenType.BRACKET_LEFT, new GroupParselet());
        prefixParselets.put(TokenType.CURLY_BRACKET_LEFT, new BracketScopeParselet());
        prefixParselets.put(TokenType.MINUS, new UnaryMinusParselet());
        prefixParselets.put(TokenType.PLUS, new UnaryPlusParselet());
        prefixParselets.put(TokenType.BANG, new BooleanNotParselet());

        infixParselets.put(TokenType.QUESTION, new TernaryParselet());
        infixParselets.put(TokenType.ARRAY_LEFT, new ArrayAccessParselet());
        infixParselets.put(TokenType.PLUS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.MINUS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.SLASH, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.ASTERISK, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.EQUALS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.NOT_EQUALS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.GREATER, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.GREATER_OR_EQUALS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.SMALLER, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.SMALLER_OR_EQUALS, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.AND, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.OR, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.COALESCE, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.ARROW, new GenericBinaryOpParselet());
        infixParselets.put(TokenType.ASSIGN, new AssignParselet());
    }

    public MoLangParser(TokenIterator iterator) {
        this.tokenIterator = iterator;
    }

    public List<Expression> parse() {
        List<Expression> exprs = new ArrayList<>();

        do {
            exprs.add(parseExpression());
        } while (matchToken(TokenType.SEMICOLON));

        return exprs;
    }

    public Expression parseExpression() {
        Token token = consumeToken();

        if (token.getType().equals(TokenType.EOF)) {
            return null;
        }

        Parselet parselet = prefixParselets.get(token.getType());

        if (parselet == null) {
            throw new RuntimeException("Cannot parse " + token.getType().name() + " expression " + token.getText());
        }

        return parseInfixExpression(parselet.parse(this, token));
    }

    private Expression parseInfixExpression(Expression expression) {
        Token nextToken = readToken();

        if (infixParselets.containsKey(nextToken.getType())) {
            Token token = consumeToken();

            return infixParselets.get(nextToken.getType()).parse(this, token, expression);
        }

        return expression;
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
