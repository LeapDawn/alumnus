package zqh.model;

public class ChapterAlumnus {
    private Integer id;

    private String chapter;

    private Integer alumnus;

    private Boolean admin;

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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}