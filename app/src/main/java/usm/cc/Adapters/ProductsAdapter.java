package usm.cc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import usm.cc.Model.Product;
import usm.cc.R;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> products; // listado de productos
    private int rowLayout; // layout para cada producto
    private Context context;

    // Proporciona una referencia a las vistas para cada elemento del conjunto de datos.
    // Los elementos más complejos pueden necesitar más de una vista, el view holder nos
    // permite proporcionarle acceso a todas las vistas a un elemento del conjunto de datos.
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productBrand;
        TextView productBrandCircle;
        TextView productName;
        TextView productDescription;
        View productBrandBorder;

        public ProductViewHolder(View v) {
            super(v);
            productBrand = (TextView) v.findViewById(R.id.product_brand);
            productBrandBorder = (View) v.findViewById(R.id.product_brand_border);
            productBrandCircle = (TextView) v.findViewById(R.id.product_brand_circle);
            productName = (TextView) v.findViewById(R.id.product_name);
            productDescription = (TextView) v.findViewById(R.id.product_description);
        }
    }

    // Debemos proporcionar un constructor adecuado según el conjunto de datos.
    public ProductsAdapter(List<Product> products, int rowLayout, Context context) {
        this.products = products;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    // Creamos nuevas vistas (invocado por el layout manager).
    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProductViewHolder(v);
    }

    // Reemplazamos el contenido de una vista (invocado por el layout manager).
    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        Product item = products.get(position);

        // reemplazamos los datos
        holder.productBrand.setText(item.getMarca().toUpperCase());
        holder.productName.setText(item.getNombre().substring(0, 1).toUpperCase() + item.getNombre().substring(1));
        holder.productDescription.setText(item.getDescripcion());

        // le asignamos un color a la tarjeta según la marca del producto
        switch (item.getMarca().toLowerCase()) {
            case "crown":
                holder.productBrandBorder.setBackgroundResource(R.color.brand_crown);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_crown);
                holder.productBrandCircle.setText(R.string.brand_circle_crown);
                break;

            case "durex":
                holder.productBrandBorder.setBackgroundResource(R.color.brand_durex);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_durex);
                holder.productBrandCircle.setText(R.string.brand_circle_durex);
                break;

            case "lifestyles":
                holder.productBrandBorder.setBackgroundResource(R.color.brand_lifestyles);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_lifestyles);
                holder.productBrandCircle.setText(R.string.brand_circle_lifestyles);
                break;

            case "one":
                holder.productBrandBorder.setBackgroundResource(R.color.brand_one);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_one);
                holder.productBrandCircle.setText(R.string.brand_circle_one);
                break;

            case "trojan":
                holder.productBrandBorder.setBackgroundResource(R.color.brand_trojan);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_trojan);
                holder.productBrandCircle.setText(R.string.brand_circle_trojan);
                break;

            default:
                holder.productBrandBorder.setBackgroundResource(R.color.brand_unknown);
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_unknown);
                holder.productBrandCircle.setText(R.string.brand_circle_unknown);
                break;
        }
    }

    // Devolvemos el tamaño del conjunto de datos.
    @Override
    public int getItemCount() {
        return products.size();
    }
}
