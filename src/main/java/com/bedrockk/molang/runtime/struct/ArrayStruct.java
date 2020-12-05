package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;

import java.util.Map;

public class ArrayStruct extends VariableStruct {

    public ArrayStruct() {}

    public ArrayStruct(Map<String, MoValue> map) {
        super(map);
    }

    @Override
    public void set(String name, MoValue value) {
        // Last part always must be a integer
        String[] parts = name.split("\\.");
        parts[parts.length - 1] = String.valueOf(Integer.parseInt(parts[parts.length - 1]));

        super.set(String.join(".", parts), value);
    }
}
