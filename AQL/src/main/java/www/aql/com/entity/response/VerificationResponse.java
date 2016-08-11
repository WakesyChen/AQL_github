package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/29.
 */
public class VerificationResponse extends BaseResponse {
    @Override
    public String toString() {
        return "VerificationResponse{" +
                "data=" + data +
                '}';
    }

    public Verification data;
}
