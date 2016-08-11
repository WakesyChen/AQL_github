package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/27.
 */
public class Wininfo implements Parcelable {

    private User user;

    private String winid;
    private String routeid;
    private String nper;
    private String prizename;
    private String routename;
    private String luckynumber;
    private int joinusernumber;
    private String wintime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.winid);
        dest.writeString(this.routeid);
        dest.writeString(this.nper);
        dest.writeString(this.prizename);
        dest.writeString(this.routename);
        dest.writeString(this.luckynumber);
        dest.writeInt(this.joinusernumber);
        dest.writeString(this.wintime);
    }

    public Wininfo() {
    }

    protected Wininfo(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.winid = in.readString();
        this.routeid = in.readString();
        this.nper = in.readString();
        this.prizename = in.readString();
        this.routename = in.readString();
        this.luckynumber = in.readString();
        this.joinusernumber = in.readInt();
        this.wintime = in.readString();
    }

    public static final Parcelable.Creator<Wininfo> CREATOR = new Parcelable.Creator<Wininfo>() {
        @Override
        public Wininfo createFromParcel(Parcel source) {
            return new Wininfo(source);
        }

        @Override
        public Wininfo[] newArray(int size) {
            return new Wininfo[size];
        }
    };

    @Override
    public String toString() {
        return "Wininfo{" +
                "user=" + user +
                ", winid='" + winid + '\'' +
                ", routeid='" + routeid + '\'' +
                ", nper='" + nper + '\'' +
                ", prizename='" + prizename + '\'' +
                ", routename='" + routename + '\'' +
                ", luckynumber='" + luckynumber + '\'' +
                ", joinusernumber=" + joinusernumber +
                ", wintime='" + wintime + '\'' +
                '}';
    }
}
