package com.bedrockk.molang.parser.expression.binaryop;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.parser.expression.BinaryOpExpression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;

public class CoalesceExpression extends BinaryOpExpression {

    public CoalesceExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String getSigil() {
        return "??";
    }

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        MoValue evalLeft = left.evaluate(scope, environment);
        MoValue value = environment.getValue(evalLeft.asString());

        if (value == null || value.equals(DoubleValue.ZERO)) {
            return right.evaluate(scope, environment);
        } else {
            return evalLeft;
        }
    }
}
