package com.bedrockk.molang.runtime.value;

public class StringValue implements MoValue {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String asString() {
        return value;
    }
}
