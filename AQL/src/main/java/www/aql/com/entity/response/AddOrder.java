package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/27.
 */
public class AddOrder implements Parcelable {

    public int orderid;
    public String user;
    public String paymoney;
    public int paytype;
    public String thirdorderid;
    public int state;
    public String ordertime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderid);
        dest.writeString(this.user);
        dest.writeString(this.paymoney);
        dest.writeInt(this.paytype);
        dest.writeString(this.thirdorderid);
        dest.writeInt(this.state);
        dest.writeString(this.ordertime);
    }

    public AddOrder() {
    }

    protected AddOrder(Parcel in) {
        this.orderid = in.readInt();
        this.user = in.readString();
        this.paymoney = in.readString();
        this.paytype = in.readInt();
        this.thirdorderid = in.readString();
        this.state = in.readInt();
        this.ordertime = in.readString();
    }

    public static final Creator<AddOrder> CREATOR = new Creator<AddOrder>() {
        @Override
        public AddOrder createFromParcel(Parcel source) {
            return new AddOrder(source);
        }

        @Override
        public AddOrder[] newArray(int size) {
            return new AddOrder[size];
        }
    };

    @Override
    public String toString() {
        return "AddOrder{" +
                "orderid=" + orderid +
                ", user='" + user + '\'' +
                ", paymoney='" + paymoney + '\'' +
                ", paytype=" + paytype +
                ", thirdorderid='" + thirdorderid + '\'' +
                ", state=" + state +
                ", ordertime='" + ordertime + '\'' +
                '}';
    }
}
