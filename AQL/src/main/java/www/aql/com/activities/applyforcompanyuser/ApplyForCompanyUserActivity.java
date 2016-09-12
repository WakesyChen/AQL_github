package www.aql.com.activities.applyforcompanyuser;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.aql.com.R;
import www.aql.com.base.BaseActivity;
import www.aql.com.entity.response.ApplyForCompanyUser;
import www.aql.com.entity.response.UploadFile;
import www.aql.com.entity.response.request.ReqApplyForCompanyUser;
import www.aql.com.utils.MyUtils;
import www.aql.com.utils.SPConfig;
import www.aql.com.utils.SPUtils;

public class ApplyForCompanyUserActivity extends BaseActivity implements ApplyForCompanyUserContact
        .IApplyForCompanyUserView {

    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_input_tel)
    EditText etInputTel;
    @BindView(R.id.et_input_identitfy)
    EditText etInputIdentitfy;
    @BindView(R.id.et_business_type)
    EditText etBusinessType;
    @BindView(R.id.et_alipay_number)
    EditText etAlipayNumber;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_upload_identify)
    TextView tvUploadIdentify;
    @BindView(R.id.tv_identify_tip)
    TextView tvIdentifyTip;
    @BindView(R.id.img_identify_pic1)
    ImageView imgIdentifyPic1;
    @BindView(R.id.img_identify_pic2)
    ImageView imgIdentifyPic2;
    @BindView(R.id.tv_upload_license)
    TextView tvUploadLicense;
    @BindView(R.id.tv_business_license_tip)
    TextView tvBusinessLicenseTip;
    @BindView(R.id.img_business_license_pic)
    ImageView imgBusinessLicensePic;
    @BindView(R.id.tv_ensure_apply)
    TextView tvEnsureApply;
    @BindView(R.id.gv_show_identify)
    GridView gvIdentify;
    @BindView(R.id.gv_show_license)
    GridView gvLicense;

    // 存储Bmp图像
    private ArrayList<HashMap<String, Object>> totallist_identify;
    private ArrayList<HashMap<String, Object>> totallist_license;
    private SimpleAdapter simpleAdapter_identify;
    private SimpleAdapter simpleAdapter_license;

    private int uploadLocation;
    private int deleteLocation;
    private final int IDENTIFY = 1;
    private final int LICENSE = 2;

    private int times = 0;

    private ApplyForCompanyUserPresenter presenter;
    private String name;
    private String tel;
    private String idcard;
    private String type;
    private String alipayNum;
    private String shopAddress;
    private String idImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_company_user);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        /*
         * 载入默认图片添加图片加号 通过适配器实现 SimpleAdapter参数imageItem为数据源
		 * R.layout.griditem_addpic为布局
		 */
        totallist_identify = new ArrayList<HashMap<String, Object>>();
        totallist_license = new ArrayList<HashMap<String, Object>>();

        presenter = new ApplyForCompanyUserPresenter(this);

        setAdapter();

        /*
         * 监听GridView点击事件 报错:该函数必须抽象方法 故需要手动导入import android.com.library.view.View;
		 */
        gvIdentify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                deleteLocation = IDENTIFY;
                deleteDialog(position);
            }
        });
        gvLicense.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                deleteLocation = LICENSE;
                deleteDialog(position);
            }
        });
    }

    // 获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 打开图片
        if (resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                // 查询选择图片
                Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null,
                        null, null);
                // 返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                // 光标移动至开头 获取图片路径
                cursor.moveToFirst();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                if (path != null) {
                    refreshImg(path);
                } else {
                    MyUtils.showToast(this, "图片未找到");
                }
            } else {

            }
        }
        // 拍照
        if (resultCode == RESULT_OK && requestCode == TAKE_PHOTO) {
            //返回有缩略图
            if (data.hasExtra("data")) {
                Bitmap bitmap = data.getParcelableExtra("data");
                File file = new File(Environment.getExternalStorageDirectory() + "/myImage/");
                file.mkdirs();
                String fileName = Environment.getExternalStorageDirectory() + "/myImage/" + System.currentTimeMillis
                        () + ".jpg";
                try {
                    FileOutputStream b = new FileOutputStream(fileName);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                    b.flush();
                    b.close();
                    MyUtils.showToast(this, "照片已保存到：" + fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //得到bitmap后的操作
                refreshImg(fileName);
            }
        }
    }

    private void setAdapter() {
        simpleAdapter_identify = new SimpleAdapter(this, totallist_identify, R.layout.griditem_addpic, new
                String[]{"itemImage"},
                new int[]{R.id.imageView1});
        // 接口载入图片
        simpleAdapter_identify.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gvIdentify.setAdapter(simpleAdapter_identify);

        simpleAdapter_license = new SimpleAdapter(this, totallist_license, R.layout.griditem_addpic, new
                String[]{"itemImage"},
                new int[]{R.id.imageView1});
        // 接口载入图片
        simpleAdapter_license.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gvLicense.setAdapter(simpleAdapter_license);
    }

    private final String ITEMIMAGE = "itemImage";
    private final String IMAGEPATH = "imagePath";
    private final String IMAGEURL = "idImageUrl";

    private void refreshImg(String imgPath) {
        if (!TextUtils.isEmpty(imgPath)) {
            Bitmap addbmp = BitmapFactory.decodeFile(imgPath);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(ITEMIMAGE, addbmp);
            map.put(IMAGEPATH, imgPath);
            map.put(IMAGEURL, null);
            if (uploadLocation == IDENTIFY) {
                totallist_identify.add(map);
                if (totallist_identify.size() > 0) {
                    gvIdentify.setVisibility(View.VISIBLE);
                } else {
                    gvIdentify.setVisibility(View.GONE);
                }
                simpleAdapter_identify.notifyDataSetChanged();
            }
            if (uploadLocation == LICENSE) {
                totallist_license.add(map);
                if (totallist_license.size() > 0) {
                    gvLicense.setVisibility(View.VISIBLE);
                } else {
                    gvLicense.setVisibility(View.GONE);
                }
                simpleAdapter_license.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.tv_upload_identify, R.id.img_identify_pic1, R.id.img_identify_pic2, R.id.tv_upload_license, R.id
            .img_business_license_pic, R.id.tv_ensure_apply, R.id.tv_show_protocal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_upload_identify:
                uploadLocation = IDENTIFY;
                //选择照片
                if (totallist_identify.size() == 2) {
                    MyUtils.showToast(ApplyForCompanyUserActivity.this, "图片数2张已满");
                    //                    tvUploadIdentify.setEnabled(false);
                    return;
                }
                addImageDialog();
                break;
            case R.id.tv_upload_license:
                //选择照片
                uploadLocation = LICENSE;
                if (totallist_license.size() == 1) {
                    MyUtils.showToast(ApplyForCompanyUserActivity.this, "图片数1张已满");
                    //                    tvUploadLicense.setEnabled(false);
                    return;
                }
                addImageDialog();
                break;
            case R.id.tv_ensure_apply:
                name = etInputName.getText() + "";
                if (TextUtils.isEmpty(name)) {
                    MyUtils.showToast(this, "请先填写名字");
                    return;
                }
                tel = etInputTel.getText() + "";
                if (TextUtils.isEmpty(tel)) {
                    MyUtils.showToast(this, "请先填写联系电话");
                    return;
                }
                idcard = etInputIdentitfy.getText() + "";
                if (TextUtils.isEmpty(idcard)) {
                    MyUtils.showToast(this, "请先填写身份证号");
                    return;
                }
                type = etBusinessType.getText() + "";
                if (TextUtils.isEmpty(type)) {
                    MyUtils.showToast(this, "请先填写经营类型");
                    return;
                }
                alipayNum = etAlipayNumber.getText() + "";
                if (TextUtils.isEmpty(alipayNum)) {
                    MyUtils.showToast(this, "请先填写支付宝账号");
                    return;
                }
                shopAddress = etAddress.getText() + "";
                if (TextUtils.isEmpty(shopAddress)) {
                    MyUtils.showToast(this, "请先填写店铺地址");
                    return;
                }
                if (totallist_identify.size() <= 0) {
                    MyUtils.showToast(this, "请先上传法人身份证");
                    return;
                }
                if (totallist_license.size() <= 0) {
                    MyUtils.showToast(this, "请先上传营业执照");
                    return;
                }
                dialog.show();
                //请求服务器
                presenter.uploadIdentifyImg(new File((String) totallist_identify.get(times).get(IMAGEPATH)));
                break;
            case R.id.tv_show_protocal:
                break;
        }
    }

    private final int IMAGE_OPEN = 1; // 打开图片标记,
    private final int TAKE_PHOTO = 3; // 拍照标记

    /*
    * 添加图片 可通过本地添加、拍照添加
    */
    protected void addImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加图片");
        builder.setIcon(R.drawable.logo);
        //        builder.setCancelable(false); // 不响应back按钮
        builder.setItems(new String[]{"本地相册选择", "手机相机添加"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // 本地相册
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, IMAGE_OPEN);
                        break;
                    case 1: // 手机相机
                        dialog.dismiss();
                        Intent intentPhoto = new Intent("android.media.action.IMAGE_CAPTURE"); // 拍照
                        startActivityForResult(intentPhoto, TAKE_PHOTO);
                        break;
                }
            }
        });
        // 显示对话框
        builder.create().show();
    }

    /*
     * Dialog对话框提示用户删除操作 position为删除图片位置
	 */
    protected void deleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (deleteLocation == IDENTIFY) {
                    totallist_identify.remove(position);
                    simpleAdapter_identify.notifyDataSetChanged();
                }
                if (deleteLocation == LICENSE) {
                    totallist_license.remove(position);
                    simpleAdapter_license.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void successApply(ApplyForCompanyUser response) {

    }

    @Override
    public void successUploadIdentifyImg(UploadFile uploadFile) {
        if (times < totallist_identify.size() - 1) {
            idImageUrl = (String) totallist_identify.get(times).get(IMAGEURL);
            idImageUrl = uploadFile.filepath;
            presenter.uploadIdentifyImg(new File((String) totallist_identify.get(++times).get(IMAGEPATH)));
        } else {
            presenter.uploadLicenseImg(new File((String) totallist_license.get(0).get(IMAGEPATH)));
        }
    }

    @Override
    public void successUploadLicenseImg(UploadFile uploadFile) {
        String manageImg = (String) totallist_license.get(0).get(IMAGEURL);
        manageImg = uploadFile.filepath;

        //图片上传完毕，开始申请
        ReqApplyForCompanyUser req = new ReqApplyForCompanyUser();
        req.address = shopAddress;
        req.alipayid = alipayNum;
        req.enterpriseusername = name;
        req.identitycard = idcard;
        req.identitycardimage = (String) totallist_identify.get(0).get(IMAGEURL) + ";" + (String) totallist_identify
                .get(1).get(IMAGEURL);
        req.manageimage = manageImg;
        req.managetype = type;
        req.phone = tel;
        req.userid = SPUtils.getString(this, SPConfig.USER_ID, "");
        presenter.requestToBeCompanyUser(req);
    }

    @Override
    public void loadFail(String errMsg) {
        super.loadFail(errMsg);
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void netException() {
        super.netException();
        if (dialog != null)
            dialog.dismiss();
    }
}
