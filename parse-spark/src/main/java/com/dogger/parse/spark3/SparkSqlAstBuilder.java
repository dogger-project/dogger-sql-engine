package com.dogger.parse.spark3;

import static com.dogger.common.consts.SymbolConst.COMMA;
import static com.dogger.common.enums.CommonSqlType.CREATE_DATABASE;

import com.dogger.core.antlr4.spark3.Spark3SqlBaseBaseVisitor;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.CommentNamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.CommentSpecContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.CreateNamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.DescribeNamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.DropNamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.ErrorCapturingIdentifierContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.LocationSpecContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.MultipartIdentifierContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.MultipartIdentifierListContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.NamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.SetNamespaceLocationContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.SetNamespacePropertiesContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.ShowCurrentNamespaceContext;
import com.dogger.core.antlr4.spark3.Spark3SqlBaseParser.ShowNamespacesContext;
import com.dogger.parse.bean.Database;
import com.dogger.parse.bean.StatementResult;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.apache.spark.sql.catalyst.parser.ParserUtils;

/**
 * @Classname SparkAstBuilder
 * @Description TODO
 * @Date 2022/1/15 10:48 PM
 * @Created by bihaiyang
 */
public class SparkSqlAstBuilder extends Spark3SqlBaseBaseVisitor<StatementResult>{

    @Override
    public StatementResult visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public StatementResult visitCreateNamespace(CreateNamespaceContext ctx) {
        ParserUtils.checkDuplicateClauses(ctx.commentSpec(), "COMMENT", ctx);
        ParserUtils.checkDuplicateClauses(ctx.locationSpec(), "LOCATION", ctx);
        ParserUtils.checkDuplicateClauses(ctx.PROPERTIES(), "WITH PROPERTIES", ctx);
        ParserUtils.checkDuplicateClauses(ctx.DBPROPERTIES(), "WITH DBPROP21ERTIES", ctx);

        List<String> commentSpecs = visitCommentSpecList(ctx.commentSpec());
        List<String> locationSpecs = visitLocationSpecList(ctx.locationSpec());
        List<String> namespaces = visitMultipartIdentifierList(ctx.multipartIdentifier());
        String dbNames = String.join(COMMA, namespaces);
        String comments = String.join(COMMA, commentSpecs);
        String locations = String.join(COMMA, locationSpecs);
        Database database = new Database(dbNames, locations, comments);
        return new StatementResult(CREATE_DATABASE, database);
    }

    @Override
    public StatementResult visitSetNamespaceProperties(SetNamespacePropertiesContext ctx) {
        ctx.DBPROPERTIES();
        return super.visitSetNamespaceProperties(ctx);
    }

    @Override
    public StatementResult visitSetNamespaceLocation(SetNamespaceLocationContext ctx) {
        return super.visitSetNamespaceLocation(ctx);
    }

    @Override
    public StatementResult visitDropNamespace(DropNamespaceContext ctx) {
        return super.visitDropNamespace(ctx);
    }

    @Override
    public StatementResult visitShowNamespaces(ShowNamespacesContext ctx) {
        return super.visitShowNamespaces(ctx);
    }

    @Override
    public StatementResult visitShowCurrentNamespace(ShowCurrentNamespaceContext ctx) {
        return super.visitShowCurrentNamespace(ctx);
    }

    @Override
    public StatementResult visitDescribeNamespace(DescribeNamespaceContext ctx) {
        return super.visitDescribeNamespace(ctx);
    }

    @Override
    public StatementResult visitCommentNamespace(CommentNamespaceContext ctx) {
        return super.visitCommentNamespace(ctx);
    }

    @Override
    public StatementResult visitNamespace(NamespaceContext ctx) {
        return super.visitNamespace(ctx);
    }

    @Override
    public StatementResult visitMultipartIdentifierList(MultipartIdentifierListContext ctx) {
        return super.visitMultipartIdentifierList(ctx);
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, StatementResult currentResult) {
        return currentResult == null;
    }

    public List<String> visitCommentSpecList(List<CommentSpecContext> commentSpecContexts) {
        return commentSpecContexts.stream()
            .map(CommentSpecContext::STRING)
            .map(ParserUtils::string)
            .collect(Collectors.toList());
    }

    public List<String> visitLocationSpecList(List<LocationSpecContext> locationSpecContexts) {
        return locationSpecContexts.stream()
            .map(LocationSpecContext::STRING)
            .map(ParserUtils::string)
            .collect(Collectors.toList());
    }

    public List<String> visitMultipartIdentifierList(
        MultipartIdentifierContext multipartIdentifierContext) {
        return  multipartIdentifierContext.parts.stream()
            .map(ErrorCapturingIdentifierContext::getText)
            .collect(Collectors.toList());
    }



}
