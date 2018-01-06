package zqh.model;

import java.util.Date;

public class Chapter {
    private String name;

    private String content;

    private Date addtime;

    private Integer adder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getAdder() {
        return adder;
    }

    public void setAdder(Integer adder) {
        this.adder = adder;
    }
}