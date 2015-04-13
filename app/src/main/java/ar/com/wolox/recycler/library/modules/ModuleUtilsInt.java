package ar.com.wolox.recycler.library.modules;

import java.util.ArrayList;

public interface ModuleUtilsInt<E> {

    public int insertOrderedRow(ArrayList<Integer> itemsList, int value);
    public int removeOrderedRow(ArrayList<Integer> itemsList, int positionToRemove);
}
