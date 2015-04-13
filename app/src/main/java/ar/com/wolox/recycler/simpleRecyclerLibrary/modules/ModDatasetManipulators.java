package ar.com.wolox.recycler.simpleRecyclerLibrary.modules;

import java.util.Collection;

import ar.com.wolox.recycler.simpleRecyclerLibrary.SimpleRecyclerAdapter;
import ar.com.wolox.recycler.simpleRecyclerLibrary.entities.SimpleRecyclerItemInterface;

public class ModDatasetManipulators<E extends SimpleRecyclerItemInterface>{

    public interface Interface<E> {
        public void addItem(E item);
        public void addItemToPos(int position, E item);
        public void addAllItems(Collection<E> itemsArray);
        public void removeItemByPos(int position);
        public int getItemPosition(E itemToLocate);
        public void removeItem(E itemToRemove);
        public void moveItem(int fromPos, int toPos);
    }

    //Vars
    SimpleRecyclerAdapter<E> mRecyclerViewAdapter;

    //Constructor
    public ModDatasetManipulators(SimpleRecyclerAdapter<E> aRecyclerViewAdapter) {
        this.mRecyclerViewAdapter = aRecyclerViewAdapter;
    }

    // Getters and Setters
    public SimpleRecyclerAdapter<E> getRecyclerViewAdapter() {
        return mRecyclerViewAdapter;
    }

    public void setRecyclerViewAdapter(SimpleRecyclerAdapter<E> mRecyclerViewAdapter) {
        this.mRecyclerViewAdapter = mRecyclerViewAdapter;
    }

    //** Start of DATASET MANIPULATORS MODULE methods **
    public void addItem(E item) {
        addItemToPos(mRecyclerViewAdapter.getItemCount(), item);
    }

    public void addItemToPos(int position, E item) {
        if (position <= mRecyclerViewAdapter.getItemCount()) {
            mRecyclerViewAdapter.getItems().add(position, item);
            mRecyclerViewAdapter.notifyItemInserted(position);
        }
    }

    public void addAllItems(Collection<E> itemsArray) {
        for(E item : itemsArray) {
            addItem(item);
        }
    }

    public void removeItemByPos(int position) {
        mRecyclerViewAdapter.getItems().remove(position);
        mRecyclerViewAdapter.notifyItemRemoved(position);
    }

    public int getItemPosition(E itemToLocate) {
        return mRecyclerViewAdapter.getItems().indexOf(itemToLocate);
    }

    public void removeItem(E itemToRemove) {
        int position = getItemPosition(itemToRemove);
        if (position >= 0) removeItemByPos(position);
    }

    public void moveItem(int fromPos, int toPos) {
        E temp = mRecyclerViewAdapter.getItems().remove(fromPos);
        mRecyclerViewAdapter.getItems().add(toPos, temp);
    }
    //** End of DATASET MANIPULATORS MODULE methods **
}
