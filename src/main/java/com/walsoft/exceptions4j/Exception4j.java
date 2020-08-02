package com.walsoft.exceptions4j;

import com.google.gson.Gson;
import com.rits.cloning.Cloner;

import java.util.HashMap;
import java.util.Map;

public class Exception4j extends Exception {
    private ExceptionCode exceptionCode;
    private String name;
    private Object content;


    public Exception4j(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.name = this.getClassName();
        this.exceptionCode = exceptionCode;
    }

    public Exception4j(ExceptionCode exceptionCode, Object content) {
        super(exceptionCode.getMessage());
        this.name = this.getClassName();
        this.exceptionCode = exceptionCode;
        this.content = content;
    }

    public Exception4j(String message) {
        super(message);
        this.name = this.getClassName();
    }

    public Exception4j(String message, Object content) {
        super(message);
        this.name = this.getClassName();
        this.content = content;
    }

    public Exception4j(Exception exception) {
        super(exception);
        this.name = this.getClassName();
    }

    public Exception4j(Exception exception, Object content) {
        super(exception);
        this.name = this.getClassName();
        this.content = content;
    }

    public Exception4j(Object content) {
        this.name = this.getClassName();
        this.content = content;
    }

    public Exception4j() {
        this.name = this.getClassName();
    }

    private String getClassName(){
        return this.getClass().getSimpleName();
    }

    /**
     * Deep clones content and returns
     * @return deep cloned content
     */
    public Object getContent() {
        Object cloned = null;

        if(this.content != null) {
            Cloner cloner = new Cloner();
            cloned = cloner.deepClone(content);
        }

        return cloned;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        Map<String, Object > map = new HashMap<>();
        map.put("name", this.name);
        String code = this.exceptionCode != null ? this.exceptionCode.getCode() : null;
        map.put("code", code);
        map.put("message", super.getMessage());
        map.put("content", this.content);
        return new Gson().toJson(map);
    }
}
