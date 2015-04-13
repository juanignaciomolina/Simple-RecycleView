package ar.com.wolox.recycler.library.modules;

import java.util.ArrayList;

import ar.com.wolox.recycler.library.RecyclerViewAdapter;
import ar.com.wolox.recycler.library.entities.RecyclerViewItemInterface;

public class ModLoaders<E extends RecyclerViewItemInterface> {

    public interface Interface<E> {
        public void addLoadingRow();
        public void addLoadingRow(int position);
        public void removeLoadingRow();
        public void removeLoadingRow(int itemPosition);
        public void removeLoadingRowByLoaderPos(int loaderPosition);
        public boolean isLoader(int position);
    }

    RecyclerViewAdapter<E> mRecyclerViewAdapter;
    private ArrayList<Integer> mLoaders = new ArrayList<Integer>(); //List of item's positions

    public ModLoaders(RecyclerViewAdapter<E> aRecyclerViewAdapter) {
        this.mRecyclerViewAdapter = aRecyclerViewAdapter;
    }

    public void addLoadingRow() {
        addLoadingRow(mRecyclerViewAdapter.getItems().size());
    }

    @SuppressWarnings("unchecked")
    public void addLoadingRow(int position) {
        ModUtils.insertOrderedRow(mLoaders, position);
        mRecyclerViewAdapter.addItemToPos(position, (E) mRecyclerViewAdapter.getItemsInstance().create());
    }

    public void removeLoadingRow() {
        removeLoadingRowByLoaderPos(mLoaders.size() - 1);
    }

    public void removeLoadingRow(int itemPosition) {
        removeLoadingRowByLoaderPos(mLoaders.indexOf(itemPosition));
    }

    public void removeLoadingRowByLoaderPos(int loaderPosition) {
        int itemPosition = mLoaders.get(loaderPosition);
        if (itemPosition >= 0 && isLoader(itemPosition)) {
            ModUtils.removeOrderedRow(mLoaders, loaderPosition);
            mRecyclerViewAdapter.removeItemByPos(itemPosition);
        }
    }

    public boolean isLoader(int position) {
        for (int aLoaderPosition : mLoaders) {
            if (aLoaderPosition == position) return true;
        }
        return false;
    }

}
