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
@RequestMapping("/admin/alumnus")
public class AlumnusController {

    @Autowired
    private AlumnusService alumnusService;

    @PostMapping("/addition")
//    @RequestMapping(value = "/addition", method = {RequestMethod.POST})
    public AjaxResult save(@RequestBody Alumnus alumnus) {
        alumnus.setSpecial(false);
        alumnus.setInvalid(0);
        alumnusService.save(alumnus);
        return AjaxResult.success("新增校友信息成功");
    }

    @PutMapping("/update/valid")
    public AjaxResult updateValid(@RequestBody Alumnus alumnus){
        alumnusService.updateInvalid(alumnus.getId(),0);
        return AjaxResult.success("该校友信息已确认审核通过");
    }

    @PutMapping("/update/invalid")
    public AjaxResult updateInvalid(@RequestBody Alumnus alumnus){
        alumnusService.updateInvalid(alumnus.getId(),2);
        return AjaxResult.success("该校友信息已确认未审核通过");
    }

    @PutMapping("/update")
    public AjaxResult update(@RequestBody Alumnus alumnus) {
        alumnus.setSpecial(false);
        alumnusService.update(alumnus);
        return AjaxResult.success("更新校友信息成功");
    }

    @DeleteMapping("/deletion")
//    @RequestMapping(value = "/deletion", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult delete(@RequestParam("id") Integer id) {
        int num = alumnusService.delete(id);
        return AjaxResult.success("删除 "+ num +"条校友信息");
    }

    @RequestMapping(value = "/list/valid", method = {RequestMethod.GET, RequestMethod.POST})
//    @PostMapping(value = "/list/valid")
    public AjaxResult listValid(@RequestBody RequestList<Alumnus> requestList) {
        requestList.getKey().setSpecial(false);
        ResultModel<Alumnus> list = alumnusService.listValid(requestList);
        return AjaxResult.success(list);
    }

    @RequestMapping(value = "/list/invalid", method = {RequestMethod.GET, RequestMethod.POST})
    public AjaxResult listInvalid(@RequestBody RequestList requestList){
        ResultModel<Alumnus> list = alumnusService.listInvalid(requestList);
        return AjaxResult.success(list);
    }


    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public AjaxResult importData(@RequestParam("file") MultipartFile file) throws IOException, ExcelException {
        if (file == null) {
            return AjaxResult.fail(111,"上传的文件为空");
        }
        UploadResultModel resultModel = alumnusService.importExcel(file.getInputStream(), false);
        return AjaxResult.success(resultModel);
    }
}
