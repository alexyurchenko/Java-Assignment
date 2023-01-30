package o.yurchenko.homeexercise.localstorage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import o.yurchenko.homeexercise.localstorage.Favorite;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite ORDER BY stargazersCount")
    public Flowable<List<Favorite>> favorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addFavorite(Favorite favorite);
}
