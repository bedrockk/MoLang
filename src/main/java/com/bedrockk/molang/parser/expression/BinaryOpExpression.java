package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import lombok.*;

@Getter
@RequiredArgsConstructor
abstract public class BinaryOpExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public abstract String getSigil();
}
