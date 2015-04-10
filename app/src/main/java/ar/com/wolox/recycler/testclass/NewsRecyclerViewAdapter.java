package ar.com.wolox.recycler.testclass;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.com.wolox.recycler.R;
import ar.com.wolox.recycler.library.RecyclerViewAdapter;
import ar.com.wolox.recycler.library.entities.LoadingRowViewHolder;

public class NewsRecyclerViewAdapter extends RecyclerViewAdapter<RowNews> {

    private NewsRowViewHolder mNewsRowViewHolder;

    public NewsRecyclerViewAdapter(RowNews item) {
        super(item);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoader(position)) return 1;
        else return 0;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        View itemLayoutView;

        switch (viewType) {
            default:
            case 0:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_news, null);
                return new NewsRowViewHolder(itemLayoutView);
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_loading, null);
                return new LoadingRowViewHolder(itemLayoutView);
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {
            default:
            case 0:

                mNewsRowViewHolder = (NewsRowViewHolder) viewHolder;
                //mNewsRowViewHolder.mTitle.setText(getItems().get(position).getTitle());
                mNewsRowViewHolder.mTitle.setText(getItems().get(position).getTitle());
                //mNewsRowViewHolder.mContent.setText(getItems().get(position).getText());
                mNewsRowViewHolder.mContent.setText(getItems().get(position).getContent());
                mNewsRowViewHolder.mDate.setText("23m");
                break;

            case 1:
                //Do something with the loading row if needed
        }
    }


}
