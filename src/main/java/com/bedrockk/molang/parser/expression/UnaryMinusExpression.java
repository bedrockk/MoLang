package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.Value;

@Value
public class UnaryMinusExpression implements Expression {

    Expression expression;
}
