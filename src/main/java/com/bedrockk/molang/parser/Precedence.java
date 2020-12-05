package com.bedrockk.molang.parser;

public enum Precedence {
    ANYTHING,
    SCOPE,

    ASSIGNMENT,
    CONDITIONAL,
    ARRAY_ACCESS,

    COALESCE,

    AND,
    OR,

    COMPARE,

    SUM,
    PRODUCT,
    PREFIX,
    ARROW
}
