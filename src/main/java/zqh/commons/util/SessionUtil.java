package zqh.commons.util;

import zqh.commons.Const;
import zqh.commons.exception.DataViolationException;
import zqh.model.Account;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static Account getUser(HttpSession session){
        Account user = (Account) session.getAttribute(Const.SESSION_ACCOUNT);
        if (user != null) {
            return user;
        } else {
            throw new DataViolationException(1,"");
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

}
