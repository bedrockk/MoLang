package com.bedrockk.molang.parser.expression.binaryop;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.expression.BinaryOpExpression;

public class GreaterExpression extends BinaryOpExpression {

    public GreaterExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return ">";
    }
}
