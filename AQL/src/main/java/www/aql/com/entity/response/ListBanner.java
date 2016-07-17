package www.aql.com.entity.response;

import java.util.List;

/**
 * Created by Jason on 2016/7/17.
 */
public class ListBanner extends BaseResponse {
    public List<Banner> data;

    @Override
    public String toString() {
        return "ListBanner{" +
                "data=" + data +
                '}';
    }
}
