package o.yurchenko.homeexercise.feature.trending.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import o.yurchenko.homeexercise.feature.trending.api.TrendingRepository;
import o.yurchenko.homeexercise.feature.trending.data.TrendingRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class TrendingModule {

    @Binds
    @ViewModelScoped
    public abstract TrendingRepository bindTrendingRepository(TrendingRepositoryImpl trendingRepositoryImpl);
}
