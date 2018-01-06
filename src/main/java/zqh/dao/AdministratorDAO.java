package zqh.dao;

import zqh.model.Administrator;

public interface AdministratorDAO {
    int insert(Administrator record);

    Administrator selectOne(String account);

    int update(Administrator record);
}