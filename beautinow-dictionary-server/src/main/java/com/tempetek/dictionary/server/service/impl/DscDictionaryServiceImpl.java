package com.tempetek.dictionary.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tempetek.dictionary.server.constants.BeautinowContants;
import com.tempetek.dictionary.server.entity.DscDictionary;
import com.tempetek.dictionary.server.exception.BeautinowException;
import com.tempetek.dictionary.server.mapper.DscDictionaryMapper;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryAddDTO;
import com.tempetek.dictionary.server.model.dto.ReqDictionaryParamDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDTO;
import com.tempetek.dictionary.server.model.dto.ResDictionaryDetailDTO;
import com.tempetek.dictionary.server.service.IDscDictionaryService;
import com.tempetek.maviki.enums.MessageEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DscDictionaryServiceImpl extends ServiceImpl<DscDictionaryMapper, DscDictionary> implements IDscDictionaryService {

    @Override
    public Page<ResDictionaryDTO> findByPager(ReqDictionaryParamDTO reqDictionaryParamDTO) {
        try {
            if (reqDictionaryParamDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "分页查询条件不能为空!");
            }

            Page page = new Page(reqDictionaryParamDTO.getPageNo(), reqDictionaryParamDTO.getPageSize());
            Page<DscDictionary> dscDictionaryPage = baseMapper.selectPage(page, new QueryWrapper<DscDictionary>().lambda()
                .like(StringUtils.isNotBlank(reqDictionaryParamDTO.getCode()), DscDictionary::getCode, reqDictionaryParamDTO.getCode())
                .like(StringUtils.isNotBlank(reqDictionaryParamDTO.getName()), DscDictionary::getName, reqDictionaryParamDTO.getName())
                .orderByDesc(DscDictionary::getModifiedTime));
            Page<ResDictionaryDTO> resDictionaryDTOPage = new Page<>();
            List<ResDictionaryDTO> resDictionaryDTOList = new ArrayList<>();

            for (DscDictionary dscDictionary : dscDictionaryPage.getRecords()) {
                ResDictionaryDTO resDictionaryDTO = new ResDictionaryDTO();
                BeanUtils.copyProperties(dscDictionary, resDictionaryDTO);
                resDictionaryDTOList.add(resDictionaryDTO);
            }

            resDictionaryDTOPage.setRecords(resDictionaryDTOList);
            resDictionaryDTOPage.setTotal(dscDictionaryPage.getTotal());
            return resDictionaryDTOPage;
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "数据字典项分页列表获取失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项分页列表获取失败!");
        }
    }

    @Override
    public String add(ReqDictionaryAddDTO reqDictionaryAddDTO) {
        try {
            if (reqDictionaryAddDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "新增数据字典项信息不能为空!");
            }

            DscDictionary dscDictionary = baseMapper.selectOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getCode, reqDictionaryAddDTO.getCode()));

            if (dscDictionary != null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "已存在该编码的数据字典项信息!");
            }

            dscDictionary = new DscDictionary();
            BeanUtils.copyProperties(reqDictionaryAddDTO, dscDictionary);
            dscDictionary.setCreator("admin");
            dscDictionary.setCreateTime(System.currentTimeMillis());
            dscDictionary.setModifier("admin");
            dscDictionary.setModifiedTime(System.currentTimeMillis());
            baseMapper.insert(dscDictionary);
            return "新增数据字典项信息成功!";
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "新增数据字典项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "新增数据字典项信息失败!");
        }
    }

    @Override
    public ResDictionaryDetailDTO detail(String id) {
        try {
            if (StringUtils.isBlank(id)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项ID不能为空!");
            }

            DscDictionary dscDictionary = baseMapper.selectOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, id));

            if (dscDictionary == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项信息不存在!");
            }

            ResDictionaryDetailDTO resDictionaryDetailDTO = new ResDictionaryDetailDTO();
            BeanUtils.copyProperties(dscDictionary, resDictionaryDetailDTO);
            return resDictionaryDetailDTO;
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "根据ID获取数据字典项详情失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "根据ID获取数据字典项详情失败!");
        }
    }

    @Override
    public String edit(ReqDictionaryAddDTO reqDictionaryAddDTO) {
        try {
            if (reqDictionaryAddDTO == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项信息不能为空!");
            }

            if (StringUtils.isBlank(reqDictionaryAddDTO.getCode())) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项编码不能为空!");
            }

            DscDictionary dscDictionary = baseMapper.selectOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getCode, reqDictionaryAddDTO.getCode()));

            if (dscDictionary != null &&  !dscDictionary.getId().equals(reqDictionaryAddDTO.getId()) ) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "已存在此编码的数据字典项!");
            }

            dscDictionary = baseMapper.selectOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, reqDictionaryAddDTO.getId()));

            if (dscDictionary == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项信息不存在!");
            }

            BeanUtils.copyProperties(reqDictionaryAddDTO, dscDictionary);
            dscDictionary.setModifier("admin");
            dscDictionary.setModifiedTime(System.currentTimeMillis());
            baseMapper.update(dscDictionary, new UpdateWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, dscDictionary.getId()));
            return "修改数据字典项信息成功!";
        } catch (Exception e) {
            log.error(e.getMessage() + "修改数据字典项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "修改数据字典项信息失败!");
        }
    }

    @Override
    public String delete(String id) {
        try {
            if (StringUtils.isBlank(id)) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项ID不能为空!");
            }

            DscDictionary dscDictionary = baseMapper.selectOne(new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, id));

            if (dscDictionary == null) {
                throw new BeautinowException(MessageEnum.FAIL.getCode(), "数据字典项信息不存在!");
            }

            dscDictionary.setStatus(BeautinowContants.DELETED);
            dscDictionary.setModifier("admin");
            dscDictionary.setModifiedTime(System.currentTimeMillis());
            baseMapper.update(dscDictionary, new QueryWrapper<DscDictionary>().lambda()
                .eq(DscDictionary::getId, id));
            return "根据ID删除数据字典项信息成功!";
        } catch (BeautinowException e) {
            log.error(e.getMessage());
            throw new BeautinowException(MessageEnum.FAIL.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage() + "根据ID删除数据字典项信息失败!");
            throw new BeautinowException(MessageEnum.FAIL.getCode(), "根据ID删除数据字典项信息失败!");
        }
    }

}
