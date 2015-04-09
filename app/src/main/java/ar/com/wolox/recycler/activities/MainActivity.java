package ar.com.wolox.recycler.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ar.com.wolox.recycler.R;
import ar.com.wolox.recycler.testclass.NewsRecyclerViewAdapter;
import ar.com.wolox.recycler.testclass.RowNews;


public class MainActivity extends Activity {

    private ArrayList<RowNews> mTestDataset = new ArrayList<RowNews>();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);


        mLinearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNewsRecyclerViewAdapter= new NewsRecyclerViewAdapter(RowNews.class, new RowNews("Nicola Dille", "I'll be in your neighborhood doing errands...", "f", true, "15m"));
        mRecyclerView.setAdapter(mNewsRecyclerViewAdapter);
        // todo customize animations extending RecyclerView.ItemAnimator class
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTestDataset.add(new RowNews("Nicola Dille", "I'll be in your neighborhood doing errands...", "f", true, "15m"));
        mTestDataset.add(new RowNews("Carmelina Teston", "I'll be in your neighborhood doing errands...", "f", false, "18m"));
        mTestDataset.add(new RowNews("Sanford Hamrick", "I'll be in your neighborhood doing errands...", "f", true, "32m"));
        mTestDataset.add(new RowNews("Brendan Nemeth", "I'll be in your neighborhood doing errands...", "f", true, "9m"));
        mTestDataset.add(new RowNews("Kathryn Seawright", "I'll be in your neighborhood doing errands...", "f", true, "7m"));

        mNewsRecyclerViewAdapter.addAllItems(mTestDataset);
            mNewsRecyclerViewAdapter.addLoadingRow();
            mNewsRecyclerViewAdapter.addLoadingRow();
            mNewsRecyclerViewAdapter.addLoadingRow();
            mNewsRecyclerViewAdapter.addLoadingRow();
            mNewsRecyclerViewAdapter.addLoadingRow();

            mNewsRecyclerViewAdapter.removeLastLoadingRow();
            mNewsRecyclerViewAdapter.removeLastLoadingRow();
            mNewsRecyclerViewAdapter.removeLastLoadingRow();

    }


}
