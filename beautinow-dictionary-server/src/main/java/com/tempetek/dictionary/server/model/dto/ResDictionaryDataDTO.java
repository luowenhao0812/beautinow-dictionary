package com.tempetek.dictionary.server.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResDictionaryDataDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("数据字典大项编码")
    private String pcode;
    @ApiModelProperty("数据字典大项名称")
    private String pname;
    @ApiModelProperty("数据字典子项编码")
    private String code;
    @ApiModelProperty("数据字典子项名称")
    private String name;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    private Long createTime;
    @ApiModelProperty("状态")
    private Boolean status;

}
