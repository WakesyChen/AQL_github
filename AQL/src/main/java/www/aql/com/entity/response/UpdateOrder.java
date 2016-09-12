package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/28.
 */
public class UpdateOrder implements Parcelable {
    public int orderid;
    public int state;

    @Override
    public String toString() {
        return "UpdateOrder{" +
                "orderid=" + orderid +
                ", state=" + state +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderid);
        dest.writeInt(this.state);
    }

    public UpdateOrder() {
    }

    protected UpdateOrder(Parcel in) {
        this.orderid = in.readInt();
        this.state = in.readInt();
    }

    public static final Creator<UpdateOrder> CREATOR = new Creator<UpdateOrder>() {
        @Override
        public UpdateOrder createFromParcel(Parcel source) {
            return new UpdateOrder(source);
        }

        @Override
        public UpdateOrder[] newArray(int size) {
            return new UpdateOrder[size];
        }
    };
}
