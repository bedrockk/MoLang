package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.value.MoValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoScope {

    private boolean isBreak = false;
    private boolean isContinue = false;
    private MoValue returnValue;
}