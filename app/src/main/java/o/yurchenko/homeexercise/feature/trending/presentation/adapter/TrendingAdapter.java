package o.yurchenko.homeexercise.feature.trending.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import o.yurchenko.homeexercise.R;
import o.yurchenko.homeexercise.databinding.TrendingItemBinding;
import o.yurchenko.homeexercise.localstorage.trending.entity.Repository;

public class TrendingAdapter extends ListAdapter<Repository, TrendingAdapter.ViewHolder> {

    private Callback callback;

    public TrendingAdapter(Callback callback) {
        super(new RepositoryDiffCallback());
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TrendingItemBinding binding = TrendingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position), callback);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TrendingItemBinding binding;

        public ViewHolder(@NonNull TrendingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Repository repository, Callback callback) {
            Context context = binding.getRoot().getContext();
            Glide.with(context)
                    .load(repository.getOwner().getAvatarUrl())
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .into(binding.imgAvatar);
            binding.textUsername.setText(repository.getOwner().getLogin());
            binding.textRepositoryName.setText(repository.getName());
            String desc = repository.getDescription() != null ? repository.getDescription() : context.getString(R.string.trending_no_description);
            binding.textRepositoryDesc.setText(desc);
            binding.textRepositoryStars.setText(repository.getStargazersCount());
            binding.getRoot().setOnClickListener(v -> callback.onClick(repository));
        }
    }
}
