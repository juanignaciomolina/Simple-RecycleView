package ar.com.wolox.recycler.testclass;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.wolox.recycler.R;

public class NewsRowViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mContent;
    public ImageView mImage;
    public ImageView mLike;
    public TextView mDate;

    public NewsRowViewHolder(View itemLayoutView) {
        super(itemLayoutView);
        this.mTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
        this.mContent = (TextView) itemLayoutView.findViewById(R.id.item_content);
        this.mImage = (ImageView) itemLayoutView.findViewById(R.id.item_image);
        this.mLike = (ImageView) itemLayoutView.findViewById(R.id.item_like);
        this.mDate = (TextView) itemLayoutView.findViewById(R.id.item_date);

    }

}