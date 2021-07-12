package com.tempetek.dictionary.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel(value = "数据字典子项数据表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dsc_dictionary_data")
public class DscDictionaryData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("数据字典类型ID")
    private String dictionaryId;
    @ApiModelProperty("数据字典子项值")
    private String value;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("修改人")
    private String modifier;
    @ApiModelProperty("修改时间")
    private Long modifiedTime;

}
