package www.aql.com.activities.applyforcompanyuser;

import java.io.File;

import www.aql.com.entity.response.ApplyForCompanyUser;
import www.aql.com.entity.response.UploadFile;
import www.aql.com.entity.response.request.ReqApplyForCompanyUser;
import www.aql.com.interfaces.IBasePresenter;
import www.aql.com.interfaces.IBaseView;

/**
 * Created by Jason on 2016/8/8.
 */
public class ApplyForCompanyUserContact {
    interface IApplyForCompanyUserView extends IBaseView {
        void successApply(ApplyForCompanyUser response);

        void successUploadIdentifyImg(UploadFile uploadFile);

        void successUploadLicenseImg(UploadFile uploadFile);

    }

    interface IApplyForCompanyUserPresenter extends IBasePresenter {

        void requestToBeCompanyUser(ReqApplyForCompanyUser req);

        void uploadIdentifyImg(File file);

        void uploadLicenseImg(File file);

    }
}
