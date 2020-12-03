package com.bedrockk.molang.runtime;

import com.bedrockk.molang.parser.Expression;
import com.bedrockk.molang.runtime.struct.ContextStruct;
import com.bedrockk.molang.runtime.struct.VariableStruct;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class MoLangRuntime {

    private final MoLangEnvironment environment = new MoLangEnvironment();

    public MoLangRuntime() {
        environment.getStructs().put("math", MoLangMath.LIBRARY);
        environment.getStructs().put("temp", new VariableStruct());
    }

    public Object execute(List<Expression> expressions) {
        return execute(expressions, new HashMap<>());
    }

    public Object execute(List<Expression> expressions, Map<String, Object> context) {
        environment.getStructs().put("context", new ContextStruct(context));

        Object result = 0.0;
        for (Expression expression : new ArrayList<>(expressions)) {
            result = expression.evaluate(environment);
        }

        environment.getStructs().get("temp").clear();
        environment.getStructs().remove("context");

        return result;
    }
}
