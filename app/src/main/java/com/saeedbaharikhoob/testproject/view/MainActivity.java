package com.saeedbaharikhoob.testproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.saeedbaharikhoob.testproject.databinding.ActivityMainBinding;
import com.saeedbaharikhoob.testproject.utils.RxBus;
import com.saeedbaharikhoob.testproject.utils.TabHost;
import com.saeedbaharikhoob.testproject.utils.TabPager.TabListener;
import com.saeedbaharikhoob.testproject.view.adapter.MainPagePagerAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerMainComponent;
import com.saeedbaharikhoob.testproject.view.di.module.MainModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPagePagerAdapter pagerAdapter;
    @Inject
    ActivityMainBinding activityMainBinding;

    private TabHost tabHost;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModule(new MainModule(this,getSupportFragmentManager())).build().inject(this);

        pager = activityMainBinding.pager;
        tabHost = activityMainBinding.materialTabHost;
        pager.setOffscreenPageLimit(5);
        pager.setAdapter(pagerAdapter);
        tabHost.setTabListener(tabListener);

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(pagerAdapter.getPageTitle(i), pagerAdapter.getEnableIcon(i), pagerAdapter.getDisableIcon(i));
        }

        tabHost.setup();
        pager.setCurrentItem(3);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedTab(pagerAdapter.getCount() - position - 1, false);




            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    TabListener tabListener = new TabListener() {
        @Override
        public void onTabSelected(int tab) {

            pager.setCurrentItem(pagerAdapter.getCount() - tab - 1);


        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }
}
