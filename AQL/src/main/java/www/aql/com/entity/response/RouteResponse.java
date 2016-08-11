package www.aql.com.entity.response;

import java.util.List;

/**
 * Created by Jason on 2016/7/19.
 */
public class RouteResponse extends BaseResponse {
    public List<Route> data;

    @Override
    public String toString() {
        return "RouteResponse{" +
                "data=" + data +
                '}';
    }
}
