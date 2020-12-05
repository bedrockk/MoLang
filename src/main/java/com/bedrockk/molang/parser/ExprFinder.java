package com.bedrockk.molang.parser;

import com.bedrockk.molang.parser.visitor.FindingVisitor;
import com.bedrockk.molang.parser.visitor.FirstFindingVisitor;

import java.util.List;
import java.util.function.Predicate;

public class ExprFinder {

    public static List<Expression> find(List<Expression> expressions, Predicate<Expression> predicate) {
        ExprTraverser traverser = new ExprTraverser();
        FindingVisitor visitor = new FindingVisitor(predicate);

        traverser.getVisitors().add(visitor);
        traverser.traverse(expressions);

        return visitor.getFoundExpressions();
    }

    public static Expression findFirst(List<Expression> expressions, Predicate<Expression> predicate) {
        ExprTraverser traverser = new ExprTraverser();
        FirstFindingVisitor visitor = new FirstFindingVisitor(predicate);

        traverser.getVisitors().add(visitor);
        traverser.traverse(expressions);

        return visitor.getFound();
    }
}
