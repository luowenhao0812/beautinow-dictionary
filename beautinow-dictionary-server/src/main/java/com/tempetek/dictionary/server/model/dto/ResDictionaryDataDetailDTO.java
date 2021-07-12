package com.tempetek.dictionary.server.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDictionaryDataDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("数据字典大项编码")
    private String pcode;
    @ApiModelProperty("数据字典大项名称")
    private String pname;
    @ApiModelProperty("状态")
    private Boolean status;

}
