package com.bedrockk.molang.ast;

import com.bedrockk.molang.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class ThisExpression implements Expression {

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        return environment;
    }
}
