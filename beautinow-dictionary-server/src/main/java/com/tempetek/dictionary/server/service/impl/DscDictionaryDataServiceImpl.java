package com.tempetek.dictionary.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tempetek.dictionary.server.entity.DscDictionary;
import com.tempetek.dictionary.server.entity.DscDictionaryData;
import com.tempetek.dictionary.server.exception.BeautinowException;
import com.tempetek.dictionary.server.mapper.DscDictionaryDataMapper;
import com.tempetek.dictionary.server.model.dto.*;
import com.tempetek.dictionary.server.model.po.ReqDictionaryDataParamPO;
import com.tempetek.dictionary.server.service.IDscDictionaryDataService;
import com.tempetek.dictionary.server.service.IDscDictionaryService;
import com.tempetek.maviki.enums.MessageEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DscDictionaryDataServiceImpl extends ServiceImpl<DscDictionaryDataMapper, DscDictionaryData> implements IDscDictionaryDataService {

    @Autowired
    private IDscDictionaryService dscDictionaryService;


    @Override
    public Page<ResDictionaryDataDTO> findByPager(ReqDictionaryDataParamDTO reqDictionaryDataParamDTO) {
        try {
            if (reqDictionaryDataParamDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "分页查询条件不能为空!");
            }

            Page page = new Page(reqDictionaryDataParamDTO.getPageNo(), reqDictionaryDataParamDTO.getPageSize());
            ReqDictionaryDataParamPO reqDictionaryDataParamPO = new ReqDictionaryDataParamPO();
            BeanUtils.copyProperties(reqDictionaryDataParamDTO, reqDictionaryDataParamPO);
            Page<ResDictionaryDataDTO> resDictionaryDataDTOPage = baseMapper.findBypager(page, reqDictionaryDataParamPO);
            return resDictionaryDataDTOPage;
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "获取数据字典子项分页列表失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "获取数据字典子项分页列表失败!");
        }
    }

    @Override
    public String add(ReqDictionaryDataAddDTO reqDictionaryDataAddDTO) {
        try {
            if (reqDictionaryDataAddDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "新增数据字典子项信息不能为空!");
            }

            DscDictionaryData dscDictionaryData = baseMapper.selectOne(new QueryWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getDictionaryId, reqDictionaryDataAddDTO.getDictionaryId())
                .eq(DscDictionaryData::getCode, reqDictionaryDataAddDTO.getCode()));

            if (dscDictionaryData != null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "已存在该编码的数据字典大项的子项信息!");
            }

            dscDictionaryData = new DscDictionaryData();
            BeanUtils.copyProperties(reqDictionaryDataAddDTO, dscDictionaryData);
            dscDictionaryData.setCreator("admin");
            dscDictionaryData.setCreateTime(System.currentTimeMillis());
            dscDictionaryData.setModifier("admin");
            dscDictionaryData.setModifiedTime(System.currentTimeMillis());
            baseMapper.insert(dscDictionaryData);
            return "新增数据字典子项信息成功!";
        } catch (Exception e) {
            log.error(e.getMessage() + "新增数据字典子项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "新增数据字典子项信息失败!");
        }
    }

    @Override
    public ResDictionaryDataDetailDTO detail(String id) {
        try {
            if (StringUtils.isBlank(id)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项ID不能为空!");
            }

            DscDictionaryData dscDictionaryData = baseMapper.selectOne(new QueryWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getId, id));

            if (dscDictionaryData == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项信息不存在!");
            }

            ResDictionaryDataDetailDTO resDictionaryDataDetailDTO = new ResDictionaryDataDetailDTO();
            BeanUtils.copyProperties(dscDictionaryData, resDictionaryDataDetailDTO);
            DscDictionary dscDictionary = dscDictionaryService.getOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, dscDictionaryData.getDictionaryId()));

            if (dscDictionary != null) {
                resDictionaryDataDetailDTO.setPcode(dscDictionary.getCode());
                resDictionaryDataDetailDTO.setPname(dscDictionary.getName());
            }
            return resDictionaryDataDetailDTO;
        } catch (Exception e) {
            log.error(e.getMessage() + "根据ID获取数据字典子项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "根据ID获取数据字典子项信息失败!");
        }
    }

    @Override
    public String edit(ReqDictionaryDataAddDTO reqDictionaryDataAddDTO) {
        try {
            if (reqDictionaryDataAddDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "修改数据字典子项信息不能为空!");
            }

            DscDictionaryData dscDictionaryData = baseMapper.selectOne(new QueryWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getDictionaryId, reqDictionaryDataAddDTO.getDictionaryId())
                .eq(DscDictionaryData::getCode, reqDictionaryDataAddDTO.getCode()));

            if (dscDictionaryData != null && !dscDictionaryData.getId().equals(reqDictionaryDataAddDTO.getId())) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "该编码的数据字典大项的数据字典子项信息已存在!");
            }

            dscDictionaryData = baseMapper.selectOne(new QueryWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getId, reqDictionaryDataAddDTO.getId()));

            if (dscDictionaryData == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项信息不存在!");
            }

            BeanUtils.copyProperties(reqDictionaryDataAddDTO, dscDictionaryData);
            dscDictionaryData.setModifiedTime(System.currentTimeMillis());
            dscDictionaryData.setModifier("admin");
            baseMapper.update(dscDictionaryData, new UpdateWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getId, dscDictionaryData.getId()));
            return "修改数据字典子项信息成功!";
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "修改数据字典子项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "修改数据字典子项信息失败!");
        }
    }

    @Override
    public String delete(String id) {
        try {
            if (StringUtils.isBlank(id)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项ID不能为空!");
            }

            DscDictionaryData dscDictionaryData = baseMapper.selectOne(new QueryWrapper<DscDictionaryData>().lambda()
                .eq(DscDictionaryData::getId, id));

            if (dscDictionaryData == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项信息不存在!");
            }

            baseMapper.deleteById(id);
            return "根据ID删除数据字典子项信息成功!";
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "根据ID删除数据字典子项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "根据ID删除数据字典子项信息失败!");
        }
    }

    @Override
    public List<ResDictionaryDataListDTO> findByPcode(String pcode) {
        try {
            if (StringUtils.isBlank(pcode)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典大项编码不能为空!");
            }

            List<ResDictionaryDataListDTO> resDictionaryDataListDTOList = baseMapper.findByPcode(pcode);
            return resDictionaryDataListDTOList;
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "根据数据字典大项编码获取子项信息列表失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "根据数据字典大项编码获取子项信息列表失败!");
        }
    }

    @Override
    public String getItem(String code, String pcode) {
        try {
            if (StringUtils.isBlank(code)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典大项编码不能为空!");
            }

            if (StringUtils.isBlank(pcode)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典子项编码不能为空!");
            }

            String name = baseMapper.getItem(code, pcode);
            return name;
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "获取数据字典子项名称失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "获取数据字典子项名称失败!");
        }
    }

}
