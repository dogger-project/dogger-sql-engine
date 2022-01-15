package com.github.bhy.parse.spark3;

import com.github.bhy.antlr4.UpperCaseCharStream;
import com.github.bhy.antlr4.spark3.SparkSqlBaseLexer;
import com.github.bhy.antlr4.spark3.SparkSqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Classname SparkSqlParseVisitorTest
 * @Description TODO
 * @Date 2022/1/15 6:14 PM
 * @Created by bihaiyang
 */
public class SparkSqlParseVisitorTest {

    @Test
    public void visit(){
        String sql = "CREATE DATABASE IF NOT EXISTS bigdata";
        UpperCaseCharStream upperCaseCharStream =
            new UpperCaseCharStream(CharStreams.fromString(StringUtils.trim(sql)));
        SparkSqlBaseLexer lexer = new SparkSqlBaseLexer(upperCaseCharStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SparkSqlBaseParser sparkSqlBaseParser = new SparkSqlBaseParser(tokenStream);

        sparkSqlBaseParser.getInterpreter().setPredictionMode(PredictionMode.SLL);

        SparkSqlParseVisitor sqlVisitor = new SparkSqlParseVisitor();
        Object visit = sqlVisitor.visit(sparkSqlBaseParser.singleStatement());
        System.out.println(visit);
    }
}
