

package app.philm.in.tasks;


import com.uwetrottmann.tmdb.entities.Videos;

import app.philm.in.model.PhilmMovie;
import app.philm.in.network.NetworkError;
import app.philm.in.state.BaseState;
import app.philm.in.state.MoviesState;
import retrofit.RetrofitError;

public class FetchTmdbMovieTrailersRunnable extends BaseMovieRunnable<Videos> {

    private final int mId;

    public FetchTmdbMovieTrailersRunnable(int callingId, int id) {
        super(callingId);
        mId = id;
    }

    @Override
    public Videos doBackgroundCall() throws RetrofitError {
        return getTmdbClient().moviesService().videos(mId, getCountryProvider().getTwoLetterLanguageCode());
    }

    @Override
    public void onSuccess(Videos result) {
        PhilmMovie movie = mMoviesState.getMovie(mId);

        if (movie != null) {
            movie.updateWithVideos(result);

            getEventBus().post(new MoviesState.MovieVideosItemsUpdatedEvent(getCallingId(), movie));
        }
    }

    @Override
    public void onError(RetrofitError re) {
        super.onError(re);

        PhilmMovie movie = mMoviesState.getMovie(mId);
        if (movie != null) {
            getEventBus().post(new MoviesState.MovieVideosItemsUpdatedEvent(getCallingId(), movie));
        }
    }

    @Override
    protected int getSource() {
        return NetworkError.SOURCE_TMDB;
    }

    @Override
    protected Object createLoadingProgressEvent(boolean show) {
        return new BaseState.ShowVideosLoadingProgressEvent(getCallingId(), show);
    }
}