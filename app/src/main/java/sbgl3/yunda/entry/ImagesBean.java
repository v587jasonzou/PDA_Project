package sbgl3.yunda.entry;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ImagesBean implements Serializable {
    private String imagesName;
    private String imagesPath;
    private String ImagesLocalPath;
//    private Bitmap imageBitmap;


    public String getImagesName() {
        return imagesName;
    }

    public void setImagesName(String imagesName) {
        this.imagesName = imagesName;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getImagesLocalPath() {
        return ImagesLocalPath;
    }

    public void setImagesLocalPath(String imagesLocalPath) {
        ImagesLocalPath = imagesLocalPath;
    }
}
