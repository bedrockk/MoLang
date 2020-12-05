package com.bedrockk.molang.parser;

import lombok.Getter;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
public class ExprTraverser {

    private boolean stopTraversal = false;
    private final List<ExprVisitor> visitors = new LinkedList<>();

    public void traverse(List<Expression> expressions) {
        for (ExprVisitor visitor : visitors) {
            visitor.beforeTraverse(expressions);
        }

        stopTraversal = false;
        traverseArray(expressions);

        for (ExprVisitor visitor : visitors) {
            visitor.afterTraverse(expressions);
        }
    }

    private void traverseArray(List<Expression> expressions) {
        List<Expression> list = new ArrayList<>(expressions);

        for (int i = 0; i < list.size(); i++) {
            Expression expression = list.get(i);

            boolean removeCurrent = false;
            boolean traverseChildren = true;
            boolean traverseCurrent = true;

            for (ExprVisitor visitor : visitors) {
                Object result = visitor.onVisit(expression);

                if (result instanceof ActionType) {
                    switch ((ActionType) result) {
                        case REMOVE_CURRENT:
                            removeCurrent = true;
                            break;
                        case STOP_TRAVERSAL:
                            stopTraversal = true;
                            break;
                        case DONT_TRAVERSE_CURRENT_AND_CHILDREN:
                            traverseCurrent = false;
                        case DONT_TRAVERSE_CHILDREN:
                            traverseChildren = false;
                            break;
                    }
                } else if (result instanceof Expression) {
                    expression = (Expression) result;
                }
            }

            if (!traverseCurrent) {
                break;
            } else  if (traverseChildren && !removeCurrent) {
                traverseExpr(expression);
            }

            for (ExprVisitor visitor : visitors) {
                visitor.onLeave(expression);
            }

            if (removeCurrent) {
                expressions.remove(i);
            } else {
                expressions.set(i, expression);
            }

            if (stopTraversal) {
                break;
            }
        }
    }

    private void traverseExpr(@NonNull Expression expression) {
        for (Field field : getAllFields(expression.getClass())) {
            field.setAccessible(true);
            Object fieldValue = getFieldValue(field, expression);

            if (fieldValue instanceof Expression) {
                Expression subExpr = (Expression) fieldValue;

                boolean removeCurrent = false;
                boolean traverseChildren = true;
                boolean traverseCurrent = true;

                for (ExprVisitor visitor : visitors) {
                    Object result = visitor.onVisit(subExpr);

                    if (result instanceof ActionType) {
                        switch ((ActionType) result) {
                            case REMOVE_CURRENT:
                                removeCurrent = true;
                                break;
                            case STOP_TRAVERSAL:
                                stopTraversal = true;
                                break;
                            case DONT_TRAVERSE_CURRENT_AND_CHILDREN:
                                traverseCurrent = false;
                            case DONT_TRAVERSE_CHILDREN:
                                traverseChildren = false;
                                break;
                        }
                    } else if (result instanceof Expression) {
                        subExpr = (Expression) result;
                    }
                }

                if (!traverseCurrent) {
                    break;
                } else if (traverseChildren && !removeCurrent) {
                    traverseExpr(subExpr);
                }

                for (ExprVisitor visitor : visitors) {
                    visitor.onLeave(subExpr);
                }

                if (removeCurrent) {
                    setFieldValue(field, expression, null);
                } else {
                    setFieldValue(field, expression, subExpr);
                }

                if (stopTraversal) {
                    break;
                }
            } else if (fieldValue != null && fieldValue.getClass().isArray()) {
                Object[] array = (Object[]) fieldValue;
                List<Expression> exprs = new ArrayList<>();

                for (Object i : array) {
                    if (i instanceof Expression) {
                        exprs.add((Expression) i);
                    }
                }

                traverseArray(exprs);

                setFieldValue(field, expression, exprs.toArray(new Expression[0]));
            }
        }
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();

        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }

        return fields;
    }

    private Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (Throwable throwable) {
            // noop
        }

        return null;
    }

    private void setFieldValue(Field field, Object obj, Object value) {
        try {
            field.set(obj, value);
        } catch (Throwable throwable) {
            // noop
        }
    }

    public enum ActionType{
        REMOVE_CURRENT,
        STOP_TRAVERSAL,
        DONT_TRAVERSE_CURRENT_AND_CHILDREN,
        DONT_TRAVERSE_CHILDREN
    }
}
