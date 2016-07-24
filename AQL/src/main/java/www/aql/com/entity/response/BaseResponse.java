package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/17.
 */
public class BaseResponse {
    public static final int SUCCESS_OK = 0;
    public long errcode;
    public String errmsg;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
