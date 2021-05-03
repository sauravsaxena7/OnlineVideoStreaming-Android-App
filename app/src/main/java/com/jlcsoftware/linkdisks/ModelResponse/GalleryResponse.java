package com.jlcsoftware.linkdisks.ModelResponse;

import java.io.File;

public class GalleryResponse {

    File file;

    public GalleryResponse(File file) {
        this.file = file;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
