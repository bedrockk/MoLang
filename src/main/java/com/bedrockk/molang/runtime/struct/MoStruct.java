package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;

public interface MoStruct extends MoValue {

    void set(String key, MoValue value);

    MoValue get(String key, MoParams params);

    void clear();

    @Override
    default MoStruct value() {
        return this;
    }
}
