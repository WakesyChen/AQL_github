package www.aql.com.enums;

/**
 * Created by Jason on 2016/7/29.
 */
public interface ResponseErrorCode {
    int APP_ID_NOT_EXIST = -2;
    int INVALID_REQUEST = -1;
    int FAIL_MSG_SEND = 2;
    int TEL_HAS_BEEN_REGISTED = 3;
    int NULL_TEL_OR_USERNAME = 4;
    int WRONG_TEL_OR_PASSWORD = 5;
    int USER_NOT_Exist = 6;
    int NULL_USER_ID = 7;
    int ERROR_FILE_UPLOAD = 8;
    int FAIL_ADD_COMMENTS = 9;
    int FAIL_PRAISE = 10;
    int FAIL_CANCEL_PRAISE = 11;
    int FAIL_JOIN_DUOBAO = 12;
    int FAIL_SHARE_ORDER = 13;
    int FAIL_ADD_SHOPPINGCART = 14;
    int FAIL_DEL_SHOPPINGCART = 15;
    int FAIL_ADD_ORDER = 16;
    int FAIL_UPDATE_ORDER = 17;
    int FAIL_APPLY_COMPANY_USER = 18;
    int FAIL_ONE_YUAN_BUY = 19;
}
