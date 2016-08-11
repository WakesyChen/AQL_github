package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/8/8.
 */
public class ApplyForCompanyUserResponse extends BaseResponse {
    public ApplyForCompanyUser data;

    @Override
    public String toString() {
        return "ApplyForCompanyUserResponse{" +
                "data=" + data +
                '}';
    }
}
