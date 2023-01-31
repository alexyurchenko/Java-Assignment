package o.yurchenko.homeexercise.feature.favorites.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import o.yurchenko.homeexercise.R;
import o.yurchenko.homeexercise.databinding.FavoriteItemBinding;
import o.yurchenko.homeexercise.localstorage.entity.Favorite;

public class FavoriteAdapter extends ListAdapter<Favorite, FavoriteAdapter.ViewHolder> {

    private Callback callback;

    public FavoriteAdapter(Callback callback) {
        super(new FavoriteDiffCallback());
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoriteItemBinding binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position), callback);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private FavoriteItemBinding binding;

        public ViewHolder(@NonNull FavoriteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Favorite favorite, Callback callback) {
            Context context = binding.getRoot().getContext();
            Glide.with(context)
                    .load(favorite.getAvatarUrl())
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .into(binding.imgAvatar);
            binding.textUsername.setText(favorite.getLogin());
            binding.textRepositoryName.setText(favorite.getName());
            String desc = favorite.getDescription() != null ? favorite.getDescription() : context.getString(R.string.trending_no_description);
            binding.textRepositoryDesc.setText(desc);
            binding.textRepositoryStars.setText(favorite.getStargazersCount());
            binding.buttonRemove.setOnClickListener(v -> callback.onClick(favorite));
        }
    }
}
