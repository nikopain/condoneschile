package usm.cc.View;

import java.util.ArrayList;

import usm.cc.Model.Condom;

/**
 * Created by niko on 28/04/2016.
 */
public class ListViewItem {
    private String idRow;
    private Condom condon;

    public ListViewItem(String idRow, Condom condon) {
        this.idRow = idRow;
        this.condon = condon;
    }

    public String getIdRow() {
        return idRow;
    }

    public void setIdRow(String idRow) {
        this.idRow = idRow;
    }

    public Condom getCondon() {
        return condon;
    }

    public void setViewPagerItems(Condom condon) {
        this.condon = condon;
    }
}
