package com.cunw.cloud.familydesk.business.controller;

import com.cunw.cloud.familydesk.common.vo.StudentDiaryVO;
import com.cunw.cloud.familydesk.filemanage.service.IBusiUploadFileInfoService;
import com.cunw.cloud.familydesk.json.annotation.JSON;
import com.cunw.cloud.framework.controller.BaseRESTfulController;
import com.cunw.cloud.framework.controller.dto.RESTfulResult;
import com.cunw.cloud.framework.exception.ParameterException;
import com.cunw.cloud.familydesk.common.model.BusiStudentDiary;
import com.cunw.cloud.familydesk.business.service.IBusiStudentDiaryService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * BusiStudentDiary RESTful接口：
 * @author generator
 * @version 1.0-SNAPSHORT 2019-01-19 10:32
 * 类描述
 * 修订历史：
 * 日期：2019-01-19
 * 作者：generator
 * 参考：
 * 描述：BusiStudentDiary RESTful接口
 * @see null
 * 湖南新云网科技有限公司版权所有.
 */
@RestController
@RequestMapping("/${api.version}/busiStudentDiarys")
public class BusiStudentDiaryController extends BaseRESTfulController {

    @Autowired
    IBusiStudentDiaryService busiStudentDiaryService;

    @Autowired
    IBusiUploadFileInfoService busiUploadFileInfoService;

    @ApiOperation(value="已答试卷知识点分析", notes="已答试卷知识点分析")
    @PostMapping(value = "/questTypeAndCount")
    public RESTfulResult questTypeAndCount(@ModelAttribute BusiStudentDiary model){
        BusiStudentDiary busiStudentDiary = busiStudentDiaryService.add(model);
        return success(busiStudentDiary);
    }

    @ApiOperation(value="新增日记", notes="新增日记")
    @PostMapping(value = "/insertStudentDiary")
    public RESTfulResult addDiary(@ModelAttribute BusiStudentDiary model,String fileIds){
        if (model == null || model.getDiaryStudentId() == null || model.getDiaryStudentId().trim().equals("")
                || model.getDiaryTitle() == null || model.getDiaryTitle().trim().equals("")
                || model.getDiaryText() == null || model.getDiaryText().trim().equals("")
        ){
            throw new ParameterException("日记信息不全");
        }
        busiStudentDiaryService.insertStudentDiary(model,fileIds);
        return success();
    }

    @ApiOperation(value="查询日记详情", notes="查询日记详情")
    @GetMapping(value = "/getDiaryInfo/{diaryId}")
    @JSON(type = StudentDiaryVO.class,filter = "fileUrl")
    public RESTfulResult getDiaryInfo(@PathVariable String diaryId){
        return success(busiStudentDiaryService.getDiaryInfo(diaryId));
    }

    @ApiOperation(value="查询学生日记列表", notes="查询学生日记列表")
    @GetMapping(value = "/getStudentDiaryList")
    @JSON(type = BusiStudentDiary.class,include = "crtDate,diaryMood,diaryTitle,diaryText,diaryId")
    public RESTfulResult getStudentDiaryList(@RequestParam("diaryStudentId") String diaryStudentId, Integer pageNum, Integer pageSize){
        if (StringUtils.isEmpty(diaryStudentId)) {
            throw new ParameterException("学生ID不能为空");
        }
        return success(busiStudentDiaryService.getStudentDiaryList(diaryStudentId, pageNum, pageSize));
    }

}
