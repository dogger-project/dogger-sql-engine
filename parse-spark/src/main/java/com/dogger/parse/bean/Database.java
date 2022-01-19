package com.dogger.parse.bean;


import java.util.Map;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @Classname Database
 * @Description 数据库信息
 * @Date 2022/1/16 4:00 PM
 * @Created by bihaiyang
 */
@Data
@RequiredArgsConstructor
public class Database implements BaseStatement{

    private String name;
    private String locationUri;
    private String comment;
    private Map<String, String> parameters;


    public Database(String name, String locationUri, String comment) {
        this.name = name;
        this.locationUri = locationUri;
        this.comment = comment;
    }
}
