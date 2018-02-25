
package com.gvmarc.tvshows.data.entity.details;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreatedBy implements Serializable{

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @Nullable
    @SerializedName("gender")
    private Integer gender;

    @SerializedName("profile_path")
    private String profilePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

}
