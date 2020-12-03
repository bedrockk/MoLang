package com.bedrockk.molang.parser;

import java.util.List;

public interface ExprVisitor {

    default void beforeTraverse(List<Expression> expressions) {
        // noop
    }

    Object onVisit(Expression expression);

    default void afterTraverse(List<Expression> expressions) {
        // noop
    }
}
