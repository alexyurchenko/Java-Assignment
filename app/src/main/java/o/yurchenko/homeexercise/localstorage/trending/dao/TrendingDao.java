package o.yurchenko.homeexercise.localstorage.trending.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

@Dao
public interface TrendingDao {

    @Query("SELECT * FROM trending_repositories ORDER BY stargazersCount")
    Flowable<List<Repository>> repositories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Repository> repositories);

    @Query("SELECT * FROM trending_repositories WHERE id = :id")
    Single<Repository> repositoryById(long id); // todo maybe?

    @Query("DELETE FROM trending_repositories")
    Completable clearRepositories();
}
