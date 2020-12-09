package com.bedrockk.molang.parser.visitor;

import com.bedrockk.molang.parser.ExprVisitor;
import com.bedrockk.molang.parser.Expression;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class ExprConnectingVisitor implements ExprVisitor {

    private final LinkedList<Expression> stack = new LinkedList<>();
    private Expression previous;

    @Override
    public void beforeTraverse(List<Expression> expressions) {
        stack.clear();
        previous = null;
    }

    @Override
    public Object onVisit(Expression expression) {
        if (!stack.isEmpty()) {
            expression.getAttributes().put("parent", stack.getLast());
        }

        if (previous != null && expression.getAttributes().get("parent") == previous.getAttributes().get("parent")) {
            expression.getAttributes().put("previous", previous);
            previous.getAttributes().put("next", expression);
        }

        stack.add(expression);

        return null;
    }

    @Override
    public void onLeave(Expression expression) {
        previous = expression;
        stack.pollLast();
    }
}
