package com.saeedbaharikhoob.testproject.view.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.view.fragments.NewsFragment;
import com.saeedbaharikhoob.testproject.view.fragments.SearchFragment;
import com.saeedbaharikhoob.testproject.view.fragments.StateFragment;
import com.saeedbaharikhoob.testproject.view.fragments.UserFragment;

/**
 * Created by ashez on 11/2/2015 AD.
 */
public class MainPagePagerAdapter extends FragmentPagerAdapter {


    String[] pages = null;
    Integer[] enableIcon;
    Integer[] disableIcon;

    Fragment[] fragments = null;


    public MainPagePagerAdapter(FragmentManager fm) {
        super(fm);

        pages = new String[]{
                G.context.getString(R.string.news),
                G.context.getString(R.string.states),
                G.context.getString(R.string.search),
                G.context.getString(R.string.profile)

        };
        enableIcon = new Integer[]{
                R.drawable.ic_buy_on,
                R.drawable.ic_location_on,
                R.drawable.ic_search_on,
                R.drawable.ic_profile_on,


        };
        disableIcon = new Integer[]{

                R.drawable.ic_buy_off,
                R.drawable.ic_location_off,
                R.drawable.ic_search_off,
                R.drawable.ic_profile_off,

        };


        fragments = new Fragment[pages.length];
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        switch (position) {

            case 0:
                if (fragments[0] == null)
                    fragments[0] = UserFragment.newInstance();
                frag = fragments[0];
                break;

            case 1:
                if (fragments[1] == null)
                    fragments[1] = SearchFragment.newInstance();

                frag = fragments[1];
                break;
            case 2:
                if (fragments[2] == null)
                    fragments[2] = StateFragment.newInstance();

                frag = fragments[2];
                break;
            case 3:
                if (fragments[3] == null)
                    fragments[3] = NewsFragment.newInstance();

                frag = fragments[3];
                break;

        }


        return frag;
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages[position];
    }

    public Integer getEnableIcon(int position) {
        return enableIcon[position];
    }

    public Integer getDisableIcon(int position) {
        return disableIcon[position];
    }
}
