package zqh.commons.exception;

import lombok.Getter;

public class NoLoginException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    @Getter
    private int code;

    public NoLoginException(int code, String message) {
        super(message);
        this.code = code;
    }

    public NoLoginException(String message) {
        super(message);
    }
}
