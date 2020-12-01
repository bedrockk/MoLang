package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.Value;

@Value
public class TernaryExpression implements Expression {

    Expression condition;
    Expression thenExpr;
    Expression elseExpr;
}
