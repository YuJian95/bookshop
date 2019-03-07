package common;

/**
 * 自定义异常，用来抛出异常信息
 */

public class MyException extends RuntimeException {

    private String message;

    // 无参构造函数
    public MyException() {

    }

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
