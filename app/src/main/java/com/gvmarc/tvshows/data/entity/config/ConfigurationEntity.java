package com.gvmarc.tvshows.data.entity.config;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ConfigurationEntity implements Serializable{

    @SerializedName("images")
    private ImagesConfigEntity images;

    @SerializedName("change_keys")
    private List<String> changeKeys = null;

    public ImagesConfigEntity getImages() {
        return images;
    }

    public void setImages(ImagesConfigEntity images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
