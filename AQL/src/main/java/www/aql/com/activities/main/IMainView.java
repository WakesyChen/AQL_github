package www.aql.com.activities.main;

import java.util.List;

import www.aql.com.entity.response.Banner;
import www.aql.com.entity.response.ColumnInfo;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/17.
 */
public interface IMainView extends IBaseView {

    void finishedLoadBanner(List<Banner> list);

    void finishedLoadColumnInfo(List<ColumnInfo> list);

}
