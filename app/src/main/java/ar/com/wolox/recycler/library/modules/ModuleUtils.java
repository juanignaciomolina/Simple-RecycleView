package ar.com.wolox.recycler.library.modules;

import android.util.Log;

import java.util.ArrayList;

import ar.com.wolox.recycler.library.RecyclerViewAdapter;
import ar.com.wolox.recycler.library.entities.RecyclerViewItemInterface;

public class ModuleUtils<E extends RecyclerViewItemInterface> implements ModuleUtilsInt<E> {

    RecyclerViewAdapter<E> mRecyclerViewAdapter;

    public ModuleUtils(RecyclerViewAdapter<E> aRecyclerViewAdapter) {
        this.mRecyclerViewAdapter = aRecyclerViewAdapter;
    }

    public int insertOrderedRow(ArrayList<Integer> itemsList, int value){
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

    public int removeOrderedRow(ArrayList<Integer> itemsList, int positionToRemove) {
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

}
