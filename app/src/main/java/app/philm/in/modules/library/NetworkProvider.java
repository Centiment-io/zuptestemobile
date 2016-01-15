

package app.philm.in.modules.library;

import android.content.Context;

import com.jakewharton.trakt.Trakt;
import com.uwetrottmann.tmdb.Tmdb;

import java.io.File;

import javax.inject.Singleton;

import app.philm.in.Constants;
import app.philm.in.network.PhilmTmdb;
import app.philm.in.network.PhilmTrakt;
import app.philm.in.qualifiers.ApplicationContext;
import app.philm.in.qualifiers.CacheDirectory;
import dagger.Module;
import dagger.Provides;

@Module(
        library = true,
        includes = ContextProvider.class
)
public class NetworkProvider {

    @Provides @Singleton
    public Trakt provideTraktClient(@CacheDirectory File cacheLocation) {
        Trakt trakt = new PhilmTrakt(cacheLocation);
        trakt.setApiKey(Constants.TRAKT_API_KEY);
        trakt.setIsDebug(Constants.DEBUG_NETWORK);
        return trakt;
    }

    @Provides @Singleton
    public Tmdb provideTmdbClient(@CacheDirectory File cacheLocation) {
        Tmdb tmdb = new PhilmTmdb(cacheLocation);
        tmdb.setApiKey(Constants.TMDB_API_KEY);
        tmdb.setIsDebug(Constants.DEBUG_NETWORK);
        return tmdb;
    }

    @Provides @Singleton @CacheDirectory
    public File provideHttpCacheLocation(@ApplicationContext Context context) {
        return context.getCacheDir();
    }

}
