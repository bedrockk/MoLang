package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;

public interface MoStruct {

    void set(String key, Object value);

    Object get(String key, MoParams params);

    void clear();
}
