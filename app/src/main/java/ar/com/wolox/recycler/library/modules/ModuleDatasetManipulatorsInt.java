package ar.com.wolox.recycler.library.modules;

import java.util.Collection;

public interface ModuleDatasetManipulatorsInt<E> {

    public void addItem(E item);
    public void addItemToPos(int position, E item);
    public void addAllItems(Collection<E> itemsArray);
    public void removeItemByPos(int position);
    public int getItemPosition(E itemToLocate);
    public void removeItem(E itemToRemove);
    public void moveItem(int fromPos, int toPos);
}
