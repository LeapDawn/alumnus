package zqh.model;

import lombok.ToString;

@ToString
public class Account {
    private String accounts;

    private String password;

    private Integer alumnus;

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts == null ? null : accounts.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAlumnus() {
        return alumnus;
    }

    public void setAlumnus(Integer alumnus) {
        this.alumnus = alumnus;
    }
}