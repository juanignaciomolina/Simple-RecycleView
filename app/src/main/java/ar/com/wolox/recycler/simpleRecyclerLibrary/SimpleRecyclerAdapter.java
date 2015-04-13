package ar.com.wolox.recycler.simpleRecyclerLibrary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.wolox.recycler.simpleRecyclerLibrary.entities.LoadingRowViewHolder;
import ar.com.wolox.recycler.simpleRecyclerLibrary.entities.SimpleRecyclerItemInterface;
import ar.com.wolox.recycler.simpleRecyclerLibrary.modules.ModDatasetManipulators;
import ar.com.wolox.recycler.simpleRecyclerLibrary.modules.ModLoaders;

public abstract class SimpleRecyclerAdapter<E extends SimpleRecyclerItemInterface>
        extends
        RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements
        ModDatasetManipulators.Interface<E>,
        ModLoaders.Interface<E>
{

    //Constants
    private final int TYPE_LOADER = 10001;

    //Vars
    private ArrayList<E> mItems = new ArrayList<E>(); //List of items for the recycler
    private E mItemsInstance;

    //Getters and Setters
    public ArrayList<E> getItems() {return mItems;}
    public void setItems(ArrayList<E> mItems) {this.mItems = mItems;}
    public E getItemsInstance() {return mItemsInstance;}
    public void setItemsInstance(E mItemsInstance) {this.mItemsInstance = mItemsInstance;}

    //Constructor
    public SimpleRecyclerAdapter(E item) {setItemsInstance(item);}

    //** Start of ABSTRACTS AND OVERRIDES **

        // * Return dataset's size *
        // Invoked by the layout manager (required by the RecyclerView.Adapter)
        @Override
        public int getItemCount() {return mItems.size();}

        // * Get items type *
        // Invoked by the layout manager
        @Override
        public int getItemViewType(int position) {
            if (isLoader(position)) return TYPE_LOADER;
            return simpleGetItemViewType(position);
        }
        // Custom behaviour defined by the child class
        public abstract int simpleGetItemViewType(int position);

        // * Create new views *
        // Invoked by the layout manager (required by the RecyclerView.Adapter)
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_LOADER) {
                View itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(mModuleLoaders.getLoaderLayout(), null);
                return new LoadingRowViewHolder(itemLayoutView);
            }

            return simpleOnCreateViewHolder(parent, viewType);
        }
        // Custom behaviour defined by the child class
        public abstract RecyclerView.ViewHolder simpleOnCreateViewHolder(ViewGroup parent, int viewType);

        // * Replace the contents of a view *
        // Invoked by the layout manager (required by the RecyclerView.Adapter)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_LOADER) {return;} //Do something with the loading row if needed
            simpleOnBindViewHolder(viewHolder, position);
        }
        // Custom behaviour defined by the child class
        public abstract void simpleOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    //** End of ABSTRACTS AND OVERRIDES **

    //** Start of MODULES **

        //* Start of DATASET MANIPULATORS MODULE *
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
        //* End of DATASET MANIPULATORS MODULE *

        //* Start of LOADERS MODULE *
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
        //* End of LOADERS MODULE *

    //** End of MODULES **
}
