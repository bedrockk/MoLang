package com.phoshp.molang.parser.expression.binaryop;

import com.phoshp.molang.parser.Expression;
import com.phoshp.molang.parser.expression.BinaryOpExpression;

public class DivideExpression extends BinaryOpExpression {

    public DivideExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "/";
    }
}
