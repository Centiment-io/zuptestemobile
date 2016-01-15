

package app.philm.in.fragments.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.philm.in.R;
import app.philm.in.view.SlidingTabLayout;

public abstract class BasePhilmTabFragment extends BasePhilmMovieFragment {

    private static final String SAVE_SELECTED_TAB = "selected_tab";

    private ViewPager mViewPager;
    private TabPagerAdapter mAdapter;
    private SlidingTabLayout mSlidingTabStrip;

    private int mCurrentItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        mAdapter = new TabPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.spacing_minor));

        mSlidingTabStrip = (SlidingTabLayout) view.findViewById(R.id.viewpager_tabs);
        mSlidingTabStrip.setViewPager(mViewPager);
        mSlidingTabStrip.setTabListener(new SlidingTabLayout.TabListener() {
            @Override
            public void onTabSelected(int pos) {
                // NO-OP
            }

            @Override
            public void onTabReSelected(int pos) {
                final Fragment fragment = mAdapter.getItem(pos);
                if (fragment instanceof ListFragment) {
                    ((ListFragment) fragment).smoothScrollTo(0);
                }
            }
        });

        if (savedInstanceState != null) {
            mCurrentItem = savedInstanceState.getInt(SAVE_SELECTED_TAB);
        }

        return view;
    }

    @Override
    public void showSecondaryLoadingProgress(boolean visible) {
        // NO-OP
    }

    @Override
    public void onResume() {
        super.onResume();
        mSlidingTabStrip.getBackground().setAlpha(255);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurrentItem = mViewPager.getCurrentItem();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVE_SELECTED_TAB, mCurrentItem);
        super.onSaveInstanceState(outState);
    }

    protected ViewPager getViewPager() {
        return mViewPager;
    }

    protected void setFragments(List<Fragment> fragments) {
        mAdapter.setFragments(fragments);
        mSlidingTabStrip.notifyDataSetChanged();
        mViewPager.setCurrentItem(mCurrentItem);
    }

    protected SlidingTabLayout getSlidingTabStrip() {
        return mSlidingTabStrip;
    }

    protected TabPagerAdapter getAdapter() {
        return mAdapter;
    }

    protected abstract String getTabTitle(int position);

    protected class TabPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragments;

        private TabPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
        }

        void setFragments(List<Fragment> fragments) {
            mFragments.clear();
            mFragments.addAll(fragments);
            notifyDataSetChanged();
        }

        @Override
        public final Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public final int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getTabTitle(position);
        }
    }

}
