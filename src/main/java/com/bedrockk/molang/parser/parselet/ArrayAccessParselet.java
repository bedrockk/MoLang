package com.bedrockk.molang.parser.parselet;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.InfixParselet;
import com.bedrockk.molang.parser.MoLangParser;
import com.bedrockk.molang.parser.Precedence;
import com.bedrockk.molang.parser.expression.ArrayAccessExpression;
import com.bedrockk.molang.parser.tokenizer.Token;
import com.bedrockk.molang.parser.tokenizer.TokenType;

public class ArrayAccessParselet extends InfixParselet {

    public ArrayAccessParselet(Precedence precedence) {
        super(precedence);
    }

    public ArrayAccessParselet() {
        super();
    }

    @Override
    public Expression parse(MoLangParser parser, Token token, Expression leftExpr) {
        Expression index = parser.parseExpression(getPrecedence());
        parser.consumeToken(TokenType.ARRAY_RIGHT);

        return new ArrayAccessExpression(leftExpr, index);
    }
}
