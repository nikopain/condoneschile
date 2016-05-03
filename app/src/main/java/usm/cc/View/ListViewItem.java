package usm.cc.View;

import java.util.ArrayList;

/**
 * Created by niko on 28/04/2016.
 */
public class ListViewItem {
    private String idRow;
    private ArrayList<ViewPagerItem> viewPagerItems;

    public ListViewItem(String idRow, ArrayList<ViewPagerItem> viewPagerItems) {
        this.idRow = idRow;
        this.viewPagerItems = viewPagerItems;
    }

    public String getIdRow() {
        return idRow;
    }

    public void setIdRow(String idRow) {
        this.idRow = idRow;
    }

    public ArrayList<ViewPagerItem> getViewPagerItems() {
        return viewPagerItems;
    }

    public void setViewPagerItems(ArrayList<ViewPagerItem> viewPagerItems) {
        this.viewPagerItems = viewPagerItems;
    }
}
