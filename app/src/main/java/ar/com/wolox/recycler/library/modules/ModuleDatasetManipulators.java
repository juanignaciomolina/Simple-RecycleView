package ar.com.wolox.recycler.library.modules;

import java.util.Collection;

import ar.com.wolox.recycler.library.RecyclerViewAdapter;
import ar.com.wolox.recycler.library.entities.RecyclerViewItemInterface;

public class ModuleDatasetManipulators<E extends RecyclerViewItemInterface>
        implements ModuleDatasetManipulatorsInt<E>{

    RecyclerViewAdapter<E> mRecyclerViewAdapter;

    public ModuleDatasetManipulators(RecyclerViewAdapter<E> aRecyclerViewAdapter) {
        this.mRecyclerViewAdapter = aRecyclerViewAdapter;
    }

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

}