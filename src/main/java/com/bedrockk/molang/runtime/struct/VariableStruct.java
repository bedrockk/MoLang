package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VariableStruct implements MoStruct {
    private final ConcurrentMap<String, Object> values = new ConcurrentHashMap<>();

    public VariableStruct() {}

    public VariableStruct(Map<String, Object> map) {
        values.putAll(map);
    }

    @Override
    public void set(String name, Object value) {
        if (value instanceof MoStruct || value instanceof Float) {
            LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
            String main = segments.poll();

            if (segments.size() > 0 && main != null) {
                Object struct = get(main, MoParams.EMPTY);

                if (!(struct instanceof MoStruct)) {
                    struct = new VariableStruct();
                }

                ((MoStruct) struct).set(segments.poll(), value);
            } else {
                values.put(name, value);
            }
        } else {
            throw new RuntimeException("Tried to set invalid type of value in MoStruct: " + value.getClass());
        }
    }

    @Override
    public Object get(String name, MoParams params) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (segments.size() > 0 && main != null) {
            Object struct = values.get(main);

            if (struct instanceof MoStruct) {
                return ((MoStruct) struct).get(segments.poll(), MoParams.EMPTY);
            }
        }

        return values.get(name);
    }

    @Override
    public void clear() {
        values.clear();
    }
}
