package www.aql.com.urls;

/**
 * Created by Jason on 2016/7/4.
 */
public interface MyUrls {
    /**
     * 服务器地址
     */
    public String service_Url = "http://120.27.26.58:8001";
    /**
     * 建立连接（GET）
     */
    public String createConnection = service_Url + "/auth/gettoken";
    /**
     * 获取短信验证码（GET）
     */
    public String getSMSCode = service_Url + "/API/GetPhoneVerifyCode";
    /**
     * 用户注册（GET）
     */
    public String regist = service_Url + "/API/RegUser";
    /**
     * 用户登录验证（GET）
     */
    public String loginCheck = service_Url + "/API/UserLogin";
    /**
     * 修改用户信息（GET）
     */
    public String modifyUserInfo = service_Url + "/API/SetUserInfo";
    /**
     * 文件上传（POST）
     */
    public String uploadFile = service_Url + "/API/UpLoadFile";
    /**
     * 设置用户证件信息（POST）
     */
    public String setUserCertificateInfo = service_Url + "/API/SetUserCred";
    /**
     * 获取栏目列表信息（GET）
     */
    public String getColumnsInfo = service_Url + "/API/GetColumns";
    /**
     * 根据栏目列表获取banner横幅图（GET）
     */
    public String getBannersByColumns = service_Url + "/API/GetBanners";
    /**
     * 根据栏目获取旅游路线（GET）
     */
    public String getRouteByColumns = service_Url + "/API/GetRoutes";
    /**
     * 发表评论（POST）
     */
    public String addComments = service_Url + "/API/AddRouteReview";
    /**
     * 根据路线ID获取用户评论（GET）
     */
    public String getCommentsByRouteID = service_Url + "/API/GetRouteReviews";
    /**
     * 点赞（GET）
     */
    public String praise = service_Url + "/API/AddRouteLike";
    /**
     * 取消点赞（GET）
     */
    public String canclePraise = service_Url + "/API/RemoveRouteLike";
    /**
     * 获取中国省份（GET）
     */
    public String getProvinces = service_Url + "/API/GetProvinces";
    /**
     * 获取省下面的市（GET）
     */
    public String getCitiesByProvince = service_Url + "/API/GetCitys";
    /**
     * 获取市下面的区（GET）
     */
    public String getDistrictsByCity = service_Url + "/API/GetDistricts";
    /**
     * 获取购物车（GET）
     */
    public String getShoppingcart = service_Url + "/API/GetShoppingCarts";
    /**
     * 新增订单
     */
    public String addOrder = service_Url + "/API/AddOrder";
    /**
     * 更改订单状态
     */
    public String updateOrder = service_Url + "/API/UpdateOrderState";
    /**
     * 申请企业用户
     */
    public String applyForCompanyUser = service_Url + "/API/AddEnterpriseUser";
    /**
     * 获取用户基本信息
     */
    public String getUserInfo = service_Url + "/API/GetUserInfo";
}
