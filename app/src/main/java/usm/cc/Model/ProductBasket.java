package usm.cc.Model;

public class ProductBasket {

    private int id;
    private int stock;
    private int units;
    private String brand;
    private String name;

    public ProductBasket(int id, String brand, String name, int stock, int units) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.stock = stock;
        this.units = units;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getUnits() { return units; }
    public void setUnits(int units) { this.units = units; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
