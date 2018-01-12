package zqh.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zqh.commons.exception.NoLoginException;
import zqh.commons.util.SessionUtil;
import zqh.dto.AjaxResult;
import zqh.model.Account;
import zqh.model.Administrator;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class GlobalUserAop {

    @Autowired
    private HttpSession session;
    @Pointcut("execution(* zqh.web.admin.*Controller.*(..)) && !execution(* zqh.web..*Controller.login(..))")
    public void adminLoginService() {

    }

    @Pointcut("execution(* zqh.web.*Controller.*(..)) && !execution(* zqh.web.*Controller.*ByAdminOrAccount(..)) && !execution(* zqh.web..*Controller.login(..))")
    public void accountLoginService() {

    }

    @Pointcut("execution(* zqh.web.*Controller.*ByAdminOrAccount(..)) && !execution(* zqh.web..*Controller.login(..))")
    public void loginService() {

    }

    @Around("adminLoginService()")
    public Object adminLoginValidate(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Administrator administrator = null;
        try {
            administrator = adminLoginValidate();
        } catch (NoLoginException e) {
            return AjaxResult.fail(e.getCode(), "");
        }
//        if (administrator == null) {
//            return AjaxResult.fail(2,"");
//        }
        return thisJoinPoint.proceed(thisJoinPoint.getArgs());
    }

    @Around("accountLoginService()")
    public Object accountLoginValidate(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Account account = null;
        try {
            account = accountLoginValidate();
        } catch (NoLoginException e) {
            return AjaxResult.fail(e.getCode(), "");
        }
//        if (account == null) {
//            return AjaxResult.fail(1,"");
//        }
        Integer invalid = SessionUtil.getAlumnusInvalid(session);
        if (invalid == null || invalid == 2 || invalid == 1) {
            return AjaxResult.fail(305,"");
        }
        return thisJoinPoint.proceed(thisJoinPoint.getArgs());
    }

    @Around("loginService()")
    public Object loginValidate(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Account account = null;
        Administrator administrator = null;
        try {
            account = accountLoginValidate();
            administrator = adminLoginValidate();
        } catch (Exception e) {
        }
        if (account != null || administrator != null) {
            return thisJoinPoint.proceed(thisJoinPoint.getArgs());
        } else {
            return AjaxResult.fail(1,"");
        }
    }

    private Account accountLoginValidate() {
        System.out.println("=========1==============");
        if (session != null) {
            return SessionUtil.getUser(session);
        } else {
            throw new NoLoginException(1, "");
        }
    }

    private Administrator adminLoginValidate() {
        System.out.println("=========2==============");
        if (session != null) {
            return SessionUtil.getAdmin(session);
        } else {
            throw new NoLoginException(2, "");
        }
    }
}
