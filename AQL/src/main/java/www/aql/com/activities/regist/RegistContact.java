package www.aql.com.activities.regist;

import www.aql.com.entity.response.RegistResponse;
import www.aql.com.entity.response.VerificationResponse;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/7/29.
 */
public class RegistContact {
    interface IRegistView extends IBaseView {

        void finishRegist(RegistResponse response);

        void finishGetVerification(VerificationResponse response);
    }

    interface IRegistPresenter extends IBasePresenter {
        void regist(String userphone, String password, String nickname);

        void getVerification(String phone, int type, String activity);
    }
}
