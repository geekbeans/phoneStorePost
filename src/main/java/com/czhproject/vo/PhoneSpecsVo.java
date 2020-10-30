package com.czhproject.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//此为tree中K和V的数据结构
@Data
public class PhoneSpecsVo {
    @JsonProperty("id")
    private Integer specsId;
    @JsonProperty("name")
    private String specsName;
    @JsonProperty("imgUrl")
    private String specsIcon;
    @JsonProperty("PreviewImgUrl")
    private String specsPreview;
}
