

package app.philm.in.tasks;

import com.uwetrottmann.tmdb.entities.MovieResultsPage;

import app.philm.in.state.MoviesState;
import retrofit.RetrofitError;

public class FetchTmdbNowPlayingRunnable extends BaseTmdbPaginatedMovieRunnable {

    public FetchTmdbNowPlayingRunnable(int callingId, int page) {
        super(callingId, page);
    }

    @Override
    public MovieResultsPage doBackgroundCall() throws RetrofitError {
        return getTmdbClient().moviesService().nowPlaying(
                getPage(),
                getCountryProvider().getTwoLetterLanguageCode());
    }

    @Override
    protected MoviesState.MoviePaginatedResult getResultFromState() {
        return mMoviesState.getNowPlaying();
    }

    @Override
    protected void updateState(MoviesState.MoviePaginatedResult result) {
        mMoviesState.setNowPlaying(result);
    }
}