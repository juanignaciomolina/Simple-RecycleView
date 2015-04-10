package ar.com.wolox.recycler.library;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    // Return the size of your dataset (invoked by the layout manager)
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
        addLoadingRow(mItems.size());
    }

    @SuppressWarnings("unchecked")
    public void addLoadingRow(int position) {
        insertOrderedRow(mLoaders, position);
        this.addItemToPos(position, (E) mItemsInstance.create());
    }

    public void removeLoadingRow() {
        removeLoadingRowByLoaderPos(mLoaders.size() - 1);
    }

    public void removeLoadingRow(int itemPosition) {
        removeLoadingRowByLoaderPos(mLoaders.indexOf(itemPosition));
    }

    protected void removeLoadingRowByLoaderPos(int loaderPosition) {
        int itemPosition = mLoaders.get(loaderPosition);
        if (itemPosition >= 0 && isLoader(itemPosition)) {
            removeOrderedRow(mLoaders, loaderPosition);
            removeItemByPos(itemPosition);
        }
    }

    public boolean isLoader(int position) {
        for(int aLoaderPosition : mLoaders) {
            if (aLoaderPosition == position) return true;
        }
    return false;
    }

    //** End of LOADERS **

    //** Start of UTILS **

    private int insertOrderedRow(ArrayList<Integer> itemsList, int value){
        int i = 0;
        int size = itemsList.size();
        while(i < size && itemsList.get(i) < value){
            i++;
        }
        if(i < size){

            itemsList.add(itemsList.get(size - 1) + 1);

            for(int j = size - 1; j > i; j--){
                itemsList.set(j, itemsList.get(j - 1) + 1); //+1 because there is a new row
            }
            itemsList.set(i, value);
        }
        else itemsList.add(value);

        Log.d("Loaders array +", String.valueOf(itemsList));
        return i;
    }

    private int removeOrderedRow(ArrayList<Integer> itemsList, int positionToRemove) {
        int size = itemsList.size();

        if(positionToRemove < size && positionToRemove >= 0){
            for(int j = size - 1; j > positionToRemove; j--){
                itemsList.set(j, itemsList.get(j) - 1); //-1 because there is one less row
            }

            itemsList.remove(positionToRemove);
        }
        else return -1;

        Log.d("Loaders array -", String.valueOf(itemsList));
        return positionToRemove;
    }

    //** End of UTILS **
}
