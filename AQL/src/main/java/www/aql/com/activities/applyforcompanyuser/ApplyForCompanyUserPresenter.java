package www.aql.com.activities.applyforcompanyuser;

import java.io.File;

import www.aql.com.entity.response.request.ReqApplyForCompanyUser;

/**
 * Created by Jason on 2016/8/8.
 */
public class ApplyForCompanyUserPresenter implements ApplyForCompanyUserContact.IApplyForCompanyUserPresenter {
    private ApplyForCompanyUserContact.IApplyForCompanyUserView view;

    public ApplyForCompanyUserPresenter(ApplyForCompanyUserContact.IApplyForCompanyUserView view) {
        this.view = view;
    }

    @Override
    public void requestToBeCompanyUser(ReqApplyForCompanyUser req) {

    }

    @Override
    public void uploadIdentifyImg(File file) {

    }

    @Override
    public void uploadLicenseImg(File file) {
        
    }
}
