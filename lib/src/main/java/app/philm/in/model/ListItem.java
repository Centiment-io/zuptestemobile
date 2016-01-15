

package app.philm.in.model;

public interface ListItem<T> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_SECTION = 1;

    public int getListType();

    public T getListItem();

    public int getListSectionTitle();
}
