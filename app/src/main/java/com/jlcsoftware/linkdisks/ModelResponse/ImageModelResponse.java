package com.jlcsoftware.linkdisks.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageModelResponse {

    String _id;
    @SerializedName("images")
    @Expose
    public List<Image> images = null;


    public ImageModelResponse(String _id, List<Image> images) {
        this._id = _id;
        this.images = images;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
