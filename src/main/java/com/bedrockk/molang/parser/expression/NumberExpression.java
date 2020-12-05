package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class NumberExpression implements Expression {

    double number;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        return new DoubleValue(number);
    }
}
