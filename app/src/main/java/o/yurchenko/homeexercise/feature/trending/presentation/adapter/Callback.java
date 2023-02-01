package o.yurchenko.homeexercise.feature.trending.presentation.adapter;

import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public interface Callback {

    void onClick(Repository repository);
}
