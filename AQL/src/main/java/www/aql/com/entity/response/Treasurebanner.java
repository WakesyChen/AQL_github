package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/27.
 */
public class Treasurebanner implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Treasurebanner() {
    }

    protected Treasurebanner(Parcel in) {
    }

    public static final Parcelable.Creator<Treasurebanner> CREATOR = new Parcelable.Creator<Treasurebanner>() {
        @Override
        public Treasurebanner createFromParcel(Parcel source) {
            return new Treasurebanner(source);
        }

        @Override
        public Treasurebanner[] newArray(int size) {
            return new Treasurebanner[size];
        }
    };
}
