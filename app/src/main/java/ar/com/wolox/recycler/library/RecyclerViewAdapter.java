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
        insertOrderedRow(mLoaders, position);
        //mLoaders.add(position);
        //quicksort(mLoaders);
        //updateLoadersPosition(mLoaders.indexOf(position) + 1, mLoaders.size() - 1 , +1);
        this.addItemToPos(position, (E) mItemsInstance.create());
        //Ordered list
        //int insertPos = mLoaders.indexOf(position);
        //if (insertPos >= 0) {
        //    updateLoadersPosition(insertPos + 1, mLoaders.size() - 1, +1);
        //    mLoaders.add(insertPos, position);
        //}
        //else mLoaders.add(position);
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
            removeOrderedRow(mLoaders, loaderPosition);
            removeItemByPos(itemPosition);
            //updateLoadersPosition(loaderPosition, mLoaders.size() - 1, -1);
            //mLoaders.remove(loaderPosition);
        }
    }

    private void updateLoadersPosition(int start, int end, int value) {
        if ( start < 0 || end >= mLoaders.size() ) return;

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

    /**
     * This method sort the input ArrayList using quick sort algorithm.
     * @param input the ArrayList of integers.
     * @return sorted ArrayList of integers.
     */
    /*
    private ArrayList<Integer> quicksort(ArrayList<Integer> input){

        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        int pivot = input.get(middle);

        ArrayList<Integer> less = new ArrayList<Integer>();
        ArrayList<Integer> greater = new ArrayList<Integer>();

        for (int i = 0; i < input.size(); i++) {
            if(input.get(i) <= pivot){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quicksort(less), pivot, quicksort(greater));
    }
    */

    /**
     * Join the less array, pivot integer, and greater array
     * to single array.
     * @param less integer ArrayList with values less than pivot.
     * @param pivot the pivot integer.
     * @param greater integer ArrayList with values greater than pivot.
     * @return the integer ArrayList after join.
     */
    /*
    private ArrayList<Integer> concatenate(ArrayList<Integer> less, int pivot, ArrayList<Integer> greater){

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < less.size(); i++) {
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) {
            list.add(greater.get(i));
        }

        return list;
    }
    */
    //** End of UTILS **
}
