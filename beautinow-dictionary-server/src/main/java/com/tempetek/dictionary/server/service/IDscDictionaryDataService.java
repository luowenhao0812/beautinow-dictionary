package com.tempetek.dictionary.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tempetek.dictionary.server.entity.DscDictionaryData;
import com.tempetek.dictionary.server.model.dto.*;

import java.util.List;

public interface IDscDictionaryDataService extends IService<DscDictionaryData> {

    Page<ResDictionaryDataDTO> findByPager(ReqDictionaryDataParamDTO reqDictionaryDataParamDTO);

    String add(ReqDictionaryDataAddDTO reqDictionaryDataAddDTO);

    ResDictionaryDataDetailDTO detail(String id);

    String edit(ReqDictionaryDataAddDTO reqDictionaryDataAddDTO);

    String delete(String id);

    List<ResDictionaryDataListDTO> findByPcode(String pcode);

    String getItem(String code, String pcode);

}
