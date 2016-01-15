

package app.philm.in.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import java.util.List;

import app.philm.in.R;
import app.philm.in.adapters.MovieGridAdapter;
import app.philm.in.model.ListItem;
import app.philm.in.model.PhilmMovie;
import app.philm.in.util.ActivityTransitions;

public abstract class MovieGridFragment extends BasePhilmMovieListFragment<GridView> {

    private MovieGridAdapter mMovieGridAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieGridAdapter = new MovieGridAdapter(getActivity());
        setListAdapter(mMovieGridAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int spacing = getResources().getDimensionPixelSize(R.dimen.movie_grid_spacing);
        getListView().setPadding(spacing, spacing, spacing, spacing);
    }

    @Override
    public void onListItemClick(GridView l, View v, int position, long id) {
        if (hasCallbacks()) {
            ListItem<PhilmMovie> item = (ListItem<PhilmMovie>) l.getItemAtPosition(position);
            if (item.getListType() == ListItem.TYPE_ITEM) {
                getCallbacks().showMovieDetail(item.getListItem(),
                        ActivityTransitions.scaleUpAnimation(v));
            }
        }
    }

    @Override
    public void setItems(List<ListItem<PhilmMovie>> items) {
        mMovieGridAdapter.setItems(items);
        moveListViewToSavedPositions();
    }

    @Override
    public String getRequestParameter() {
        return null;
    }

    @Override
    public boolean isModal() {
        return false;
    }

    @Override
    protected GridView createListView(Context context, LayoutInflater inflater) {
        return (GridView) inflater.inflate(R.layout.view_grid, null);
    }

}
