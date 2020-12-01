package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.Value;

@Value
public class ReturnExpression implements Expression {

    Expression expression;
}
