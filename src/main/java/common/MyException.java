package common;

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
