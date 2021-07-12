package com.tempetek.dictionary.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tempetek.dictionary.server.model.dto.*;
import com.tempetek.dictionary.server.service.IDscDictionaryDataService;
import com.tempetek.maviki.web.AjaxMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dsc/dictionary/data")
@Api(tags = "数据字典子项管理")
public class DscDictionaryDataController {

    @Autowired
    private IDscDictionaryDataService dscDictionaryDataService;

    @ApiOperation(value = "获取数据字典子项分页列表")
    @PostMapping(value = "/findByPager")
    public AjaxMessage findByPager(@RequestBody ReqDictionaryDataParamDTO reqDictionaryDataParamDTO) {
        Page<ResDictionaryDataDTO> page = dscDictionaryDataService.findByPager(reqDictionaryDataParamDTO);
        return AjaxMessage.success(page);
    }

    @ApiOperation(value = "新增数据字典子项信息")
    @PostMapping(value = "/add")
    public AjaxMessage add(@RequestBody ReqDictionaryDataAddDTO reqDictionaryDataAddDTO) {
        String result = dscDictionaryDataService.add(reqDictionaryDataAddDTO);
        return AjaxMessage.success(result);
    }

    @ApiOperation(value = "根据ID获取数据字典子项信息")
    @GetMapping(value = "/detail")
    public AjaxMessage detail(@RequestParam String id) {
        ResDictionaryDataDetailDTO resDictionaryDataDetailDTO = dscDictionaryDataService.detail(id);
        return AjaxMessage.success(resDictionaryDataDetailDTO);
    }

    @ApiOperation(value = "修改数据字典子项信息")
    @PostMapping(value = "/edit")
    public AjaxMessage edit(@RequestBody ReqDictionaryDataAddDTO reqDictionaryDataAddDTO) {
        String result = dscDictionaryDataService.edit(reqDictionaryDataAddDTO);
        return AjaxMessage.success(result);
    }

    @ApiOperation(value = "删除数据字典子项信息")
    @GetMapping(value = "/delete")
    public AjaxMessage delete(@RequestParam String id) {
        String result = dscDictionaryDataService.delete(id);
        return AjaxMessage.success(result);
    }

    @ApiOperation(value = "根据数据字典大项编码获取子项信息列表")
    @GetMapping("/findByPcode")
    public AjaxMessage findByPcode(@RequestParam String pcode) {
        List<ResDictionaryDataListDTO> resDictionaryDataListDTOList = dscDictionaryDataService.findByPcode(pcode);
        return AjaxMessage.success(resDictionaryDataListDTOList);
    }

}
