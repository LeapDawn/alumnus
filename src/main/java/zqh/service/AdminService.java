package zqh.service;

import zqh.model.Administrator;

/**
 * 管理员业务相关信息
 */
public interface AdminService {

    /**
     * 新增管理员账号信息
     * @param administrator
     */
    void save(Administrator administrator);


    /**
     * 更新管理员账号信息
     * @param administrator
     */
    void update(Administrator administrator);

    /**
     * 管理员登陆
     * @param administrator
     * @return
     */
    Administrator login(Administrator administrator);

}
