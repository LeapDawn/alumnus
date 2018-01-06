package zqh.dao;

import zqh.model.Account;

public interface AccountDAO {

    int insert(Account record);

    Account selectOne(String account);

    int updatePassword(Account record);

    int updateAlumnus(Account record);
}