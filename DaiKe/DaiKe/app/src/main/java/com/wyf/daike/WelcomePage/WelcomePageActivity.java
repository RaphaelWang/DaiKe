package com.wyf.daike.WelcomePage;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wyf.daike.Adapter.WelcomeViewPageAdapter;
import com.wyf.daike.R;

public class WelcomePageActivity extends Activity {

    private ImageView imgViews[];
    private int imgId[];
    private ImageView imageView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_page);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        imgId = new int[]{};
        imgViews = new ImageView[imgId.length];

        for(int i=0;i<imgId.length;i++)
        {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imgId[i]);
        }

        WelcomeViewPageAdapter adapter = new WelcomeViewPageAdapter(imgViews);
        viewPager.setAdapter(adapter);
    }
}
