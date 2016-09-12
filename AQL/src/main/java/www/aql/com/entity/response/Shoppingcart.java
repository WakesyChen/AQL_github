package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/26.
 */
public class Shoppingcart implements Parcelable {


    public String shopingcartid;
    public String routeid;
    public int paycopynum;
    public String createtime;

    public Route routeinfo;
    public User user;
    public Issue userselectissues;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shopingcartid);
        dest.writeString(this.routeid);
        dest.writeInt(this.paycopynum);
        dest.writeString(this.createtime);
        dest.writeParcelable(this.routeinfo, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.userselectissues, flags);
    }

    public Shoppingcart() {
    }

    protected Shoppingcart(Parcel in) {
        this.shopingcartid = in.readString();
        this.routeid = in.readString();
        this.paycopynum = in.readInt();
        this.createtime = in.readString();
        this.routeinfo = in.readParcelable(Route.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
        this.userselectissues = in.readParcelable(Issue.class.getClassLoader());
    }

    public static final Creator<Shoppingcart> CREATOR = new Creator<Shoppingcart>() {
        @Override
        public Shoppingcart createFromParcel(Parcel source) {
            return new Shoppingcart(source);
        }

        @Override
        public Shoppingcart[] newArray(int size) {
            return new Shoppingcart[size];
        }
    };

    @Override
    public String toString() {
        return "Shoppingcart{" +
                "shopingcartid='" + shopingcartid + '\'' +
                ", routeid='" + routeid + '\'' +
                ", paycopynum=" + paycopynum +
                ", createtime='" + createtime + '\'' +
                ", routeinfo=" + routeinfo +
                ", user=" + user +
                ", userselectissues=" + userselectissues +
                '}';
    }
}
