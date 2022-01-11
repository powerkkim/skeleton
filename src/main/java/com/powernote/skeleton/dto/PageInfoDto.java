package com.powernote.skeleton.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageInfoDto {
    protected String number;
    protected String size;
    protected String direction;
    protected String standard;
    protected String searchKey;
    protected String searchWord;

    public PageInfoDto(String number, String size, String direction, String standard, String searchKey, String search){
        this.number = number;
        this.size = size;
        this.direction = direction;
        this.standard = standard;
        this.searchKey = searchKey;
        this.searchWord = search;
    }

    /** 아무 매개변수 없이 default값을 생성해주는 생성자 */
    public PageInfoDto(){
        this.number = "0";
        this.size = "10";
        this.direction = "";
        this.standard = "";
        this.searchKey = "";
        this.searchWord = "";
    }

    @Override
    public String toString() {
        return "PageInfoDto{" +
                "number='" + number + '\'' +
                ", size='" + size + '\'' +
                ", direction='" + direction + '\'' +
                ", standard='" + standard + '\'' +
                ", searchKey='" + searchKey + '\'' +
                ", searchWord='" + searchWord + '\'' +
                '}';
    }
}
