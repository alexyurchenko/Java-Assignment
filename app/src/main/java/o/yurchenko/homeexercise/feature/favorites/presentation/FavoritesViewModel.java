package o.yurchenko.homeexercise.feature.favorites.presentation;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import o.yurchenko.homeexercise.feature.favorites.api.FavoritesRepository;
import o.yurchenko.homeexercise.localstorage.Favorite;

@HiltViewModel
public class FavoritesViewModel extends ViewModel {

    private FavoritesRepository favoritesRepository;

    private final PublishSubject<List<Favorite>> successSubject = PublishSubject.create();

    private final PublishSubject<Throwable> errorSubject = PublishSubject.create(); // todo map errors

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    FavoritesViewModel(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
        favorites();
    }

    public Observable<List<Favorite>> getFavorites() {
        return successSubject.hide();
    }

    public Observable<Throwable> getError() {
        return errorSubject.hide();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public void favorites() {
        compositeDisposable.add(favoritesRepository.favorites()
                .subscribe(successSubject::onNext, errorSubject::onNext));
    }

    public void remove(long id) {
        compositeDisposable.add(favoritesRepository.remove(id).andThen(favoritesRepository.favorites())
                .subscribe(successSubject::onNext, errorSubject::onNext));
    }
}
