package com.github.bhy.parse.spark3;


import com.github.bhy.antlr4.UpperCaseCharStream;
import com.github.bhy.antlr4.spark3.SparkSqlBaseLexer;
import com.github.bhy.antlr4.spark3.SparkSqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Classname SparkSqlParse
 * @Description spark sql解析器
 * @Date 2022/1/13 12:16 AM
 * @Created by bihaiyang
 */
public class SparkSqlParse{

    public static void generateStatementInfo(String sql){
        UpperCaseCharStream upperCaseCharStream =
            new UpperCaseCharStream(CharStreams.fromString(StringUtils.trim(sql)));
        SparkSqlBaseLexer sparkSqlBaseLexer = new SparkSqlBaseLexer(upperCaseCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(sparkSqlBaseLexer);
        SparkSqlBaseParser sparkSqlBaseParser = new SparkSqlBaseParser(commonTokenStream);

        sparkSqlBaseParser.getInterpreter().setPredictionMode(PredictionMode.LL);

        SparkSqlParseVisitor sqlVisitor = new SparkSqlParseVisitor();
        sqlVisitor.visit(sparkSqlBaseParser.singleStatement());
    }
}
