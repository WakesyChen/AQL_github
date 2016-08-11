package www.aql.com.entity.response;

import java.util.List;

/**
 * Created by Jason on 2016/7/17.
 */
public class ColumnInfoResponse extends BaseResponse {
    public List<ColumnInfo> data;

    @Override
    public String toString() {
        return "ColumnInfoResponse{" +
                "data=" + data +
                '}';
    }
}
