package app.philm.in.adapters;

import com.google.common.base.Objects;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.philm.in.model.ListItem;
import app.philm.in.view.PinnedSectionListView;

abstract class BasePhilmSectionedListAdapter<T> extends BaseAdapter
        implements PinnedSectionListView.PinnedSectionListAdapter {

    protected final Activity mActivity;
    private final LayoutInflater mLayoutInflater;

    private final int mViewLayoutId;
    private final int mPinnedViewLayoutId;

    private List<ListItem<T>> mItems;

    public BasePhilmSectionedListAdapter(Activity activity, int viewLayoutId,
            int pinnedViewLayoutId) {
        mActivity = activity;
        mLayoutInflater = activity.getLayoutInflater();
        mViewLayoutId = viewLayoutId;
        mPinnedViewLayoutId = pinnedViewLayoutId;
    }

    public void setItems(List<ListItem<T>> items) {
        if (!Objects.equal(items, mItems)) {
            mItems = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public ListItem<T> getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup viewGroup) {
        final ListItem<T> item = getItem(position);
        View view = convertView;

        if (view == null) {
            final int layout = item.getListType() == ListItem.TYPE_ITEM
                    ? mViewLayoutId
                    : mPinnedViewLayoutId;
            view = mLayoutInflater.inflate(layout, viewGroup, false);
        }

        switch (item.getListType()) {
            case ListItem.TYPE_ITEM:
                bindView(position, view, item);
                break;
            case ListItem.TYPE_SECTION:
                bindPinnedView(position, view, item);
                break;
        }

        return view;
    }

    protected abstract void bindView(int position, View view, ListItem<T> item);

    protected void bindPinnedView(int position, View view, ListItem<T> item) {
        ((TextView) view).setText(item.getListSectionTitle());
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getListType();
    }

    @Override
    public boolean isItemViewTypePinned(int type) {
        return type == ListItem.TYPE_SECTION;
    }
}
