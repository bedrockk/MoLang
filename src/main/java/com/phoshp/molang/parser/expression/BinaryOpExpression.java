package com.phoshp.molang.parser.expression;

import com.phoshp.molang.parser.Expression;
import lombok.*;

@Getter
@RequiredArgsConstructor
abstract public class BinaryOpExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public abstract String getSigil();
}
