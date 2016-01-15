

package app.philm.in.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.philm.in.BasePhilmActivity;
import app.philm.in.Display;
import app.philm.in.R;

public abstract class BasePhilmFragment extends Fragment {

    private Toolbar mToolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar, true);
    }

    protected final void setSupportActionBar(Toolbar toolbar, boolean handleBackground) {
        ((BasePhilmActivity) getActivity()).setSupportActionBar(toolbar, handleBackground);
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected Display getDisplay() {
        return ((BasePhilmActivity) getActivity()).getDisplay();
    }

}
