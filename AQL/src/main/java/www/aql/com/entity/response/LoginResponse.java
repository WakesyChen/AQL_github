package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/29.
 */
public class LoginResponse extends BaseResponse {
    public User data;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                '}';
    }
}
