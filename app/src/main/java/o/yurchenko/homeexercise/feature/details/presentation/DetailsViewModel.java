package o.yurchenko.homeexercise.feature.details.presentation;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import o.yurchenko.homeexercise.feature.details.api.DetailsRepository;
import o.yurchenko.homeexercise.feature.trending.api.model.Repository;

@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private final DetailsRepository detailsRepository;

    private final PublishSubject<Boolean> favoriteSubject = PublishSubject.create();
    private final PublishSubject<Throwable> errorSubject = PublishSubject.create(); // todo map errors

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    DetailsViewModel(DetailsRepository repository) {
        this.detailsRepository = repository;
    }

    public Observable<Boolean> getFavoriteSubject() {
        return favoriteSubject.hide();
    }

    public Observable<Throwable> getErrorSubject() {
        return errorSubject.hide();
    }

    public void isFavorite(long id) {
        compositeDisposable.add(detailsRepository.isFavorite(id)
                        .subscribe(favoriteSubject::onNext, errorSubject::onNext));
    }

    public void addToFavorites(Repository repository) {
        compositeDisposable.add(detailsRepository.addFavorite(repository)
                .subscribe(() -> favoriteSubject.onNext(true), errorSubject::onNext));
    }

    public void removeFromFavorites(long id) {
        compositeDisposable.add(detailsRepository.removeFavorite(id)
                .subscribe(() -> favoriteSubject.onNext(false), errorSubject::onNext));
    }
}
