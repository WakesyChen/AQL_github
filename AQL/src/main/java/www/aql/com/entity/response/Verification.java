package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/29.
 */
public class Verification implements Parcelable {
    public String code;
    public String phone;

    @Override
    public String toString() {
        return "Verification{" +
                "code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.phone);
    }

    public Verification() {
    }

    protected Verification(Parcel in) {
        this.code = in.readString();
        this.phone = in.readString();
    }

    public static final Creator<Verification> CREATOR = new Creator<Verification>() {
        @Override
        public Verification createFromParcel(Parcel source) {
            return new Verification(source);
        }

        @Override
        public Verification[] newArray(int size) {
            return new Verification[size];
        }
    };
}
