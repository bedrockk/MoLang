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
    public <T> T get(int index, Class<T> expectedType) {
        MoValue obj = params.get(index);

        if (obj.getClass().equals(expectedType)) {
            return (T) obj;
        } else {
            throw new RuntimeException("MoParams: Expected parameter type of " + expectedType.getName() + ", " + obj.getClass().getName() + " given.");
        }
    }

    public boolean contains(int index) {
        return params.size() >= index + 1;
    }

    public int getInt(int index) {
        return (int) getDouble(index);
    }

    public double getDouble(int index) {
        return get(index, DoubleValue.class).asDouble();
    }

    public MoStruct getStruct(int index) {
        return get(index, MoStruct.class);
    }

    public String getString(int index) {
        return get(index, StringValue.class).asString();
    }

    public MoLangEnvironment getEnv(int index) {
        return get(index, MoLangEnvironment.class);
    }

    public List<MoValue> getParams() {
        return params;
    }
}
