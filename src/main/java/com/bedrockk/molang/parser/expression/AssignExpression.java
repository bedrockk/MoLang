package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class AssignExpression implements Expression {

    Expression variable;
    Expression expr;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        variable.assign(scope, environment, expr.evaluate(scope, environment));

        return DoubleValue.ZERO;
    }
}
