package com.example.nyt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nyt.activities.CatDetailActivity;
import com.example.nyt.model.Article;

import java.util.ArrayList;
import java.util.List;

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to ArticleViewHolder (our custom ViewHolder)
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ArticleViewHolder> {
    // class variable that holds the data that we want to adapt
    private List<Article> articlesToAdapt;
    private List<Article> filteredList;

    public void setData(List<Article> articlesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.articlesToAdapt = articlesToAdapt;
        filteredList = new ArrayList<>(articlesToAdapt);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.article, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        final Article articleAtPosition = articlesToAdapt.get(position);

        holder.headlineTextView.setText(articleAtPosition.getName());
        holder.summaryTextView.setText(articleAtPosition.getOrigin());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("ArticleID", articleAtPosition.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articlesToAdapt.size();
    }

//    @Override
//    public Filter getFilter() {
//        return exampleFilter;
//
//    }
//
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Article> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(articlesToAdapt);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (Article article : articlesToAdapt) {
//                    if (article.getName().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(article);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }


//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            articlesToAdapt.clear();
//            articlesToAdapt.addAll((List) results.values);
//            notifyDataSetChanged();
//
//        }
//    };

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView headlineTextView;
        public TextView summaryTextView;
        public ImageView shareImageView;
        public ImageView bookmarkImageView;
        public ImageView articleImageView;
        public boolean isBookmarked = false;

        // This constructor is used in onCreateViewHolder
        public ArticleViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            headlineTextView = v.findViewById(R.id.newsHeadline);
            summaryTextView = v.findViewById(R.id.newsDetails);
            shareImageView = v.findViewById(R.id.newsShareButton);
            bookmarkImageView = v.findViewById(R.id.newsSaveButton);

            // We can define onClickListener for bookmark button here because it depends on data
            // unique to this ViewHolder (i.e. whether this item has already been bookmarked or not)
            // Technically, we can do everything that we do in onBindViewHolder in here as well.
            bookmarkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isBookmarked) {
                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    } else {
                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    }
                    isBookmarked = !isBookmarked;
                }
            });

        }
    }
}
