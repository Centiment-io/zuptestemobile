

package app.philm.in.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import app.philm.in.R;
import app.philm.in.adapters.PersonCreditSectionedListAdapter;
import app.philm.in.controllers.MovieController;
import app.philm.in.model.ListItem;
import app.philm.in.model.PhilmPersonCredit;
import app.philm.in.util.ActivityTransitions;


public abstract class BasePersonCreditListFragment
        extends BaseMovieControllerListFragment<ListView, PhilmPersonCredit>
        implements MovieController.PersonCreditListUi {

    private PersonCreditSectionedListAdapter mMovieListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieListAdapter = new PersonCreditSectionedListAdapter(getActivity());
        setListAdapter(mMovieListAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (hasCallbacks()) {
            ListItem<PhilmPersonCredit> item = (ListItem<PhilmPersonCredit>) l.getItemAtPosition(position);
            if (item.getListType() == ListItem.TYPE_ITEM) {
                PhilmPersonCredit credit = item.getListItem();
                if (credit != null) {
                    getCallbacks().showMovieDetail(credit, ActivityTransitions.scaleUpAnimation(v));
                }
            }
        }
    }

    @Override
    public void setItems(List<ListItem<PhilmPersonCredit>> items) {
        mMovieListAdapter.setItems(items);
    }

    @Override
    public boolean isModal() {
        return false;
    }

    @Override
    protected ListView createListView(Context context, LayoutInflater inflater) {
        return (ListView) inflater.inflate(R.layout.view_pinned_list, null);
    }
}
