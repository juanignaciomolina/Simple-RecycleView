package ar.com.wolox.recycler.simpleRecyclerLibrary.entities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import ar.com.wolox.recycler.R;

public class LoadingRowViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar mProgressBar;

    public LoadingRowViewHolder(View itemLayoutView) {
        super(itemLayoutView);
        this.mProgressBar = (ProgressBar) itemLayoutView.findViewById(R.id.loading_indicator);
    }

}