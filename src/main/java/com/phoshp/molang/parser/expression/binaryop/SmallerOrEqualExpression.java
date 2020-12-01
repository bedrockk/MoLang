package com.phoshp.molang.parser.expression.binaryop;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.expression.BinaryOpExpression;

public class SmallerOrEqualExpression extends BinaryOpExpression {

    public SmallerOrEqualExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "<=";
    }
}
