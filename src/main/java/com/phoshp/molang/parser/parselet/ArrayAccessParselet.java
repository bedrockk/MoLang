package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.InfixParselet;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.ArrayAccessExpression;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenType;

public class ArrayAccessParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        Expression index = parser.parseExpression();
        parser.consumeToken(TokenType.ARRAY_RIGHT);

        return new ArrayAccessExpression(leftExpr, index);
    }
}
