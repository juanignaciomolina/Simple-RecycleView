package ar.com.wolox.recycler.library.modules;

public interface ModuleLoadersInt<E> {

    public void addLoadingRow();
    public void addLoadingRow(int position);
    public void removeLoadingRow();
    public void removeLoadingRow(int itemPosition);
    public void removeLoadingRowByLoaderPos(int loaderPosition);
    public boolean isLoader(int position);
}
