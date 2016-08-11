package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/24.
 */
public class Evaluation {

    public String routeid;
    public String reviewid;
    public ReViewUser reviewuser;
    public String reviewcontent;
    public String reviewtime;

    @Override
    public String toString() {
        return "Evaluation{" +
                "routeid='" + routeid + '\'' +
                ", reviewid='" + reviewid + '\'' +
                ", reviewcontent='" + reviewcontent + '\'' +
                ", reviewtime='" + reviewtime + '\'' +
                '}';
    }
}
