package o.yurchenko.homeexercise.feature.favorites.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import o.yurchenko.homeexercise.feature.favorites.api.FavoritesRepository;
import o.yurchenko.homeexercise.feature.favorites.data.FavoritesRepositoryImpl;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class FavoritesModule {

    @Binds
    @ViewModelScoped
    public abstract FavoritesRepository bindFavoritesRepository(FavoritesRepositoryImpl favoritesRepositoryImpl);
}
