package com.phoshp.molang.parser.parselet;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.InfixParselet;
import com.phoshp.molang.parser.Parselet;
import com.phoshp.molang.parser.MoLangParser;
import com.phoshp.molang.parser.expression.TernaryExpression;
import com.phoshp.molang.parser.tokenizer.Token;
import com.phoshp.molang.parser.tokenizer.TokenType;

public class TernaryParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        if (parser.matchToken(TokenType.COLON)) {
            return new TernaryExpression(leftExpr, null, parser.parseExpression());
        } else {
            Expression thenExpr = parser.parseExpression();

            if (!parser.matchToken(TokenType.COLON)) {
                return new TernaryExpression(leftExpr, thenExpr, null);
            } else {
                return new TernaryExpression(leftExpr, thenExpr, parser.parseExpression());
            }
        }
    }
}
