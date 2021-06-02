package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.parser.InfixParselet;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.ast.ArrayAccessExpression;
import com.bedrockk.molang.parser.tokenizer.Token;
import com.bedrockk.molang.parser.tokenizer.TokenType;

public class ArrayAccessParselet implements InfixParselet {

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        Expression index = parser.parseExpression(getPrecedence());
        parser.consumeToken(TokenType.ARRAY_RIGHT);

        return new ArrayAccessExpression(leftExpr, index);
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.ARRAY_ACCESS;
    }
}
