package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.MoStruct;
import lombok.Value;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Value
public class MoLangEnvironment {

    ConcurrentMap<String, MoStruct> structs = new ConcurrentHashMap<>();

    public Object getValue(String name, MoParams params) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (structs.containsKey(main)) {
            return structs.get(main).get(segments.poll(), params);
        }

        return 0.0;
    }

    public void setValue(String name, Object value) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (structs.containsKey(main)) {
            structs.get(main).set(segments.poll(), value);
        }
    }
}
