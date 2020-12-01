package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.StatementExpression;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class BracketScopeParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        List<Expression> exprs = new ArrayList<>();

        if (!parser.matchToken(TokenType.CURLY_BRACKET_RIGHT)) {
            do {
                if (parser.matchToken(TokenType.CURLY_BRACKET_RIGHT, false)) {
                    break;
                }

                exprs.add(parser.parseExpression());
            } while (parser.matchToken(TokenType.SEMICOLON));

            parser.consumeToken(TokenType.CURLY_BRACKET_RIGHT);
        }

        return new StatementExpression(exprs.toArray(new Expression[0]));
    }
}
