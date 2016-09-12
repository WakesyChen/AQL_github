package www.aql.com.activities.applyforcompanyuser;

import org.xutils.http.RequestParams;

import java.io.File;

import www.aql.com.entity.response.ApplyForCompanyUserResponse;
import www.aql.com.entity.response.BaseResponse;
import www.aql.com.entity.response.UploadFileResponse;
import www.aql.com.entity.response.request.ReqApplyForCompanyUser;
import www.aql.com.enums.Keys;
import www.aql.com.urls.MyUrls;
import www.aql.com.utils.NetRequestCallBack;
import www.aql.com.utils.XUtilsHelper;
import www.aql.com.utils.RequestParamsHelper;

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
        RequestParams params = RequestParamsHelper.getBaseRequestParam(MyUrls.applyForCompanyUser);
        params.addBodyParameter(Keys.USER_ID, req.userid);
        params.addBodyParameter(Keys.enterpriseusername, req.enterpriseusername);
        params.addBodyParameter(Keys.phone, req.phone);
        params.addBodyParameter(Keys.identitycard, req.identitycard);
        params.addBodyParameter(Keys.managetype, req.managetype);
        params.addBodyParameter(Keys.alipayid, req.alipayid);
        params.addBodyParameter(Keys.address, req.address);
        params.addBodyParameter(Keys.identitycardimage, req.phone);
        params.addBodyParameter(Keys.manageimage, req.manageimage);
        XUtilsHelper.getInstance().get(params, new NetRequestCallBack<ApplyForCompanyUserResponse>() {
            @Override
            protected void onSuccess(ApplyForCompanyUserResponse response) {
                if (response == null) {
                    view.netException();
                    return;
                }
                if (response.errcode == BaseResponse.SUCCESS_OK) {
                    view.successApply(response.data);
                } else {
                    view.loadFail(response.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                if (errorMsg != null) {
                    view.loadFail(errorMsg);
                } else {
                    view.netException();
                }
            }
        }, ApplyForCompanyUserResponse.class);
    }

    @Override
    public void uploadIdentifyImg(File file) {
        XUtilsHelper.getInstance().post(MyUrls.uploadFile, file, new NetRequestCallBack<UploadFileResponse>() {
            @Override
            protected void onSuccess(UploadFileResponse uploadFileResponse) {
                if (uploadFileResponse.errcode == BaseResponse.SUCCESS_OK) {
                    view.successUploadIdentifyImg(uploadFileResponse.data);
                } else {
                    view.loadFail(uploadFileResponse.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                view.loadFail(errorMsg);
            }
        }, UploadFileResponse.class);

    }

    @Override
    public void uploadLicenseImg(File file) {
        XUtilsHelper.getInstance().post(MyUrls.uploadFile, file, new NetRequestCallBack<UploadFileResponse>() {
            @Override
            protected void onSuccess(UploadFileResponse uploadFileResponse) {
                if (uploadFileResponse.errcode == BaseResponse.SUCCESS_OK) {
                    view.successUploadLicenseImg(uploadFileResponse.data);
                } else {
                    view.loadFail(uploadFileResponse.errmsg);
                }
            }

            @Override
            protected void onFailed(String errorMsg) {
                view.loadFail(errorMsg);
            }
        }, UploadFileResponse.class);
    }
}
