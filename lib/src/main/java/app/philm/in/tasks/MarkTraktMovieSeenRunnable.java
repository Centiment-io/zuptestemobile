

package app.philm.in.tasks;

import com.jakewharton.trakt.Trakt;
import com.jakewharton.trakt.entities.Response;
import com.jakewharton.trakt.services.MovieService;

import app.philm.in.model.PhilmMovie;
import app.philm.in.network.NetworkError;
import retrofit.RetrofitError;

public class MarkTraktMovieSeenRunnable extends BaseTraktActionRunnable {

    public MarkTraktMovieSeenRunnable(int callingId, String... ids) {
        super(callingId, ids);
    }

    @Override
    public Response doTraktCall(Trakt trakt, MovieService.Movies body) throws RetrofitError {
        return trakt.movieService().seen(body);
    }

    @Override
    protected void movieRequiresModifying(PhilmMovie movie) {
        movie.setWatched(true);
    }

    @Override
    protected int getSource() {
        return NetworkError.SOURCE_TRAKT;
    }
}