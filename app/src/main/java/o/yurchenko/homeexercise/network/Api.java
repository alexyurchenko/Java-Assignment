package o.yurchenko.homeexercise.network;

import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.feature.trending.data.dto.RepositoriesDto;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("search/repositories")
    Single<RepositoriesDto> repositories(
            @Query("q") String q,
            @Query("sort") String sort
    );
}
