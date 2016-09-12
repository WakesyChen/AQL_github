package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/8/22.
 */
public class UserInfo implements Parcelable {

    /**
     * userid : cf408e719b224f9aa89d3a26d35f6133
     * userphone : 13207129329
     * nickname : hoho
     * usertype : 0
     * thirdopenid :
     * thirdusername :
     * isenterpriseuser : 0
     * routereviewnum : 3
     * routelikenum : 0
     * fansnumber : 0
     * regtime : 2016-06-25 11:30:18
     */

    public String userid;
    public String userphone;
    public String nickname;
    public int usertype;
    public String thirdopenid;
    public String thirdusername;
    public int isenterpriseuser;
    public int routereviewnum;
    public int routelikenum;
    public int fansnumber;
    public String regtime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userid);
        dest.writeString(this.userphone);
        dest.writeString(this.nickname);
        dest.writeInt(this.usertype);
        dest.writeString(this.thirdopenid);
        dest.writeString(this.thirdusername);
        dest.writeInt(this.isenterpriseuser);
        dest.writeInt(this.routereviewnum);
        dest.writeInt(this.routelikenum);
        dest.writeInt(this.fansnumber);
        dest.writeString(this.regtime);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.userid = in.readString();
        this.userphone = in.readString();
        this.nickname = in.readString();
        this.usertype = in.readInt();
        this.thirdopenid = in.readString();
        this.thirdusername = in.readString();
        this.isenterpriseuser = in.readInt();
        this.routereviewnum = in.readInt();
        this.routelikenum = in.readInt();
        this.fansnumber = in.readInt();
        this.regtime = in.readString();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", userphone='" + userphone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", usertype=" + usertype +
                ", thirdopenid='" + thirdopenid + '\'' +
                ", thirdusername='" + thirdusername + '\'' +
                ", isenterpriseuser=" + isenterpriseuser +
                ", routereviewnum=" + routereviewnum +
                ", routelikenum=" + routelikenum +
                ", fansnumber=" + fansnumber +
                ", regtime='" + regtime + '\'' +
                '}';
    }
}
