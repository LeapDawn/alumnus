package zqh.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zqh.commons.exception.ExcelException;
import zqh.dto.AjaxResult;
import zqh.dto.RequestList;
import zqh.dto.ResultModel;
import zqh.dto.UploadResultModel;
import zqh.model.Alumnus;
import zqh.service.AlumnusService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/admin/alumnus/sp")
public class SpAlumnusController {

    @Autowired
    private AlumnusService alumnusService;

    @PostMapping("/addition")
    public AjaxResult save(@RequestBody Alumnus alumnus) {
        alumnus.setSpecial(true);
        alumnus.setInvalid(0);
        alumnusService.save(alumnus);
        return AjaxResult.success("新增特殊校友信息成功");
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Alumnus alumnus) {
        alumnus.setSpecial(true);
        alumnusService.update(alumnus);
        return AjaxResult.success("更新特殊校友信息成功");
    }

    @DeleteMapping( "/deletion")
    public AjaxResult delete(@RequestParam("id") int id) {
        int num = alumnusService.delete(id);
        return AjaxResult.success("删除 "+ num +"条特殊校友信息");
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult list(@RequestBody RequestList<Alumnus> requestList) {
        requestList.getKey().setSpecial(true);
        ResultModel<Alumnus> list = alumnusService.listValid(requestList);
        return AjaxResult.success(list);
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public AjaxResult importData(@RequestParam("file") MultipartFile file) throws IOException, ExcelException {
        if (file == null) {
            return AjaxResult.fail(111,"上传的文件为空");
        }
        UploadResultModel resultModel = alumnusService.importExcel(file.getInputStream(), true);
        return AjaxResult.success(resultModel);
    }
}
