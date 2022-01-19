package com.dogger.parse.spark3;


import com.dogger.core.antlr4.spark3.Spark3SqlBaseLexer;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.sql.catalyst.parser.UpperCaseCharStream;
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
        Spark3SqlBaseLexer lexer = new Spark3SqlBaseLexer(upperCaseCharStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Spark3SqlBaseParser sparkSqlBaseParser = new Spark3SqlBaseParser(tokenStream);

        sparkSqlBaseParser.getInterpreter().setPredictionMode(PredictionMode.SLL);

        SparkSqlParseVisitor sqlVisitor = new SparkSqlParseVisitor();
        Object visit = sqlVisitor.visit(sparkSqlBaseParser.singleStatement());
        System.out.println(visit);
    }
}
