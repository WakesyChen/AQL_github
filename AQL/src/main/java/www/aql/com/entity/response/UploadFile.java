package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/8/8.
 */
public class UploadFile implements Parcelable {

    /**
     * fileid : AE88067C-7F46-4536-BE84-509504B76A2B
     * filename : topBg.jpg
     * filepath : upload/201606/AE88067C-7F46-4536-BE84-509504B76A2B.jpg
     * filesize : 11004
     */

    public String fileid;
    public String filename;
    public String filepath;
    public String filesize;

    @Override
    public String toString() {
        return "UploadFile{" +
                "fileid='" + fileid + '\'' +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filesize='" + filesize + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileid);
        dest.writeString(this.filename);
        dest.writeString(this.filepath);
        dest.writeString(this.filesize);
    }

    public UploadFile() {
    }

    protected UploadFile(Parcel in) {
        this.fileid = in.readString();
        this.filename = in.readString();
        this.filepath = in.readString();
        this.filesize = in.readString();
    }

    public static final Creator<UploadFile> CREATOR = new Creator<UploadFile>() {
        @Override
        public UploadFile createFromParcel(Parcel source) {
            return new UploadFile(source);
        }

        @Override
        public UploadFile[] newArray(int size) {
            return new UploadFile[size];
        }
    };
}
