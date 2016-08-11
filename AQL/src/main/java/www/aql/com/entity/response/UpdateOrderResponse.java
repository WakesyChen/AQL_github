package www.aql.com.entity.response;

/**
 * Created by Jason on 2016/7/28.
 */
public class UpdateOrderResponse extends BaseResponse {
    public UpdateOrder data;

    @Override
    public String toString() {
        return "UpdateOrderResponse{" +
                "data=" + data +
                '}';
    }
}
