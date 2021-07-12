package com.tempetek.dictionary.server.controller;

import com.tempetek.dictionary.server.service.IDscDictionaryDataService;
import com.tempetek.maviki.web.AjaxMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "提供其他服务调用的数据字典feign接口")
@RestController
@RequestMapping(value = "/feign/dictionary")
public class FeignController {

    @Autowired
    private IDscDictionaryDataService dscDictionaryDataService;

    @GetMapping(value = "/data/getItem")
    public AjaxMessage getItem(@RequestParam String code, @RequestParam String pcode) {
        String name = dscDictionaryDataService.getItem(code, pcode);
        return AjaxMessage.success(name);
    }
}
