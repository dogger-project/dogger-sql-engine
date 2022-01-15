package com.github.bhy.parse.spark3;

import com.github.bhy.antlr4.spark3.SparkSqlBaseBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @Classname SparkSqlParseVisitor
 * @Description TODO
 * @Date 2022/1/15 12:48 AM
 * @Created by bihaiyang
 */
public class SparkSqlParseVisitor extends SparkSqlBaseBaseVisitor {

    @Override
    public Object visit(ParseTree tree) {
        Object visit = super.visit(tree);
        return visit;
    }
}
