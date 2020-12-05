package com.bedrockk.molang.parser.expression;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.MoLangEnvironment;
import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.MoScope;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class FuncCallExpression implements Expression {

    Expression name;
    Expression[] args;

    @Override
    public MoValue evaluate(MoScope scope, MoLangEnvironment environment) {
        List<Expression> params = Arrays.asList(args);
        String name = this.name instanceof NameExpression ? ((NameExpression) this.name).getName() : this.name.evaluate(scope, environment).asString();

        return environment.getValue(name, new MoParams(
                params.stream().map(e -> e.evaluate(scope, environment)).collect(Collectors.toList())
        ));
    }
}
