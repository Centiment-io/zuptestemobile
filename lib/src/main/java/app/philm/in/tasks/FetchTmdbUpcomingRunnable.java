

package app.philm.in.tasks;

import com.uwetrottmann.tmdb.entities.MovieResultsPage;

import app.philm.in.state.MoviesState;
import retrofit.RetrofitError;

public class FetchTmdbUpcomingRunnable extends BaseTmdbPaginatedMovieRunnable {

    public FetchTmdbUpcomingRunnable(int callingId, int page) {
        super(callingId, page);
    }

    @Override
    public MovieResultsPage doBackgroundCall() throws RetrofitError {
        return getTmdbClient().moviesService().upcoming(getPage(),
                getCountryProvider().getTwoLetterLanguageCode());
    }

    @Override
    protected MoviesState.MoviePaginatedResult getResultFromState() {
        return mMoviesState.getUpcoming();
    }

    @Override
    protected void updateState(MoviesState.MoviePaginatedResult result) {
        mMoviesState.setUpcoming(result);
    }
}