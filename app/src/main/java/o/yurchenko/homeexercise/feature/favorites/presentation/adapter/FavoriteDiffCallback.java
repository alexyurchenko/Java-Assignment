package o.yurchenko.homeexercise.feature.favorites.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import o.yurchenko.homeexercise.localstorage.entity.Favorite;

public class FavoriteDiffCallback extends DiffUtil.ItemCallback<Favorite> {

    @Override
    public boolean areItemsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Favorite oldItem, @NonNull Favorite newItem) {
        return oldItem.equals(newItem);
    }
}
