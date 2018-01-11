package zqh.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChapterApply {
    private Integer id;

    private Integer alumnus;

    private String name;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlumnus() {
        return alumnus;
    }

    public void setAlumnus(Integer alumnus) {
        this.alumnus = alumnus;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}