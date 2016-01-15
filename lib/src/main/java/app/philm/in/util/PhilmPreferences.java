

package app.philm.in.util;


public interface PhilmPreferences {

    public boolean shouldRemoveFromWatchlistOnWatched();

    public void setRemoveFromWatchlistOnWatched(boolean remove);

    public boolean hasShownTraktLoginPrompt();

    public void setShownTraktLoginPrompt();

}
