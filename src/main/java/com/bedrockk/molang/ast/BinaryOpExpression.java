package com.bedrockk.molang.ast;

import com.bedrockk.molang.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract public class BinaryOpExpression implements Expression {

    protected final Expression left;
    protected final Expression right;

    public abstract String getSigil();
}
