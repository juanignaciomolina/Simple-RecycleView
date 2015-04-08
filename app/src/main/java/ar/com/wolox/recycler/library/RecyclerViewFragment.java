package ar.com.wolox.recycler.library;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

public class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    public static final int ITEMS_PER_PAGE = 20;
    public static final int DEFAULT_SCROLL_AMOUNT = 10;

    protected enum Status { LOADING, LOADED, ERROR }

    @Override
    public void onRefresh() {
        //Execute reload logic
    }

}
