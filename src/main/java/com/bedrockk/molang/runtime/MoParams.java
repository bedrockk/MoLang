package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.MoStruct;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import com.bedrockk.molang.runtime.value.StringValue;

import java.util.ArrayList;
import java.util.List;

public class MoParams {

    public static final MoParams EMPTY = new MoParams(new ArrayList<>());

    private final List<MoValue> params;

    public MoParams(List<MoValue> params) {
        this.params = params;
    }

    @SuppressWarnings("unchecked")
    public <T extends MoValue> T get(int index) {
        return (T) params.get(index);
    }

    public boolean contains(int index) {
        return params.size() >= index + 1;
    }

    public int getInt(int index) {
        return (int) getDouble(index);
    }

    public double getDouble(int index) {
        return this.<DoubleValue>get(index).asDouble();
    }

    public MoStruct getStruct(int index) {
        return get(index);
    }

    public String getString(int index) {
        return this.<StringValue>get(index).asString();
    }

    public MoLangEnvironment getEnv(int index) {
        return get(index);
    }

    public List<MoValue> getParams() {
        return params;
    }
}
