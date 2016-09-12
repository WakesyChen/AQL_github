package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/17.
 */
public class Banner implements Parcelable {
    public long columnid;
    public String image;
    public String url;
    public long sort;

    @Override
    public String toString() {
        return "Banner{" +
                "columnid=" + columnid +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.columnid);
        dest.writeString(this.image);
        dest.writeString(this.url);
        dest.writeLong(this.sort);
    }

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.columnid = in.readLong();
        this.image = in.readString();
        this.url = in.readString();
        this.sort = in.readLong();
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };
}
