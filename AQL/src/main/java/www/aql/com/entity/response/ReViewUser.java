package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/25.
 */
public class ReViewUser {

    public String userid;
    public String userphone;
    public String nickname;
    public String userheader;

    @Override
    public String toString() {
        return "ReViewUser{" +
                "userid='" + userid + '\'' +
                ", userphone='" + userphone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userheader='" + userheader + '\'' +
                '}';
    }
}
