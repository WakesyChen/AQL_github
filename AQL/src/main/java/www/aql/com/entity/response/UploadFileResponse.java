package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/8/8.
 */
public class UploadFileResponse extends BaseResponse {
    public UploadFile data;

    @Override
    public String toString() {
        return "UploadFileResponse{" +
                "data=" + data +
                '}';
    }
}
