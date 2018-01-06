package zqh.model;

import java.util.Date;

public class ChapterAlumnusApply {
    private Integer id;

    private String chapter;

    private Integer alumnus;

    private Integer status;

    private Date date;

    private Alumnus alumnusEntity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Alumnus getAlumnusEntity() {
        return alumnusEntity;
    }

    public void setAlumnusEntity(Alumnus alumnusEntity) {
        this.alumnusEntity = alumnusEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public Integer getAlumnus() {
        return alumnus;
    }

    public void setAlumnus(Integer alumnus) {
        this.alumnus = alumnus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}