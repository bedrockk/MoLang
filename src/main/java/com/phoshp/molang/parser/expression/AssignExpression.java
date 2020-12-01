package com.phoshp.molang.parser.expression;

import com.phoshp.molang.parser.Expression;
import lombok.Value;

@Value
public class AssignExpression implements Expression {

    Expression variable;
    Expression expr;
}
