package ar.com.wolox.recycler.testclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.com.wolox.recycler.R;
import ar.com.wolox.recycler.library.RecyclerViewAdapter;

public class NewsRecyclerViewAdapter extends RecyclerViewAdapter<RowNews> {

    private NewsRowViewHolder mNewsRowViewHolder;

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        View itemLayoutView;

        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, null);
        return new NewsRowViewHolder(itemLayoutView);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        mNewsRowViewHolder = (NewsRowViewHolder) viewHolder;
        //mNewsRowViewHolder.mTitle.setText(getItems().get(position).getTitle());
        mNewsRowViewHolder.mTitle.setText("TEST");
        //mNewsRowViewHolder.mContent.setText(getItems().get(position).getText());
        mNewsRowViewHolder.mContent.setText("HOLAA!!!");
        mNewsRowViewHolder.mDate.setText("23m");

    }


}
