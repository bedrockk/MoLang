package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.Value;

@Value
public class StatementExpression implements Expression {

    Expression[] expressions;
}
