package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/20.
 */
public class UrlInfo implements Parcelable {
    public String schedulingurl;
    public String lineurl;
    public String activitiesbudgeturl;
    public String servicesurl;
    public String schedulingmoreurl;
    public String linemoreurl;
    public String activitiesbudgetmoreurl;
    public String servicesmoreurl;
    public String visainfourl;
    public String reserveinfourl;

    @Override
    public String toString() {
        return "UrlInfo{" +
                "schedulingurl='" + schedulingurl + '\'' +
                ", lineurl='" + lineurl + '\'' +
                ", activitiesbudgeturl='" + activitiesbudgeturl + '\'' +
                ", servicesurl='" + servicesurl + '\'' +
                ", schedulingmoreurl=" + schedulingmoreurl +
                ", linemoreurl=" + linemoreurl +
                ", activitiesbudgetmoreurl=" + activitiesbudgetmoreurl +
                ", servicesmoreurl=" + servicesmoreurl +
                ", visainfourl='" + visainfourl + '\'' +
                ", reserveinfourl='" + reserveinfourl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.schedulingurl);
        dest.writeString(this.lineurl);
        dest.writeString(this.activitiesbudgeturl);
        dest.writeString(this.servicesurl);
        dest.writeString(this.schedulingmoreurl);
        dest.writeString(this.linemoreurl);
        dest.writeString(this.activitiesbudgetmoreurl);
        dest.writeString(this.servicesmoreurl);
        dest.writeString(this.visainfourl);
        dest.writeString(this.reserveinfourl);
    }

    public UrlInfo() {
    }

    protected UrlInfo(Parcel in) {
        this.schedulingurl = in.readString();
        this.lineurl = in.readString();
        this.activitiesbudgeturl = in.readString();
        this.servicesurl = in.readString();
        this.schedulingmoreurl = in.readString();
        this.linemoreurl = in.readString();
        this.activitiesbudgetmoreurl = in.readString();
        this.servicesmoreurl = in.readString();
        this.visainfourl = in.readString();
        this.reserveinfourl = in.readString();
    }

    public static final Parcelable.Creator<UrlInfo> CREATOR = new Parcelable.Creator<UrlInfo>() {
        @Override
        public UrlInfo createFromParcel(Parcel source) {
            return new UrlInfo(source);
        }

        @Override
        public UrlInfo[] newArray(int size) {
            return new UrlInfo[size];
        }
    };
}
