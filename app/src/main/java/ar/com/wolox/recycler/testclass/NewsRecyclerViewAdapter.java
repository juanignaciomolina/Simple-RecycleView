package ar.com.wolox.recycler.testclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.com.wolox.recycler.R;
import ar.com.wolox.recycler.simpleRecyclerLibrary.SimpleRecyclerAdapter;

public class NewsRecyclerViewAdapter extends SimpleRecyclerAdapter<RowNews> {

    private NewsRowViewHolder mNewsRowViewHolder;

    public NewsRecyclerViewAdapter(RowNews item) {
        super(item);
    }

    // Get an Item's type according (invoked by the SimpleRecyclerAdapter)
    public int simpleGetItemViewType(int position) {
        return 0;
    }

    // Create new views (invoked by the SimpleRecyclerAdapter)
    public RecyclerView.ViewHolder simpleOnCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        View itemLayoutView;
        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, null);
        return new NewsRowViewHolder(itemLayoutView);
    }

    // Replace the contents of a view (invoked by the SimpleRecyclerAdapter)
    public void simpleOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        mNewsRowViewHolder = (NewsRowViewHolder) viewHolder;
        mNewsRowViewHolder.mTitle.setText(getItems().get(position).getTitle());
        mNewsRowViewHolder.mContent.setText(getItems().get(position).getContent());
        mNewsRowViewHolder.mDate.setText("23m");
    }
}