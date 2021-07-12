package com.tempetek.dictionary.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tempetek.dictionary.server.entity.DscDictionaryData;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDataDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDataListDTO;
import com.tempetek.dictionary.server.model.po.ReqDictionaryDataParamPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DscDictionaryDataMapper extends BaseMapper<DscDictionaryData> {

    Page<ResDictionaryDataDTO> findBypager(Page page, @Param("po") ReqDictionaryDataParamPO reqDictionaryDataParamPO);

    List<ResDictionaryDataListDTO> findByPcode(@Param("pcode") String pcode);

    String getItem(@Param("code") String code, @Param("pcode") String pcode);

}
