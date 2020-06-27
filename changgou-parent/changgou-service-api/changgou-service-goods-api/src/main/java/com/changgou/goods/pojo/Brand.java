package com.changgou.goods.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "Brand", value ="Brand")
@Table(name ="tb_brand")
public class Brand implements Serializable {
    @ApiModelProperty(value = "品牌id",required = false)
    @Id
    private String id;
    @ApiModelProperty(value = "品牌名称",required = false)
    private String name;
    @ApiModelProperty(value = "品牌图片地址",required = false)
    private String image;
    @ApiModelProperty(value = "品牌的首字母",required = false)
    private String letter;
    @ApiModelProperty(value = "排序",required = false)
    private Integer seq;

    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "letter")
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Column(name = "seq")
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
