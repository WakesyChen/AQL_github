package www.aql.com.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jason on 2016/7/19.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> list_title;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> list_title) {
        super(fm);
        this.list = list;
        this.list_title = list_title;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
