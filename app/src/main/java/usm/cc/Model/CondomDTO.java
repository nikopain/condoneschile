package usm.cc.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niko on 17/05/2016.
 */
public class CondomDTO {

    private List<Condom> data;
    private List<String> dbInfo;

    public List<Condom> getProductos() { return data; }
    public void setProductos(List<Condom> data) { this.data = data; }

    public List<String> getDbInfo() { return dbInfo; }
    public void setDbInfo(List<String> dbInfo) { this.dbInfo = dbInfo; }
}
