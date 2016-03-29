package com.johnnymolina.workoutswithimgur.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fivehundredpx.greedolayout.GreedoLayoutSizeCalculator;
import com.johnnymolina.workoutswithimgur.R;
import com.johnnymolina.workoutswithimgur.network.api.model.Image;

import java.util.List;

public class DownloadedMediaAdapter
            extends RecyclerView.Adapter<DownloadedMediaAdapter.ViewHolder>
            implements GreedoLayoutSizeCalculator.SizeCalculatorDelegate {

        public DownloadedMediaAdapter(Context context, List<Image> imageList){
            this.mContext = context;
            this.mImageList = imageList;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView mImageView;

            public ViewHolder(ImageView imageView) {
                super(imageView);
                mImageView = imageView;
            }
        }

        private List<Image> mImageList;
        private Context mContext;

        public void setImages(List<Image> imageList){
            this.mImageList = imageList;
        }

        @Override
        public double aspectRatioForIndex(int index) {
            // Precaution, have better handling for this in greedo-layout
            if (index >= getItemCount()) return 1.0;
            return  mImageList.get(index).getWidth() / (double) mImageList.get(index).getHeight();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));

            return new ViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Image image = mImageList.get(position);
            String imageThumbnailUrl = "http://i.imgur.com/" + image.getId() + "t.png";

            Glide.with(mContext)
                        .load(imageThumbnailUrl)
                        .asBitmap()
                        .fitCenter()
                        .placeholder(mContext.getResources().getDrawable(R.drawable.ic_image_black_24dp))
                        .into(viewHolder.mImageView);
        }

        @Override
        public int getItemCount() {
            if (mImageList!=null) {
                return mImageList.size();
            }
            return 0;
        }


    }
