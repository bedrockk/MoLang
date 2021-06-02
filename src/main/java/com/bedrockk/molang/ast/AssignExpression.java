package com.bedrockk.molang.ast;

import com.bedrockk.molang.Expression;
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
        var value = expr.evaluate(scope, environment);
        variable.assign(scope, environment, value);
        return value;
    }
}
