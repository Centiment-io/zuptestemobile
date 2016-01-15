

package app.philm.in.view;


import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import app.philm.in.R;

public class InsetsLinearLayout extends LinearLayout {

    public InsetsLinearLayout(Context context) {
        this(context, null);
    }

    public InsetsLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsetsLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        View layoutProfileInner = findViewById(R.id.inner_layout_profile);

        if (layoutProfileInner != null) {
            MarginLayoutParams lp = (MarginLayoutParams) layoutProfileInner.getLayoutParams();
            lp.topMargin = insets.top;
            ViewCompat.requestApplyInsets(this);
        }

        setPadding(insets.left, 0, insets.right, insets.bottom);

        return true;
    }
}
