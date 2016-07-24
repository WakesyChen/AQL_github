package www.aql.com.entity.response;

import java.util.List;

/**
 * Created by Jason on 2016/7/17.
 */
public class BannerResponse extends BaseResponse {
    public List<Banner> data;

    @Override
    public String toString() {
        return "BannerResponse{" +
                "data=" + data +
                '}';
    }
}
