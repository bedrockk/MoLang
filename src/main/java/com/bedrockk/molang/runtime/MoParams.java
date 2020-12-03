package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.MoStruct;

import java.util.ArrayList;
import java.util.List;

public class MoParams {

    public static final MoParams EMPTY = new MoParams(new ArrayList<>());

    private final List<Object> params;

    public MoParams(List<Object> params) {
        this.params = params;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(int index, Class<T> expectedType) {
        Object obj = params.get(index);

        if (obj.getClass().equals(expectedType)) {
            return (T) obj;
        } else {
            throw new RuntimeException("MoParams: Expected parameter type of " + expectedType.getName() + ", " + obj.getClass().getName() + " given.");
        }
    }

    public boolean contains(int index) {
        return params.contains(index);
    }

    public int getInt(int index) {
        return (int) getFloat(index);
    }

    public float getFloat(int index) {
        return get(index, Float.class);
    }

    public MoStruct getStruct(int index) {
        return get(index, MoStruct.class);
    }

    public String getString(int index) {
        return get(index, String.class);
    }

    public MoLangEnvironment getEnv(int index) {
        return get(index, MoLangEnvironment.class);
    }
}
