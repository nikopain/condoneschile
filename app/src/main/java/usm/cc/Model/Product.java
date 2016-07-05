package usm.cc.Model;

public class Product {

    private String _0;
    private String _1;
    private String _2;
    private String _3;
    private String _4;
    private String _5;
    private String _6;
    private String _7;
    private String _8;
    private String _9;
    private int id;
    private String marca;
    private String nombre;
    private int total;
    private int reservado;
    private int vendido;
    private int disponible;
    private String imagen;
    private String descripcion;
    private int mostrar;

    public Product(String marca, String nombre, int disponible, String descripcion) {
        this.marca = marca;
        this.nombre = nombre;
        this.disponible = disponible;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return marca; }
    public void setBrand(String marca) { this.marca = marca; }

    public String getName() { return nombre; }
    public void setName(String nombre) { this.nombre = nombre; }

    public int getStock() { return disponible; }
    public void setStock(int disponible) { this.disponible = disponible; }

    public String getImage() { return imagen; }
    public void setImage(String imagen) { this.imagen = imagen; }

    public String getDescription() { return descripcion; }
    public void setDescription(String descripcion) { this.descripcion = descripcion; }
}