package com.dogger.parse.spark3;

import com.dogger.core.antlr4.spark3.Spark3SqlBaseBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @Classname SparkSqlParseVisitor
 * @Description TODO
 * @Date 2022/1/15 12:48 AM
 * @Created by bihaiyang
 */
public class SparkSqlParseVisitor extends Spark3SqlBaseBaseVisitor {

    @Override
    public Object visit(ParseTree tree) {
        Object visit = super.visit(tree);
        return visit;
    }
}
