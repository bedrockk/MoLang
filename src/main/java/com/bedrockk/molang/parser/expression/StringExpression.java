package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import com.bedrockk.molang.runtime.value.StringValue;
import lombok.Value;

@Value
public class StringExpression implements Expression {

    String string;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        return new StringValue(string);
    }
}
