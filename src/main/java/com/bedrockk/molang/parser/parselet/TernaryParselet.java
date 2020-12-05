package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.InfixParselet;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.expression.TernaryExpression;
import com.bedrockk.molang.parser.tokenizer.Token;
import com.bedrockk.molang.parser.tokenizer.TokenType;

public class TernaryParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        if (parser.matchToken(TokenType.COLON)) {
            return new TernaryExpression(leftExpr, null, parser.parseExpression(getPrecedence()));
        } else {
            Expression thenExpr = parser.parseExpression(getPrecedence());

            if (!parser.matchToken(TokenType.COLON)) {
                return new TernaryExpression(leftExpr, thenExpr, null);
            } else {
                return new TernaryExpression(leftExpr, thenExpr, parser.parseExpression(getPrecedence()));
            }
        }
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.CONDITIONAL;
    }
}
