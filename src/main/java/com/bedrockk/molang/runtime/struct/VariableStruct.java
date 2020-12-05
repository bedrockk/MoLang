package com.bedrockk.molang.runtime.struct;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class VariableStruct implements MoStruct {
    private final Map<String, MoValue> map;

    public VariableStruct() {
        this.map = new HashMap<>();
    }

    @Override
    public void set(String name, MoValue value) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (segments.size() > 0 && main != null) {
            Object struct = get(main, MoParams.EMPTY);

            if (!(struct instanceof MoStruct)) {
                struct = new VariableStruct();
            }

            ((MoStruct) struct).set(segments.poll(), value);

            map.put(main, (MoStruct) struct);
        } else {
            map.put(name, value);
        }
    }

    @Override
    public MoValue get(String name, MoParams params) {
        LinkedList<String> segments = new LinkedList<>(Arrays.asList(name.split("\\.")));
        String main = segments.poll();

        if (segments.size() > 0 && main != null) {
            Object struct = map.get(main);

            if (struct instanceof MoStruct) {
                return ((MoStruct) struct).get(segments.poll(), MoParams.EMPTY);
            }
        }

        return map.get(name);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
