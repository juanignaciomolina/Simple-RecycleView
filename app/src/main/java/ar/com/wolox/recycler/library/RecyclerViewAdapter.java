package ar.com.wolox.recycler.library;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.wolox.recycler.library.entities.RecyclerViewItemInterface;
import ar.com.wolox.recycler.library.modules.ModDatasetManipulators;
import ar.com.wolox.recycler.library.modules.ModLoaders;

public abstract class RecyclerViewAdapter<E extends RecyclerViewItemInterface>
        extends
        RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements
        ModDatasetManipulators.Interface<E>,
        ModLoaders.Interface<E>
{

    //Vars
    private ArrayList<E> mItems = new ArrayList<E>(); //List of items for the recycler
    private E mItemsInstance;

    //Getters and Setters
    public ArrayList<E> getItems() {return mItems;}
    public void setItems(ArrayList<E> mItems) {this.mItems = mItems;}
    public E getItemsInstance() {return mItemsInstance;}
    public void setItemsInstance(E mItemsInstance) {this.mItemsInstance = mItemsInstance;}

    //Constructor
    public RecyclerViewAdapter(E item)
    {
        setItemsInstance(item);
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
    public int getItemCount() {return mItems.size();}


    //** Start of DATASET MANIPULATORS MODULE **
    private ModDatasetManipulators<E> mModuleDatasetManipulators = new ModDatasetManipulators<E>(this);
    protected ModDatasetManipulators<E> getModuleDatasetManipulators() { return mModuleDatasetManipulators; }

    public void addItem(E item) {
        mModuleDatasetManipulators.addItem(item);}
    public void addItemToPos(int position, E item) {
        mModuleDatasetManipulators.addItemToPos(position, item);}
    public void addAllItems(Collection<E> itemsArray) {
        mModuleDatasetManipulators.addAllItems(itemsArray);}
    public void removeItemByPos(int position) {
        mModuleDatasetManipulators.removeItemByPos(position);}
    public int getItemPosition(E itemToLocate) {
        return mModuleDatasetManipulators.getItemPosition(itemToLocate);}
    public void removeItem(E itemToRemove) {
        mModuleDatasetManipulators.removeItem(itemToRemove);}
    public void moveItem(int fromPos, int toPos) {
        mModuleDatasetManipulators.moveItem(fromPos, toPos);}
    //** End of DATASET MANIPULATORS MODULE **

    //** Start of LOADERS MODULE **
    private ModLoaders<E> mModuleLoaders = new ModLoaders<E>(this);
    public ModLoaders<E> getModuleLoaders() { return mModuleLoaders; }

    public void addLoadingRow() {
        mModuleLoaders.addLoadingRow();}
    public void addLoadingRow(int position) {
        mModuleLoaders.addLoadingRow(position);}
    public void removeLoadingRow() {
        mModuleLoaders.removeLoadingRow();}
    public void removeLoadingRow(int itemPosition) {
        mModuleLoaders.removeLoadingRow(itemPosition);}
    public void removeLoadingRowByLoaderPos(int loaderPosition) {
        mModuleLoaders.removeLoadingRowByLoaderPos(loaderPosition);}
    public boolean isLoader(int position) {
        return mModuleLoaders.isLoader(position);}
    //** End of LOADERS MODULE **
}
