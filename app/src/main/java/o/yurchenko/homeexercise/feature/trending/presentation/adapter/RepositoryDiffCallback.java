package o.yurchenko.homeexercise.feature.trending.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public class RepositoryDiffCallback extends DiffUtil.ItemCallback<Repository> {

    @Override
    public boolean areItemsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Repository oldItem, @NonNull Repository newItem) {
        return oldItem.equals(newItem);
    }
}
