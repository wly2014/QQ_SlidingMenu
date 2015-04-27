package wly.com.qq_slidingmenu;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {
    @InjectView(R.id.sliding_menu)
    Sliding slidingMenu;

    @InjectView(R.id.imageView)
    ImageView imageView;

    @InjectView(R.id.menu)
    ViewGroup mMenu;

    public Content mContent;

    private FragmentManager fragmentManager;
    private SignFragment signFragment;
    private SettingFragment settingFragment;
    private SeekFragment seekFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mContent = (Content) findViewById(R.id.content);
        mContent.setSliding(slidingMenu);

        signFragment = new SignFragment();
        settingFragment = new SettingFragment();
        seekFragment = new SeekFragment();


        fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.content, signFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.content, settingFragment).commit();

    }

    @OnClick({R.id.imageView, R.id.imageView2, R.id.imageView3})
    public void onClick(ImageView imageView) {
        switch (imageView.getId()) {
            case R.id.imageView:
//                Toast.makeText(MainActivity.this, "a", Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction().replace(R.id.content, signFragment).commit();
                slidingMenu.close();
                break;
            case R.id.imageView2:

                fragmentManager.beginTransaction().replace(R.id.content, settingFragment).commit();
                slidingMenu.close();
                break;
            case R.id.imageView3:

                fragmentManager.beginTransaction().replace(R.id.content, seekFragment).commit();
                slidingMenu.close();
                break;

        }

    }

}
