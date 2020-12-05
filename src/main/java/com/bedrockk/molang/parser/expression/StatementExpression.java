package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class StatementExpression implements Expression {

    Expression[] expressions;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        for (Expression expression : expressions) {
            expression.evaluate(scope, environment);

            if (scope.getReturnValue() != null) {
                return scope.getReturnValue();
            } else if (scope.isBreak() || scope.isContinue()) {
                break;
            }
        }

        return DoubleValue.ZERO;
    }
}
