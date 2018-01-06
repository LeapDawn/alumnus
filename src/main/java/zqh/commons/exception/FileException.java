package zqh.commons.exception;

import lombok.Getter;

public class FileException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    private int code;

    public FileException(int code, String message) {
        super(message);
        this.code = code;
    }

    public FileException(String message) {
        super(message);
    }
}
