package com.wyf.daike.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyf.daike.Bean.MyUser;
import com.wyf.daike.R;
import com.wyf.daike.main.view.MainActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUserName;
    private EditText editPsw;
    private EditText editCheckPsw;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }


    private void initView() {
        editUserName = (EditText) findViewById(R.id.editUserName);
        editPsw = (EditText) findViewById(R.id.editPsw);
        editCheckPsw = (EditText) findViewById(R.id.editCheckPsw);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String editUserNameString = editUserName.getText().toString().trim();
        if (TextUtils.isEmpty(editUserNameString)) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String editPswString = editPsw.getText().toString().trim();
        if (TextUtils.isEmpty(editPswString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String editCheckPswString = editCheckPsw.getText().toString().trim();
        if (TextUtils.isEmpty(editCheckPswString)) {
            Toast.makeText(this, "密码确认", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        BmobUser bu = new BmobUser();
        bu.setUsername(editUserName.getText().toString());
        bu.setPassword(editCheckPsw.getText().toString());


        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if(e==null){
                   startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }else{
                    Log.d("wyf", "done: "+"注册失败");
                }
            }
        });

    }
}
