package o.yurchenko.homeexercise.localstorage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import o.yurchenko.homeexercise.localstorage.Favorite;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite ORDER BY stargazersCount")
    Single<List<Favorite>> favorites();

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id)")
    Single<Boolean> exist(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addFavorite(Favorite favorite);

    @Query("DELETE FROM favorite WHERE id = :id")
    Completable removeById(long id);
}
