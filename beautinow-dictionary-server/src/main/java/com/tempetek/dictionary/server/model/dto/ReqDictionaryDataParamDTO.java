package com.tempetek.dictionary.server.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqDictionaryDataParamDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页码", example = "1")
    private Integer pageNo;
    @ApiModelProperty(value = "每页条数", example = "10")
    private Integer pageSize;
    @ApiModelProperty(value = "数据字典大项编码")
    private String pcode;
    @ApiModelProperty(value = "数据字典子项编码")
    private String code;
    @ApiModelProperty("数据字典子项名称")
    private String name;

}
