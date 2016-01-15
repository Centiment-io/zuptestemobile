

package app.philm.in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

public class AboutActivity extends BasePhilmActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void handleIntent(Intent intent, Display display) {
        if (!display.hasMainFragment()) {
            display.showAboutFragment();
        }
    }
}
