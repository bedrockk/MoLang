package com.bedrockk.molang.parser.expression.binaryop;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.expression.BinaryOpExpression;

public class ArrowExpression extends BinaryOpExpression {

    public ArrowExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "->";
    }
}
