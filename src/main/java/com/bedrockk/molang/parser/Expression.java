package com.bedrockk.molang.parser;

import com.bedrockk.molang.runtime.MoLangEnvironment;

public interface Expression {

    Object evaluate(MoLangEnvironment environment);
}
