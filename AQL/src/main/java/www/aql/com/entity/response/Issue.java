package www.aql.com.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jason on 2016/7/20.
 */
public class Issue implements Parcelable {
    public String issuesid;
    public String issuesname;

    @Override
    public String toString() {
        return "Issue{" +
                "issuesid='" + issuesid + '\'' +
                ", issuesname='" + issuesname + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.issuesid);
        dest.writeString(this.issuesname);
    }

    public Issue() {
    }

    protected Issue(Parcel in) {
        this.issuesid = in.readString();
        this.issuesname = in.readString();
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel source) {
            return new Issue(source);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
