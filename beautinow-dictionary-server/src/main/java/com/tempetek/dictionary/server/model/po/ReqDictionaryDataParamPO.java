package com.tempetek.dictionary.server.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqDictionaryDataParamPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("数据字典大项编码")
    private String pcode;
    @ApiModelProperty("数据字典子项编码")
    private String code;
    @ApiModelProperty("数据字典子项名称")
    private String name;

}
