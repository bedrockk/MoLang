package com.phoshp.molang.parser.expression;

import com.phoshp.molang.parser.Expression;
import lombok.Value;

@Value
public class TernaryExpression implements Expression {

    Expression condition;
    Expression thenExpr;
    Expression elseExpr;
}