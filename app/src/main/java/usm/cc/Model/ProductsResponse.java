package usm.cc.Model;

import java.util.List;

public class ProductsResponse {

    private List<Product> data;
    private List<String> dbInfo;

    public List<Product> getData() { return data; }
    public void setData(List<Product> data) { this.data = data; }

    public List<String> getDbInfo() { return dbInfo; }
    public void setDbInfo(List<String> dbInfo) { this.dbInfo = dbInfo; }
}
