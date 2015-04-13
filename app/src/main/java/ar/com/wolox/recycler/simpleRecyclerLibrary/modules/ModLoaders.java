package ar.com.wolox.recycler.simpleRecyclerLibrary.modules;

import java.util.ArrayList;

import ar.com.wolox.recycler.R;
import ar.com.wolox.recycler.simpleRecyclerLibrary.SimpleRecyclerAdapter;
import ar.com.wolox.recycler.simpleRecyclerLibrary.entities.SimpleRecyclerItemInterface;

public class ModLoaders<E extends SimpleRecyclerItemInterface> extends ModDatasetManipulators<E> {

    public interface Interface<E> {
        public void addLoadingRow();
        public void addLoadingRow(int position);
        public void removeLoadingRow();
        public void removeLoadingRow(int itemPosition);
        public void removeLoadingRowByLoaderPos(int loaderPosition);
        public boolean isLoader(int position);
    }

    //Vars
    private ArrayList<Integer> mLoaders = new ArrayList<Integer>(); //List of item's positions
    private int mLoaderLayout = R.layout.item_loading;

    //Constructor
    public ModLoaders(SimpleRecyclerAdapter<E> aRecyclerViewAdapter) {
        super(aRecyclerViewAdapter);
    }

    //Getters and Setters
    public int getLoaderLayout() {return mLoaderLayout;}
    public void setLoaderLayout(int loaderLayout) { this.mLoaderLayout = loaderLayout;}

    //** Start of LOADERS MODULE methods **
    public void addLoadingRow() {
        addLoadingRow(super.getRecyclerViewAdapter().getItems().size());
    }

    @SuppressWarnings("unchecked")
    public void addLoadingRow(int position) {
        ModUtils.insertOrderedRow(mLoaders, position);
        super.addItemToPos(position, (E) super.getRecyclerViewAdapter().getItemsInstance().create());
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
            super.removeItemByPos(itemPosition);
        }
    }

    public boolean isLoader(int position) {
        for (int aLoaderPosition : mLoaders) {
            if (aLoaderPosition == position) return true;
        }
        return false;
    }
    //** End of LOADERS MODULE methods **
}
