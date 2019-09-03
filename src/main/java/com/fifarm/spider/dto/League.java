package com.fifarm.spider.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fifarm.spider.dto.deserializer.LeagueDeserializer;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonDeserialize(using = LeagueDeserializer.class)
public class League {

    @Id
    private Long id;

    private String name;
    private String abbrName;
    private String imgUrl;
    private String imageUrlsDark;
    private String imageUrlsLight;

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

    public void setImgUrl(String imageUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImageUrlsDark() {
        return imageUrlsDark;
    }

    public void setImageUrlsDark(String imageUrlsDark) {
        this.imageUrlsDark = imageUrlsDark;
    }

    public String getImageUrlsLight() {
        return imageUrlsLight;
    }

    public void setImageUrlsLight(String imageUrlsLight) {
        this.imageUrlsLight = imageUrlsLight;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbrName='" + abbrName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imageUrlsDark='" + imageUrlsDark + '\'' +
                ", imageUrlsLight='" + imageUrlsLight + '\'' +
                '}';
    }

}
