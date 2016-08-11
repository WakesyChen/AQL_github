package www.aql.com.activities.customerevaluationmore;

import java.util.List;

import www.aql.com.entity.response.Evaluation;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/19.
 */
public class CustomerEvaluationMoreContact {
    interface ICustomerEvaluationMoreView extends IBaseView {

        void successRefreshLoadEvaluation(List<Evaluation> list);

        void successFirstLoadEvaluation(List<Evaluation> list);

        void successLoadMore(List<Evaluation> list);

    }

    interface ICustomerEvaluationMorePresenter extends IBasePresenter {

        void refreshLoadEvaluation(String routeid, int page, int rows);

        void firstLoadEvaluation(String routeid, int page, int rows);

        void loadMore(String routeid, int page, int rows);
    }
}
