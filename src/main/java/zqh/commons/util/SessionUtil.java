package zqh.commons.util;

import zqh.commons.Const;
import zqh.commons.exception.DataViolationException;
import zqh.commons.exception.NoLoginException;
import zqh.model.Account;
import zqh.model.Administrator;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static Account getUser(HttpSession session){
        Account user = (Account) session.getAttribute(Const.SESSION_ACCOUNT);
        if (user != null) {
            return user;
        } else {
            throw new NoLoginException(1,"");
        }
    }

    public static Integer getUserAlumnus(HttpSession session){
        Account user = getUser(session);
        if (user.getAlumnus() != null){
            return user.getAlumnus();
        } else{
            throw new DataViolationException(305, "");
        }
    }

    public static Administrator getAdmin(HttpSession session){
        Administrator administrator = (Administrator) session.getAttribute(Const.SESSION_ADMIN);
        if (administrator != null) {
            return administrator;
        } else {
            throw new NoLoginException(2,"");
        }
    }

    public static Integer getAlumnusInvalid(HttpSession session) {
        Integer valid = (Integer)session.getAttribute(Const.SESSION_ALUMNUS_INVALID);
        return valid;
    }

}
