package ar.com.wolox.recycler.library;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.wolox.recycler.library.entities.RecyclerViewItemInterface;

public abstract class RecyclerViewAdapter<E extends RecyclerViewItemInterface> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<E> mItems = new ArrayList<E>(); //List of items for the recycler
    private ArrayList<Integer> mLoaders = new ArrayList<Integer>(); //List of item's positions

    private E mItemsInstance;
    public RecyclerViewAdapter(E item)
    {
        this.mItemsInstance = item;
    }
    //public Class<E> getItemsType(){return mItemsType;}

    /*********
    The custom adapter that extends this adapter MUST implement this methods


    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    *********/

    // Return the size of your mItemsNews (invoked by the layout manager)
    @Override
    public int getItemCount() { return mItems.size(); }

    public ArrayList<E> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<E> mItems) {
        this.mItems = mItems;
    }

    //** Start of DATASET MANIPULATORS **
    public void addItem(E item) {
        addItemToPos(getItemCount(), item);
    }

    public void addItemToPos(int position, E item) {
        if (position <= getItemCount()) {
            mItems.add(position, item);
            notifyItemInserted(position);
        }
    }

    public void addAllItems(Collection<E> itemsArray) {
        for(E item : itemsArray) {
            addItem(item);
        }
    }

    public void removeItemByPos(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public int getItemPosition(E itemToLocate) {
        return mItems.indexOf(itemToLocate);
    }

    public void removeItem(E itemToRemove) {
        int position = getItemPosition(itemToRemove);
        if (position >= 0) removeItemByPos(position);
    }

    public void moveItem(int fromPos, int toPos) {
        E temp = mItems.remove(fromPos);
        mItems.add(toPos, temp);
    }

    //** End of DATASET MANIPULATORS **

    //** Start of LOADERS **

    @SuppressWarnings("unchecked")
    public void addLoadingRow() {
        int itemCount = getItemCount();
        this.addItemToPos(itemCount,(E) mItemsInstance.create());
        mLoaders.add(itemCount);
    }

    @SuppressWarnings("unchecked")
    public void addLoadingRow(int position) {
        this.addItemToPos(position, (E) mItemsInstance.create());
        //Ordered list
        int insertPos = mLoaders.indexOf(position);
        if (insertPos >= 0) {
            updateLoadersPosition(insertPos + 1, mLoaders.size() - 1, +1);
            mLoaders.add(insertPos, position);
        }
        else mLoaders.add(position);
    }

    public void removeLoadingRow() {
        removeLoadingRowByLoaderPos(mLoaders.size() - 1);
    }

    public void removeLoadingRow(int itemPosition) {
        removeLoadingRowByLoaderPos(mLoaders.lastIndexOf(itemPosition));
    }

    protected void removeLoadingRowByLoaderPos(int loaderPosition) {
        int itemPosition = mLoaders.get(loaderPosition);
        if (itemPosition >= 0 && isLoader(itemPosition)) {
            removeItemByPos(itemPosition);
            updateLoadersPosition(loaderPosition, mLoaders.size() - 1, -1);
            mLoaders.remove(loaderPosition);
        }
    }

    private void updateLoadersPosition(int start, int end, int value) {
        if ( start < 0 || end > mLoaders.size() ) return;

        int i = start;

        while ( i <= end ) {
            mLoaders.set(i, mLoaders.get(i) + value);
            i++;
        }
    }

    public boolean isLoader(int position) {
        for(int aLoaderPosition : mLoaders) {
            if (aLoaderPosition == position) return true;
        }
    return false;
    }

    //** End of LOADERS **

}
