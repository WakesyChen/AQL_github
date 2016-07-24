package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/17.
 */
public class ColumnInfo implements Parcelable {

    public long columnid;
    public long pcolumnid;
    public String image;
    public String columnname;
    public long sort;

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "columnid=" + columnid +
                ", pcolumnid=" + pcolumnid +
                ", image=" + image +
                ", columnname='" + columnname + '\'' +
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
        dest.writeLong(this.pcolumnid);
        dest.writeString(this.image);
        dest.writeString(this.columnname);
        dest.writeLong(this.sort);
    }

    public ColumnInfo() {
    }

    protected ColumnInfo(Parcel in) {
        this.columnid = in.readLong();
        this.pcolumnid = in.readLong();
        this.image = in.readString();
        this.columnname = in.readString();
        this.sort = in.readLong();
    }

    public static final Parcelable.Creator<ColumnInfo> CREATOR = new Parcelable.Creator<ColumnInfo>() {
        @Override
        public ColumnInfo createFromParcel(Parcel source) {
            return new ColumnInfo(source);
        }

        @Override
        public ColumnInfo[] newArray(int size) {
            return new ColumnInfo[size];
        }
    };
}
