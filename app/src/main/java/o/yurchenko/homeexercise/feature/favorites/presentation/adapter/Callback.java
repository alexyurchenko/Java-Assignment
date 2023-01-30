package o.yurchenko.homeexercise.feature.favorites.presentation.adapter;

import o.yurchenko.homeexercise.localstorage.Favorite;

public interface Callback {

    void onClick(Favorite favorite);
}
