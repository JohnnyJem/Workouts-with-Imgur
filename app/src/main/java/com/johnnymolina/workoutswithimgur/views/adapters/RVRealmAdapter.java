package com.johnnymolina.workoutswithimgur.views.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.network.api.model.realm.RealmAlbum;
import com.johnnymolina.workoutswithimgur.network.api.model.realm.RealmImage;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

public class RVRealmAdapter
            extends RealmBasedRecyclerViewAdapter<RealmAlbum, RVRealmAdapter.ViewHolder> {
    Context context;
    Resources resources;

        public RVRealmAdapter(
                Context context,
                RealmResults<RealmAlbum> realmResults,
                boolean automaticUpdate,
                boolean animateIdType) {
            super(context, realmResults, automaticUpdate, animateIdType);
            this.context = context;
            resources = context.getResources();
        }

        public class ViewHolder extends RealmViewHolder {

            public ImageView image;
            public TextView title;
            public TextView description;

            public ViewHolder(View container) {
                super(container);
                this.image = (ImageView) container.findViewById(R.id.thumbnail);
                this.title = (TextView) container.findViewById(R.id.album_title);
                this.description = (TextView) container.findViewById(R.id.album_description);
            }
        }

        @Override
        public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
            View v = inflater.inflate(R.layout.grid_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
            final RealmAlbum realmAlbum = realmResults.get(position);
            viewHolder.title.setText(realmAlbum.getImages().get(position).getTitle());
            viewHolder.description.setText(realmAlbum.getImages().get(position).getDescription());
            final RealmList<RealmImage> realmImages = realmAlbum.getImages();
            if (realmImages != null && !realmImages.isEmpty()) {
                Glide.with(context)
                        .load(realmImages.get(position).getLink())
                        .placeholder(context.getResources().getDrawable(R.drawable.ic_image_black_24dp))
                        .into(viewHolder.image);
            } else {
                viewHolder.image.setImageResource(R.drawable.ic_image_black_24dp);
            }
        }

    }