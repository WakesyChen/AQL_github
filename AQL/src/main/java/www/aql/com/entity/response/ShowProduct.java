package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/27.
 */
public class ShowProduct implements Parcelable {
    public String proName;
    public String price;
    public String issue;
    public int proNum;
    public String imgUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.proName);
        dest.writeString(this.price);
        dest.writeString(this.issue);
        dest.writeInt(this.proNum);
        dest.writeString(this.imgUrl);
    }

    public ShowProduct() {
    }

    protected ShowProduct(Parcel in) {
        this.proName = in.readString();
        this.price = in.readString();
        this.issue = in.readString();
        this.proNum = in.readInt();
        this.imgUrl = in.readString();
    }

    public static final Creator<ShowProduct> CREATOR = new Creator<ShowProduct>() {
        @Override
        public ShowProduct createFromParcel(Parcel source) {
            return new ShowProduct(source);
        }

        @Override
        public ShowProduct[] newArray(int size) {
            return new ShowProduct[size];
        }
    };

    @Override
    public String toString() {
        return "ShowProduct{" +
                "proName='" + proName + '\'' +
                ", price='" + price + '\'' +
                ", issue='" + issue + '\'' +
                ", proNum=" + proNum +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
