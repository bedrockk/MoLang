package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;
import lombok.Value;

import java.util.Map;
import java.util.function.Function;

@Value
public class QueryStruct implements MoStruct {

    Map<String, Function<MoParams, Object>> functions;

    @Override
    public Object get(String key, MoParams params) {
        if (functions.containsKey(key)) {
            Object result = functions.get(key).apply(params);

            if (result instanceof Number) {
                return ((Number) result).floatValue();
            }

            return result;
        }

        return null;
    }

    @Override
    public void set(String name, Object value) {
        throw new RuntimeException("Cannot set a value in query struct");
    }

    @Override
    public void clear() {
        functions.clear();
    }
}
