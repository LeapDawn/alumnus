package zqh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import zqh.commons.excelModel.AlumnusExcel;
import zqh.commons.exception.ExcelException;
import zqh.commons.util.ExcelUtil;
import zqh.dao.AlumnusDAO;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.dto.UploadResultModel;
import zqh.model.Alumnus;
import zqh.service.AlumnusService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("alumnusService")
public class AlumnusServiceImpl implements AlumnusService {

    @Autowired
    private AlumnusDAO alumnusDAO;

    @Override
    public void save(Alumnus alumnus) {
        // 已毕业
        alumnus.setStatus(0);
        alumnusDAO.insert(alumnus);
    }

    @Override
    public void update(Alumnus alumnus) {
        alumnusDAO.update(alumnus);
    }

    @Override
    public void updateInvalid(Integer alumnusId, Integer invalid) {
        alumnusDAO.updateInvalid(alumnusId, invalid);
    }

    @Override
    public int delete(int id) {
        return alumnusDAO.delete(id);
    }

    @Override
    public Integer selectOneByNameAndCard(Alumnus alumnus) {
        return alumnusDAO.selectOneByNameAndCard(alumnus);
    }

    @Override
    public Alumnus selectOne(Integer id) {
        return alumnusDAO.selectById(id);
    }

    @Override
    public ResultModel<Alumnus> listValid(RequestList<Alumnus> rl) {
        Alumnus key = rl.getKey();
        int total = alumnusDAO.countValid(key);
        ResultModel<Alumnus> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<Alumnus> list = alumnusDAO.selectValidList(key, (resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public ResultModel<Alumnus> listInvalid(RequestList rl) {
        int total = alumnusDAO.countInvalid();
        ResultModel<Alumnus> resultModel = new ResultModel<>(total, rl.getRows(), rl.getPage());
        List<Alumnus> list = alumnusDAO.selectInvalidList((resultModel.getCurrentPage() - 1) * resultModel.getRows(), resultModel.getRows());
        resultModel.setData(list);
        return resultModel;
    }

    @Override
    public UploadResultModel importExcel(InputStream input, boolean special) throws ExcelException {
        ExcelUtil excelUtil = new ExcelUtil();
        List<Map<String, Object>> maps = excelUtil.readExcel(input, AlumnusExcel.getImportColumns());
        List<Alumnus> errorList = new ArrayList<>();
        Alumnus alumnus = null;
        for (Map<String, Object> map : maps) {
            alumnus = new Alumnus();
            alumnus.setBirthday((String) map.get(AlumnusExcel.BIRTHDAY));
            alumnus.setCollege((String) map.get(AlumnusExcel.COLLEGE));
            alumnus.setEnrolDate((String) map.get(AlumnusExcel.ENROL_DATE));
            alumnus.setGraduationDate((String) map.get(AlumnusExcel.GRADUATION_DATE));
            alumnus.setIdCard((String) map.get(AlumnusExcel.ID_CARD));
            alumnus.setMajor((String) map.get(AlumnusExcel.MAJOR));
            alumnus.setName((String) map.get(AlumnusExcel.NAME));
            alumnus.setNation((String) map.get(AlumnusExcel.NATION));
            alumnus.setNativePlace((String) map.get(AlumnusExcel.NATIVE_PLACE));
            alumnus.setPoliticalStatus((String) map.get(AlumnusExcel.POLITICAL_STATUS));
            int code = AlumnusExcel.getStatusCode((String) map.get(AlumnusExcel.STATUS));
            alumnus.setSpecial(special);
            if (code == -1) {
                errorList.add(alumnus);
                continue;
            }
            alumnus.setStatus(code);
            this.save(alumnus);
        }
        UploadResultModel resultModel = new UploadResultModel();
        resultModel.setData(errorList);
        resultModel.setTotal(maps.size());
        resultModel.setError(errorList.size());
        resultModel.setSuccess(resultModel.getTotal() - resultModel.getError());
        return resultModel;
    }
}
