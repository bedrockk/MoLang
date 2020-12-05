package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.MoStruct;
import com.bedrockk.molang.runtime.value.DoubleValue;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Value;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Value
public class MoLangEnvironment implements MoValue {

    ConcurrentMap<String, MoStruct> structs = new ConcurrentHashMap<>();

    public MoValue getValue(String name) {
        return getValue(name, MoParams.EMPTY);
    }

    public MoValue getValue(String name, MoParams params) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (structs.containsKey(main)) {
            return structs.get(main).get(String.join(".", segments), params);
        }

        return new DoubleValue(0.0);
    }

    public void setValue(String name, MoValue value) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (structs.containsKey(main)) {
            structs.get(main).set(String.join(".", segments), value);
        }
    }

    @Override
    public Object value() {
        return this;
    }
}
