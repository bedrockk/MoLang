package com.phoshp.molang.parser.expression.binaryop;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.expression.BinaryOpExpression;

public class BooleanOrExpression extends BinaryOpExpression {

    public BooleanOrExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "||";
    }
}
