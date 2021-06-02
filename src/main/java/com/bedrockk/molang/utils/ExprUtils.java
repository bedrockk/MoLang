package com.bedrockk.molang.utils;

import com.bedrockk.molang.Expression;

public final class ExprUtils {

    public static Expression getExprAttribute(Expression expression, String attributeName) {
        Object parent = expression.getAttributes().get(attributeName);

        if (parent instanceof Expression) {
            return (Expression) parent;
        }

        return null;
    }

    public static Expression parent(Expression expression) {
        return getExprAttribute(expression, "parent");
    }

    public static Expression next(Expression expression) {
        return getExprAttribute(expression, "next");
    }

    public static Expression previous(Expression expression) {
        return getExprAttribute(expression, "previous");
    }
}
