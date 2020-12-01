package com.phoshp.molang.parser.expression;

import com.phoshp.molang.parser.Expression;
import lombok.Value;

@Value
public class ForEachExpression implements Expression {

    Expression variable;
    Expression array;
    Expression body;
}
