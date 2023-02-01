package o.yurchenko.homeexercise.feature.favorites.presentation.adapter;

import o.yurchenko.homeexercise.localstorage.favorites.entity.Favorite;

public interface Callback {

    void onClick(Favorite favorite);
}
