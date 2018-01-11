package zqh.service;

import zqh.model.Account;
import zqh.model.Alumnus;

/** 校友账号业务接口*/
public interface AccountService{

    /**
     * 新增校友账号信息
     * @param account
     */
    void save(Account account);

    /**
     * 更新校友账号密码
     * @param account
     */
    void updatePassword(Account account);

    /**
     * 校友登陆
     * @param account 校友账密实体
     * @return 已经登陆的校友信息
     */
    Account login(Account account);

    /**
     *  验证校友信心并绑定账号（需要调用this.updateAlumnus）
     * @param account
     * @param alumnus
     */
    void bind(String account, Alumnus alumnus);

    /**
     * 为账号绑定校友信息
     * @param account
     */
    void updateAlumnus(Account account);
}
