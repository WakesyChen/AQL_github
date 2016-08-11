package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/8/8.
 */
public class ApplyForCompanyUser implements Parcelable {
    /**
     * userid : 14497f6f-d75a-4e22-a883-4fa72999165889
     * enterpriseuserid : 14497f6f-d75a-4e22-a883-4fa729165889
     * enterpriseusername :
     * phone :
     * identitycard :
     * managetype :
     * alipayid :
     * address :
     * identitycardimage :
     * manageimage :
     * createtime : 2016-07-1712: 00: 00
     * state : 1
     */

    public String userid;
    public String enterpriseuserid;
    public String enterpriseusername;
    public String phone;
    public String identitycard;
    public String managetype;
    public String alipayid;
    public String address;
    public String identitycardimage;
    public String manageimage;
    public String createtime;
    public int state;


    @Override
    public String toString() {
        return "ApplyForCompanyUser{" +
                "userid='" + userid + '\'' +
                ", enterpriseuserid='" + enterpriseuserid + '\'' +
                ", enterpriseusername='" + enterpriseusername + '\'' +
                ", phone='" + phone + '\'' +
                ", identitycard='" + identitycard + '\'' +
                ", managetype='" + managetype + '\'' +
                ", alipayid='" + alipayid + '\'' +
                ", address='" + address + '\'' +
                ", identitycardimage='" + identitycardimage + '\'' +
                ", manageimage='" + manageimage + '\'' +
                ", createtime='" + createtime + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userid);
        dest.writeString(this.enterpriseuserid);
        dest.writeString(this.enterpriseusername);
        dest.writeString(this.phone);
        dest.writeString(this.identitycard);
        dest.writeString(this.managetype);
        dest.writeString(this.alipayid);
        dest.writeString(this.address);
        dest.writeString(this.identitycardimage);
        dest.writeString(this.manageimage);
        dest.writeString(this.createtime);
        dest.writeInt(this.state);
    }

    public ApplyForCompanyUser() {
    }

    protected ApplyForCompanyUser(Parcel in) {
        this.userid = in.readString();
        this.enterpriseuserid = in.readString();
        this.enterpriseusername = in.readString();
        this.phone = in.readString();
        this.identitycard = in.readString();
        this.managetype = in.readString();
        this.alipayid = in.readString();
        this.address = in.readString();
        this.identitycardimage = in.readString();
        this.manageimage = in.readString();
        this.createtime = in.readString();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<ApplyForCompanyUser> CREATOR = new Parcelable.Creator<ApplyForCompanyUser>
            () {
        @Override
        public ApplyForCompanyUser createFromParcel(Parcel source) {
            return new ApplyForCompanyUser(source);
        }

        @Override
        public ApplyForCompanyUser[] newArray(int size) {
            return new ApplyForCompanyUser[size];
        }
    };
}
