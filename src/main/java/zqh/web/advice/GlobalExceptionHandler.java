package zqh.web.advice;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zqh.commons.exception.DataViolationException;
import zqh.commons.exception.ExcelException;
import zqh.commons.exception.FileException;
import zqh.commons.exception.NoLoginException;
import zqh.dto.AjaxResult;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理(主键重复异常:11)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoLoginException.class)
    public AjaxResult NoLoginExceptionHandler(NoLoginException e, HttpServletResponse response) {
        return AjaxResult.fail(e.getCode(), "");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public AjaxResult DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        e.printStackTrace();
        return AjaxResult.fail(11, "该记录已经存在");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public AjaxResult RuntimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return AjaxResult.fail(500, "未知运行异常");
    }

    @ExceptionHandler({CannotCreateTransactionException.class, DataAccessResourceFailureException.class})
    @ResponseBody
    public AjaxResult DataBaseExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return AjaxResult.fail(300, "数据库连接异常");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public AjaxResult DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        e.printStackTrace();
        return AjaxResult.fail(400, "数据不合法");
    }

    @ExceptionHandler(DataViolationException.class)
    @ResponseBody
    public AjaxResult DataViolationExceptionHandler(DataViolationException e) {
        return AjaxResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ExcelException.class)
    @ResponseBody
    public AjaxResult ExceptionHandler(ExcelException e) {
        return AjaxResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(FileException.class)
    @ResponseBody
    public AjaxResult ExceptionHandler(FileException e) {
        return AjaxResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult ExceptionHandler(Exception e) {
        e.printStackTrace();
        return AjaxResult.fail(500, "未知异常");
    }

}
