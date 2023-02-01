package o.yurchenko.homeexercise.localstorage.trending.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import o.yurchenko.homeexercise.localstorage.AppDatabase;
import o.yurchenko.homeexercise.localstorage.trending.dao.TrendingDao;

@Module
@InstallIn(SingletonComponent.class)
public class TrendingDatabaseModule {

    @Singleton
    @Provides
    public TrendingDao provideTrendingDao(AppDatabase database) {
        return database.trendingDao();
    }
}
