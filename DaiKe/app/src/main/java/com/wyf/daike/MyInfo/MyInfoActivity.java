package com.wyf.daike.MyInfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wyf.daike.R;
import com.wyf.daike.Util.CircleImageView;
import com.wyf.daike.Util.LoadImgUtil;
import com.wyf.daike.Util.SharedPresferencesUtil;
import com.wyf.daike.global.MyApplication;
import com.wyf.daike.main.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.security.PrivilegedAction;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.bmob.v3.socketio.callback.EventCallback;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private CircleImageView imageUser;
    private TextView textTel;
    private EditText textPersonName;
    private EditText editAliPay;
    private Button btnEdit;
    private PopupWindow popWindow;
    private Button btnTakePhoto,btnCancle,btnOpenPhotoBook;
    private  final  int TAKE_PHOTO =1,CORP_PHOTO=2;
    private Uri userImageUri;
    private File root;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_my_info);
        initView();
        init();
        //getSupportActionBar().hide();


    }

    private void init() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DaiKe";
        root = new File(path);
        //从本地加载立像
//        LoadImgUtil.loadImg(this,imageUser,root+"/"+BmobUser.getCurrentUser().getUsername()+".jpg");
      //  LoadImgUtil.loadImg(this,SharedPresferencesUtil.getData(this,"userImgUrl"));
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        imageUser = (CircleImageView) findViewById(R.id.imageUser);
        textTel = (TextView) findViewById(R.id.textTel);
        textPersonName = (EditText) findViewById(R.id.textPersonName);
        editAliPay = (EditText) findViewById(R.id.editAliPay);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        LoadImgUtil.loadImg(this,imageUser,SharedPresferencesUtil.getData(this,"userImgUrl"),R.drawable.ly);
//
//        File f = new File(root,"touxiang.jpg");
//        if(!f.exists())
//        {
//
//        }
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/touxiang.jpg");
//
//        imageUser.setImageBitmap(bitmap);


        imageUser.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEdit:

                break;
            case R.id.imageUser:
                Log.d("TAG", "onClick: ");
                openPopWindow();
                break;

            case R.id.btnTakePhoto:
                openCamera();

                break;
            case  R.id.btnCancle:
                popWindow.dismiss();
                popWindow = null;
                break;
            case R.id.btnScanPhoto:
                openPhotoBook();
                break;
        }
    }

    /****
     * 打开相册
     *
     */
    private void openPhotoBook() {

        File file = new File(root.getAbsolutePath(),"touxiang.jpg");
        try{
            if(file.exists())
            {
                file.delete();
            }
            file.createNewFile();
        }catch (IOException e) {
            e.printStackTrace();
        }
        userImageUri = Uri.fromFile(file);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,userImageUri);
        startActivityForResult(intent,CORP_PHOTO);

    }

    /****
     * 打开相机
     *
     */

    private void openCamera() {

        File file =new File(root, BmobUser.getCurrentUser().getUsername()+".jpg");

        try {
            if(file!=null&&file.exists())
            {
                file.delete();
             }
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userImageUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,userImageUri);
        startActivityForResult(intent,TAKE_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    Intent intent =new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(userImageUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,userImageUri);

                    startActivityForResult(intent,CORP_PHOTO);
                }

                break;
            case CORP_PHOTO:
                Bitmap bitmap =null;

                if(resultCode==RESULT_OK) {


                    if(userImageUri==null)
                    {
                        Bundle bundle = data.getExtras();
                        bitmap = (Bitmap) bundle.get("data");

                        userImageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,null,null));

                        imageUser.setImageBitmap(bitmap);
                        upLoadUserImg(userImageUri);
                        return;
                    }


                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(userImageUri));
                        imageUser.setImageBitmap(bitmap);

                        upLoadUserImg(userImageUri);

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }

                    Log.d("TAG", "onActivityResult: ");
                }
                popWindow.dismiss();
                LoadImgUtil.loadImg(this,imageUser,SharedPresferencesUtil.getData(this,"userImgUrl"),R.drawable.ly);

                break;

            default:
                break;
        }

    }

    private void openPopWindow() {
        Log.d("TAG", "openPopWindow: ");

       View viewPop = getLayoutInflater().from(MyInfoActivity.this).inflate(R.layout.image_capture,null);

        //创建布局并且给它布局大小
        if(popWindow==null)
        {
            popWindow  = new PopupWindow(viewPop,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            Log.d("TAG", "creat openPopWindow: ");

        }

        popWindow.setFocusable(true);
//        ColorDrawable colorDrawable = new ColorDrawable(0xb0000000);
//        popWindow.setBackgroundDrawable(colorDrawable);


        //在低部显示
        popWindow.showAtLocation(viewPop, Gravity.BOTTOM,0,0);

        btnCancle = (Button)viewPop.findViewById(R.id.btnCancle);
        btnTakePhoto = (Button) viewPop.findViewById(R.id.btnTakePhoto);
        btnOpenPhotoBook = (Button) viewPop.findViewById(R.id.btnScanPhoto);


        btnOpenPhotoBook.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        viewPop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(popWindow!=null&&popWindow.isShowing())
                {
                    popWindow.dismiss();
                    popWindow =null;
                }

                return false;
            }
        });
    }

    private void submit() {
        // validate
        String textPersonNameString = textPersonName.getText().toString().trim();
        if (TextUtils.isEmpty(textPersonNameString)) {
            Toast.makeText(this, "textPersonNameString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editAliPayString = editAliPay.getText().toString().trim();
        if (TextUtils.isEmpty(editAliPayString)) {
            Toast.makeText(this, "editAliPayString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
    }
    private  void deleteFile()
    {
        BmobFile file = new BmobFile();
        String filePath = SharedPresferencesUtil.getData(this,"userImgUrl");
        if(!TextUtils.isEmpty(filePath))
        {
            file.setUrl(filePath);//此url是上传文件成功之后通过bmobFile.getUrl()方法获取的。
            file.delete(new UpdateListener() {

                @Override
                public void done(BmobException e) {
                    if(e==null){

                    }else{
                    }
                }
            });
        }

    }
    private void upLoadUserImg(Uri uri)
    {
        deleteFile();
        final BmobFile bmobFile = new BmobFile(new File(userImageUri.getPath()));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.d("wyf","上传文件成功" + bmobFile.getFileUrl());
                    SharedPresferencesUtil.saveData(MyInfoActivity.this,"userImgUrl",bmobFile.getFileUrl().toString());

                }else{
                    Log.d("wyf", "done: "+"上传文件失败：" + e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

}
