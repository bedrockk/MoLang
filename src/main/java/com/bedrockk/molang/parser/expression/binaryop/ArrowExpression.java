package com.bedrockk.molang.parser.expression.binaryop;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.expression.BinaryOpExpression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;

public class ArrowExpression extends BinaryOpExpression {

    public ArrowExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "->";
    }

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        Object leftEnv = left.evaluate(scope, environment);
        if (leftEnv instanceof MoLangEnvironment) {
            return right.evaluate(scope, (MoLangEnvironment) leftEnv);
        }

        return null;
    }
}
