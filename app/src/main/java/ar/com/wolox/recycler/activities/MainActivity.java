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

    private ArrayList<RowNews> mItems = new ArrayList<RowNews>();
    private ArrayList<RowNews> mTestDataset = new ArrayList<RowNews>();

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;

    private RowNews itemsNews[] = {
            new RowNews("Nicola Dille", "I'll be in your neighborhood doing errands...", "f", true, "15m"),
            new RowNews("Carmelina Teston", "I'll be in your neighborhood doing errands...", "f", false, "18m"),
            new RowNews("Sanford Hamrick", "I'll be in your neighborhood doing errands...", "f", true, "32m"),
            new RowNews("Jina Hersom", "I'll be in your neighborhood doing errands...", "f", false, "42m"),
            new RowNews("Brendan Nemeth", "I'll be in your neighborhood doing errands...", "f", true, "9m"),
            new RowNews("Stanton Riggenbach", "I'll be in your neighborhood doing errands...", "f", false, "17m"),
            new RowNews("Shaunna Drozd", "I'll be in your neighborhood doing errands...", "f", true, "14m"),
            new RowNews("Thresa Lashley", "I'll be in your neighborhood doing errands...", "f", false, "29m"),
            new RowNews("Shante Evensen", "I'll be in your neighborhood doing errands...", "f", true, "35m"),
            new RowNews("Jesus Sera", "I'll be in your neighborhood doing errands...", "f", false, "52m"),
            new RowNews("Kathryn Seawright", "I'll be in your neighborhood doing errands...", "f", true, "7m"),
            new RowNews("Jacquline Rochelle", "I'll be in your neighborhood doing errands...", "f", false, "22m")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);


        mLinearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNewsRecyclerViewAdapter= new NewsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mNewsRecyclerViewAdapter);
        // todo customize animations extending RecyclerView.ItemAnimator class
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mTestDataset.add(new RowNews("Nicola Dille", "I'll be in your neighborhood doing errands...", "f", true, "15m"));

        mNewsRecyclerViewAdapter.addItemsArray(mTestDataset);
    }


}
