package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/27.
 */
public class User implements Parcelable {

    public String userid;
    public String userphone;
    public String nickname;
    public String userheader;

    public String regtime;

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", userphone='" + userphone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userheader='" + userheader + '\'' +
                ", regtime='" + regtime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userid);
        dest.writeString(this.userphone);
        dest.writeString(this.nickname);
        dest.writeString(this.userheader);
        dest.writeString(this.regtime);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userid = in.readString();
        this.userphone = in.readString();
        this.nickname = in.readString();
        this.userheader = in.readString();
        this.regtime = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
