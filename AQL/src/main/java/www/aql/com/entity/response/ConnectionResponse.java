package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/17.
 */
public class ConnectionResponse {
    public String token;
    public long finite;

    @Override
    public String toString() {
        return "ConnectionResponse{" +
                "token='" + token + '\'' +
                ", finite=" + finite +
                '}';
    }
}
