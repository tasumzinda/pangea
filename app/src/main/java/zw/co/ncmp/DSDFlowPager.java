package zw.co.ncmp;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdhlakama on 8/13/2016.
 */
public class DSDFlowPager extends FragmentActivity {
    DSDPageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_view);
        List<Fragment> fragments = getFragments();
        pageAdapter = new DSDPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager =
                (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(DSDFragment.newInstance("Fragment 1"));
        fList.add(DSDFragment.newInstance("Fragment 2"));
        fList.add(DSDFragment.newInstance("Fragment 3"));
        return fList;
    }
}