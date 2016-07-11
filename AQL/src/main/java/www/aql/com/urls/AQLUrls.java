package www.aql.com.urls;

/**
 * Created by Jason on 2016/7/4.
 */
public interface AQLUrls {
    /**
     * 服务器地址
     */
    public String serviceUrl = "http://120.27.26.58:8001";
    /**
     * 建立连接（GET）
     */
    public String createConnection = serviceUrl + "/auth/gettoken";
    /**
     * 获取短信验证码（GET）
     */
    public String getSMSCode = serviceUrl + "/API/GetPhoneVerifyCode";
    /**
     * 用户注册（GET）
     */
    public String regist = serviceUrl + "/API/RegUser";
    /**
     * 用户登录验证（GET）
     */
    public String loginCheck = serviceUrl + "/API/UserLogin";
    /**
     * 修改用户信息（GET）
     */
    public String modifyUserInfo = serviceUrl + "/API/SetUserInfo";
    /**
     * 文件上传（POST）
     */
    public String uploadFile = serviceUrl + "/API/UpLoadFile";
    /**
     * 设置用户证件信息（POST）
     */
    public String setUserCertificateInfo = serviceUrl + "/API/SetUserCred";
    /**
     * 获取栏目列表信息（GET）
     */
    public String getColumnsInfo = serviceUrl + "/API/GetColumns";
    /**
     * 根据栏目列表获取banner横幅图（GET）
     */
    public String getBannersByColumns = serviceUrl + "/API/GetBanners";
    /**
     * 根据栏目获取旅游路线（GET）
     */
    public String getRouteByColumns = serviceUrl + "/API/GetRoutes";
    /**
     * 发表评论（POST）
     */
    public String addComments = serviceUrl + "/API/AddRouteReview";
    /**
     * 根据路线ID获取用户评论（GET）
     */
    public String getCommentsByRouteID = serviceUrl + "/API/GetRouteReviews";
    /**
     * 点赞（GET）
     */
    public String praise = serviceUrl + "/API/AddRouteLike";
    /**
     * 取消点赞（GET）
     */
    public String canclePraise = serviceUrl + "/API/RemoveRouteLike";
    /**
     * 获取中国省份（GET）
     */
    public String getProvinces = serviceUrl + "/API/GetProvinces";
    /**
     * 获取省下面的市（GET）
     */
    public String getCitiesByProvince = serviceUrl + "/API/GetCitys";
    /**
     * 获取市下面的区（GET）
     */
    public String getDistrictsByCity = serviceUrl + "/API/GetDistricts";
}
