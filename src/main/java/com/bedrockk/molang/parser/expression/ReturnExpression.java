package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class ReturnExpression implements Expression {

    Expression expression;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        MoValue eval = expression.evaluate(scope, environment);
        scope.setReturnValue(eval);

        return eval;
    }
}
