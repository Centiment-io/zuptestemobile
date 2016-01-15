

package app.philm.in.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import app.philm.in.R;
import app.philm.in.model.ListItem;
import app.philm.in.model.PhilmMovieCredit;
import app.philm.in.view.PhilmImageView;

public class MovieCreditSectionedListAdapter extends BasePhilmSectionedListAdapter<PhilmMovieCredit> {

    private static final String LOG_TAG = MovieCreditSectionedListAdapter.class.getSimpleName();

    public MovieCreditSectionedListAdapter(Activity activity) {
        super(activity, R.layout.item_list_2line, R.layout.item_list_movie_section_header);
    }

    @Override
    protected void bindView(int position, View view, ListItem<PhilmMovieCredit> item) {
        PhilmMovieCredit credit = item.getListItem();

        final TextView nameTextView = (TextView) view.findViewById(R.id.textview_title);
        nameTextView.setText(credit.getPerson().getName());

        final TextView characterTextView = (TextView) view.findViewById(R.id.textview_subtitle_1);
        characterTextView.setText(credit.getJob());

        final PhilmImageView imageView = (PhilmImageView) view.findViewById(R.id.imageview_poster);
        imageView.setAvatarMode(true);
        imageView.loadProfile(credit.getPerson());
    }
}
