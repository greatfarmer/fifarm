package com.fifarm.spider.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fifarm.spider.dto.deserializer.NationDeserializer;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonDeserialize(using = NationDeserializer.class)
public class Nation {

    @Id
    private Long id;

    private String name;
    private String abbrName;
    private String imgUrl;
    private String imageUrlsSmall;
    private String imageUrlsMedium;
    private String imageUrlsLarge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImageUrlsSmall() {
        return imageUrlsSmall;
    }

    public void setImageUrlsSmall(String imageUrlsSmall) {
        this.imageUrlsSmall = imageUrlsSmall;
    }

    public String getImageUrlsMedium() {
        return imageUrlsMedium;
    }

    public void setImageUrlsMedium(String imageUrlsMedium) {
        this.imageUrlsMedium = imageUrlsMedium;
    }

    public String getImageUrlsLarge() {
        return imageUrlsLarge;
    }

    public void setImageUrlsLarge(String imageUrlsLarge) {
        this.imageUrlsLarge = imageUrlsLarge;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbrName='" + abbrName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imageUrlsSmall='" + imageUrlsSmall + '\'' +
                ", imageUrlsMedium='" + imageUrlsMedium + '\'' +
                ", imageUrlsLarge='" + imageUrlsLarge + '\'' +
                '}';
    }

}
