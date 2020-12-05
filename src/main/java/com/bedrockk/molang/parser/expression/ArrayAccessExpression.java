package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

@Value
public class ArrayAccessExpression implements Expression {

    Expression array;
    Expression index;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        String name = array instanceof NameExpression ? ((NameExpression) array).getName() : array.evaluate(scope, environment).asString();

        return environment.getValue(name + "." + (int) index.evaluate(scope, environment).asDouble());
    }

    @Override
    public void assign(MoScope scope, MoLangEnvironment environment, MoValue value) {
        String name = array instanceof NameExpression ? ((NameExpression) array).getName() : array.evaluate(scope, environment).asString();

        environment.setValue(name + "." + (int) index.evaluate(scope, environment).asDouble(), value);
    }
}
