package sbgl3.yunda.entry;

import java.io.Serializable;

public class ImageUploadResponse implements Serializable {

    /**
     * success : true
     * filePath : F:\EquipmentUpload\e_inspect_record\2018\8\20180828172256701.jpg
     */

    private Boolean success;
    private String filePath;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
