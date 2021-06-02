package com.bedrockk.molang.ast;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class TernaryExpression implements Expression {

    Expression condition;
    Expression thenExpr;
    Expression elseExpr;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        if (condition.evaluate(scope, environment).equals(DoubleValue.ONE)) {
            return thenExpr == null ? condition.evaluate(scope, environment) : thenExpr.evaluate(scope, environment);
        } else if (elseExpr != null) {
            return elseExpr.evaluate(scope, environment);
        }

        return DoubleValue.ZERO;
    }
}
