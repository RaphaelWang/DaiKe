package com.wyf.daike.Login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wyf.daike.Bean.MyUser;
import com.wyf.daike.R;
import com.wyf.daike.main.MainActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button btn_submit;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        if(userInfo != null){
            // 允许用户使用应用
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            setContentView(R.layout.activity_login);
        }

        init();
        initEvent();
    }

    private void initEvent() {

        btn_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {


                MyUser bu2 = new MyUser();
                bu2.setUsername(mEmailView.getText().toString());
                bu2.setPassword(mPasswordView.getText().toString());
                bu2.login(new SaveListener<MyUser>() {

                    @Override
                    public void done(MyUser bmobUser, BmobException e) {
                        if(e==null){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                        }else{
                            Snackbar.make(v,"登录失败",Snackbar.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        });

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void init() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.editUserName);
        btn_submit = (Button) findViewById(R.id.btnSubmit);
        mPasswordView = (EditText) findViewById(R.id.editPsw);
        btn_register = (Button) findViewById(R.id.btnRegister);
    }
}