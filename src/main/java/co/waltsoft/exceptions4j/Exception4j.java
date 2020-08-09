package co.waltsoft.exceptions4j;

import com.google.gson.Gson;
import com.rits.cloning.Cloner;

import java.util.HashMap;
import java.util.Map;

public class Exception4j extends Exception {
    private final transient ExceptionCode exceptionCode;
    private final String name;
    private final transient Object content;


    public Exception4j(final ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.name = this.getClassName();
        this.exceptionCode = exceptionCode;
        this.content = null;
    }

    public Exception4j(final ExceptionCode exceptionCode, final Object content) {
        super(exceptionCode.getMessage());
        this.name = this.getClassName();
        this.exceptionCode = exceptionCode;
        this.content = content;
    }

    public Exception4j(final String message) {
        super(message);
        this.name = this.getClassName();
        content = null;
        this.exceptionCode = null;
    }

    public Exception4j(final String message, final Object content) {
        super(message);
        this.name = this.getClassName();
        this.content = content;
        this.exceptionCode = null;
    }

    public Exception4j(final Exception exception) {
        super(exception);
        this.name = this.getClassName();
        this.content = null;
        this.exceptionCode = null;
    }

    public Exception4j(final Exception exception, final Object content) {
        super(exception);
        this.name = this.getClassName();
        this.content = content;
        this.exceptionCode = null;
    }

    public Exception4j(final Object content) {
        this.name = this.getClassName();
        this.content = content;
        this.exceptionCode = null;
    }

    public Exception4j() {
        this.name = this.getClassName();
        content = null;
        this.exceptionCode = null;
    }

    private String getClassName(){
        return this.getClass().getSimpleName();
    }

    /**
     * Deep clones content and returns.
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
