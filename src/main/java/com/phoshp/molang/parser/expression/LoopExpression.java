package com.phoshp.molang.parser.expression;

import com.phoshp.molang.parser.Expression;
import lombok.Value;

@Value
public class LoopExpression implements Expression {

    Expression count;
    Expression body;
}
