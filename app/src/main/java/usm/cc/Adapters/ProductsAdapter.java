package usm.cc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        LinearLayout productLayout;
        TextView productTitle;
        TextView productBrand;
        TextView productDescription;
        TextView productStock;

        public ProductViewHolder(View v) {
            super(v);
            productLayout = (LinearLayout) v.findViewById(R.id.product_layout);
            productTitle = (TextView) v.findViewById(R.id.product_title);
            productBrand = (TextView) v.findViewById(R.id.product_brand);
            productDescription = (TextView) v.findViewById(R.id.product_description);
            productStock = (TextView) v.findViewById(R.id.product_stock);
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
        holder.productTitle.setText(products.get(position).getNombre());
        holder.productBrand.setText(products.get(position).getMarca());
        holder.productDescription.setText(products.get(position).getDescripcion());
        holder.productStock.setText(products.get(position).getDisponible().toString());
    }

    // Devolvemos el tamaño del conjunto de datos.
    @Override
    public int getItemCount() {
        return products.size();
    }
}