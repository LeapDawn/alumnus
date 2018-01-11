package zqh.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ClazzAlumnusApply {
    private Integer id;

    private String clazz;

    private Integer alumnus;

    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd")
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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
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