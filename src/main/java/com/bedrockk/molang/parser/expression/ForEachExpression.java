package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.Value;

@Value
public class ForEachExpression implements Expression {

    Expression variable;
    Expression array;
    Expression body;
}
