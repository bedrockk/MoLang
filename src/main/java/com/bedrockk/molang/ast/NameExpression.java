package com.bedrockk.molang.ast;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class NameExpression implements Expression {

    String name;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        return environment.getValue(name);
    }

    @Override
    public void assign(MoScope scope, MoLangEnvironment environment, MoValue value) {
        environment.setValue(name, value);
    }
}
