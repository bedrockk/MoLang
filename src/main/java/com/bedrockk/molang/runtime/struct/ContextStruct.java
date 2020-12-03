package com.bedrockk.molang.runtime.struct;

import java.util.Map;

public class ContextStruct extends VariableStruct {

    public ContextStruct() {}

    public ContextStruct(Map<String, Object> map) {
        super(map);
    }

    @Override
    public void set(String name, Object value) {
        throw new RuntimeException("Tried to set a value in read-only context struct");
    }
}
