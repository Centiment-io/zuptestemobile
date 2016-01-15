

package app.philm.in.tasks;

import com.uwetrottmann.tmdb.entities.MovieResultsPage;

import app.philm.in.model.PhilmMovie;
import app.philm.in.state.MoviesState;

abstract class BaseTmdbPaginatedMovieRunnable extends BaseTmdbPaginatedRunnable<
        MoviesState.MoviePaginatedResult, PhilmMovie, MovieResultsPage> {

    BaseTmdbPaginatedMovieRunnable(int callingId, int page) {
        super(callingId, page);
    }

    @Override
    protected void updatePaginatedResult(
            MoviesState.MoviePaginatedResult result,
            MovieResultsPage tmdbResult) {
        result.items.addAll(getTmdbMovieEntityMapper().mapAll(tmdbResult.results));

        result.page = tmdbResult.page;
        if (tmdbResult.total_pages != null) {
            result.totalPages = tmdbResult.total_pages;
        }
    }

    @Override
    protected MoviesState.MoviePaginatedResult createPaginatedResult() {
        return new MoviesState.MoviePaginatedResult();
    }
}