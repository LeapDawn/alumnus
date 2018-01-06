package zqh.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import zqh.model.Alumnus;
import zqh.service.AlumnusService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-*"})
public class AlumnusServiceImplTest {

    @Autowired
    private AlumnusService alumnusService;

    @Test
    public void save() throws Exception {
        Alumnus alumnus = new Alumnus();
        alumnus.setStatus(0);
        alumnus.setName("abc");
        alumnus.setBirthday("2016-01-01");
        alumnus.setCollege("def");
        alumnus.setEnrolDate("2016-08-01");
        alumnus.setIdCard("13247978987864531");
        alumnus.setGraduationDate("2016-09-01");
        alumnus.setMajor("专业");
        alumnus.setStudentno("201630370465");
        alumnus.setNation("汉");
        alumnus.setNativePlace("广州");
        alumnus.setSpecial(false);
        alumnusService.save(alumnus);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void list() throws Exception {
    }

    @Test
    public void importExcel() throws Exception {
    }

}