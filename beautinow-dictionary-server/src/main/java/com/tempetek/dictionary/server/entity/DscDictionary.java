package com.tempetek.dictionary.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@ApiModel(value = "数据字典表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dsc_dictionary")
public class DscDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @ApiModelProperty("编号")
    private String code;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("修改人")
    private String modifier;
    @ApiModelProperty("修改时间")
    private Long modifiedTime;
    @ApiModelProperty("状态")
    private Boolean status;
    @ApiModelProperty("删除状态")
    private Boolean whetherDeleted;

}
