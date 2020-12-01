package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenType;

public class GroupParselet implements Parselet {

    @Override
    public Expression parse(MoLangParser parser, Token token) {
        // this only for conditions
        Expression expr = parser.parseExpression();
        parser.consumeToken(TokenType.BRACKET_RIGHT);

        return expr;
    }
}
