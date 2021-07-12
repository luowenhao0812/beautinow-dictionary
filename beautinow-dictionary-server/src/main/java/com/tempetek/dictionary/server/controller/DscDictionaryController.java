package com.tempetek.dictionary.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryAddDTO;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryParamDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDetailDTO;
import com.tempetek.dictionary.server.service.IDscDictionaryService;
import com.tempetek.maviki.web.AjaxMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dsc/dictionary")
@Api(tags = "数据字典项管理")
public class DscDictionaryController {

    @Autowired
    private IDscDictionaryService dscDictionaryService;

    @ApiOperation(value = "数据字典项分页列表")
    @PostMapping(value = "/findByPager")
    public AjaxMessage findByPager(@RequestBody ReqDictionaryParamDTO reqDictionaryParamDTO) {
        Page<ResDictionaryDTO> page = dscDictionaryService.findByPager(reqDictionaryParamDTO);
        return AjaxMessage.success(page);
    }

    @ApiOperation(value = "新增数据字典项")
    @PostMapping(value = "/add")
    public AjaxMessage add(@RequestBody ReqDictionaryAddDTO reqDictionaryAddDTO) {
        String result = dscDictionaryService.add(reqDictionaryAddDTO);
        return AjaxMessage.success(result);
    }

    @ApiOperation(value = "根据ID获取数据字典项详情信息")
    @GetMapping(value = "/detail")
    public AjaxMessage detail(@RequestParam String id) {
        ResDictionaryDetailDTO resDictionaryDetailDTO = dscDictionaryService.detail(id);
        return AjaxMessage.success(resDictionaryDetailDTO);
    }

    @ApiOperation(value = "修改数据字典项信息")
    @PostMapping(value = "/edit")
    public AjaxMessage edit(@RequestBody ReqDictionaryAddDTO reqDictionaryAddDTO) {
        String result = dscDictionaryService.edit(reqDictionaryAddDTO);
        return AjaxMessage.success(result);
    }

    @ApiOperation(value = "删除数据字典项信息")
    @GetMapping(value = "/delete")
    public AjaxMessage delete(@RequestParam String id) {
        String result = dscDictionaryService.delete(id);
        return AjaxMessage.success(id);
    }

}
