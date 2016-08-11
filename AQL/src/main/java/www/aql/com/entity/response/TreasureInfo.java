package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Jason on 2016/7/27.
 */
public class TreasureInfo implements Parcelable {

    public String prizename;
    public String nper;
    public int gettreasurestate;
    public String rulesurl;
    public String detailsurl;
    public String neednumber;
    public String starttime;
    public String endtime;
    public String luckynumber;
    public int joinusernumber;
    public List<String> gettreasurebanners;
    public List<Wininfo> wininfo;

    @Override
    public String toString() {
        return "TreasureInfo{" +
                "prizename='" + prizename + '\'' +
                ", nper='" + nper + '\'' +
                ", gettreasurestate=" + gettreasurestate +
                ", rulesurl='" + rulesurl + '\'' +
                ", detailsurl='" + detailsurl + '\'' +
                ", neednumber='" + neednumber + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", luckynumber='" + luckynumber + '\'' +
                ", joinusernumber=" + joinusernumber +
                ", gettreasurebanners=" + gettreasurebanners +
                ", wininfo=" + wininfo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prizename);
        dest.writeString(this.nper);
        dest.writeInt(this.gettreasurestate);
        dest.writeString(this.rulesurl);
        dest.writeString(this.detailsurl);
        dest.writeString(this.neednumber);
        dest.writeString(this.starttime);
        dest.writeString(this.endtime);
        dest.writeString(this.luckynumber);
        dest.writeInt(this.joinusernumber);
        dest.writeStringList(this.gettreasurebanners);
        dest.writeTypedList(this.wininfo);
    }

    public TreasureInfo() {
    }

    protected TreasureInfo(Parcel in) {
        this.prizename = in.readString();
        this.nper = in.readString();
        this.gettreasurestate = in.readInt();
        this.rulesurl = in.readString();
        this.detailsurl = in.readString();
        this.neednumber = in.readString();
        this.starttime = in.readString();
        this.endtime = in.readString();
        this.luckynumber = in.readString();
        this.joinusernumber = in.readInt();
        this.gettreasurebanners = in.createStringArrayList();
        this.wininfo = in.createTypedArrayList(Wininfo.CREATOR);
    }

    public static final Parcelable.Creator<TreasureInfo> CREATOR = new Parcelable.Creator<TreasureInfo>() {
        @Override
        public TreasureInfo createFromParcel(Parcel source) {
            return new TreasureInfo(source);
        }

        @Override
        public TreasureInfo[] newArray(int size) {
            return new TreasureInfo[size];
        }
    };
}
