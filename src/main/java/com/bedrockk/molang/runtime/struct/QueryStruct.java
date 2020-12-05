package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class QueryStruct implements MoStruct {

    private final Map<String, Function<MoParams, Object>> functions;

    @Override
    public MoValue get(String key, MoParams params) {
        if (functions.containsKey(key)) {
            return MoValue.of(functions.get(key).apply(params));
        }

        return null;
    }

    @Override
    public void set(String name, MoValue value) {
        throw new RuntimeException("Cannot set a value in query struct");
    }

    @Override
    public void clear() {
        functions.clear();
    }
}
