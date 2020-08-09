package co.waltsoft.exceptions4j;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Exception4jTest {

    private List<String> content;

    @BeforeEach
    public void before(){
        content = new ArrayList<>();
        content.add("test1");
        content.add("test2");
    }

    @Test
    public void getCorrectContent() {
        Exception4j exception4j = new Exception4j(content);
        Object actual = exception4j.getContent();
        Object expected = content;
        Assertions.assertTrue(expected.equals(actual));
        Assertions.assertNotSame(expected, actual);
    }

    @Test
    public void getCorrectExceptionCode() {
        Exception4j exception4j = new Exception4j(ExceptionCodeEnum.FILE_ERROR);
        ExceptionCode actual = exception4j.getExceptionCode();
        ExceptionCode expected = ExceptionCodeEnum.FILE_ERROR;
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void getCorrectExceptionName() {
        Exception4j exception4j = new TestException();
        String actual = exception4j.getName();
        String expected = "TestException";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithoutArgsAndCorrectExceptionName() {
        Exception4j exception4j = new TestException();
        String actual = exception4j.toString();
        String expected = "{\"name\":\"TestException\"}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithExceptionCode() {
        Exception4j exception4j = new Exception4j(ExceptionCodeEnum.REPORT_ERROR);
        String actual = exception4j.toString();
        String expected = "{\"code\":\"REPORT_ERROR\",\"name\":\"Exception4j\",\"message\":\"Could not parse report data\"}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithExceptionCodeAndContent() {
        Exception4j exception4j = new Exception4j(ExceptionCodeEnum.FILE_ERROR, content);
        String actual = exception4j.toString();
        String expected = "{\"code\":\"FILE_ERROR\",\"name\":\"Exception4j\",\"message\":\"Could not find file\",\"content\":[\"test1\",\"test2\"]}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithException() {
        Exception exception = new Exception("Test exception");
        Exception4j exception4j = new Exception4j(exception);
        String actual = exception4j.toString();
        String expected = "{\"name\":\"Exception4j\",\"message\":\"java.lang.Exception: Test exception\"}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithExceptionAndContent() {
        Exception exception = new Exception("Test exception");
        Exception4j exception4j = new Exception4j(exception, content);
        String actual = exception4j.toString();
        String expected = "{\"name\":\"Exception4j\",\"message\":\"java.lang.Exception: Test exception\",\"content\":[\"test1\",\"test2\"]}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithContent() {
        Exception4j exception4j = new Exception4j(content);
        String actual = exception4j.toString();
        String expected = "{\"name\":\"Exception4j\",\"content\":[\"test1\",\"test2\"]}";
        Assertions.assertTrue(expected.equals(actual));
    }

    @Test
    public void toStringWithMessageAndContent() {
        Exception4j exception4j = new Exception4j("Test error", content);
        String actual = exception4j.toString();
        String expected = "{\"name\":\"Exception4j\",\"message\":\"Test error\",\"content\":[\"test1\",\"test2\"]}";
        Assertions.assertTrue(expected.equals(actual));
    }

    private class TestException extends  Exception4j{
    }

    private enum ExceptionCodeEnum implements ExceptionCode{

        FILE_ERROR("Could not find file"),
        REPORT_ERROR("Could not parse report data");

        private String message;

        ExceptionCodeEnum(String message){
            this.message = message;
        }

        @Override
        public String getCode() {
            return this.toString();
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
