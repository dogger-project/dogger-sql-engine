package com.dogger.parse.spark3;


import com.dogger.core.antlr4.spark3.Spark3SqlBaseLexer;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.sql.catalyst.parser.UpperCaseCharStream;

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
        Spark3SqlBaseLexer spark3SqlBaseLexer = new Spark3SqlBaseLexer(upperCaseCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(spark3SqlBaseLexer);
        Spark3SqlBaseParser sparkSqlBaseParser = new Spark3SqlBaseParser(commonTokenStream);

        sparkSqlBaseParser.getInterpreter().setPredictionMode(PredictionMode.LL);

        SparkSqlParseVisitor sqlVisitor = new SparkSqlParseVisitor();
        sqlVisitor.visit(sparkSqlBaseParser.singleStatement());
    }
}
