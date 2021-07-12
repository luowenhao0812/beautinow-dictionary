package com.tempetek.dictionary.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tempetek.dictionary.server.entity.DscDictionary;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryAddDTO;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryParamDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDetailDTO;

public interface IDscDictionaryService extends IService<DscDictionary> {

    Page<ResDictionaryDTO> findByPager(ReqDictionaryParamDTO reqDictionaryParamDTO);

    String add(ReqDictionaryAddDTO reqDictionaryAddDTO);

    ResDictionaryDetailDTO detail(String id);

    String edit(ReqDictionaryAddDTO reqDictionaryAddDTO);

    String delete(String id);

}
