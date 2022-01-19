package com.dogger.parse.bean;

import com.dogger.common.enums.CommonSqlType;

/**
 * @Classname StatementResult
 * @Description TODO
 * @Date 2022/1/20 12:45 AM
 * @Created by bihaiyang
 */
public class StatementResult {

    private CommonSqlType type;
    private BaseStatement statement;

    public CommonSqlType getType() {
        return type;
    }

    public void setType(CommonSqlType type) {
        this.type = type;
    }

    public BaseStatement getStatement() {
        return statement;
    }

    public void setStatement(BaseStatement statement) {
        this.statement = statement;
    }

    public StatementResult(CommonSqlType type, BaseStatement statement) {
        this.type = type;
        this.statement = statement;
    }

    public StatementResult(BaseStatement statement) {
        this.statement = statement;
    }

    public StatementResult(CommonSqlType type) {
        this.type = type;
    }
}
