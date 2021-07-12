package com.tempetek.dictionary.client.client;

import com.tempetek.maviki.web.AjaxMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "beautinow-dictionary-server")
public interface DictionaryFeignClient {

    @RequestMapping(value = "/feign/dictionary/data/getItem", method = RequestMethod.GET)
    public AjaxMessage getItem(@RequestParam String code, @RequestParam String pcode);

}
