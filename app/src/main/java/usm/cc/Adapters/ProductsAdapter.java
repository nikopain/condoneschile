package usm.cc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

import usm.cc.Model.Product;
import usm.cc.R;

public class ProductsAdapter extends RecyclerSwipeAdapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> products; // listado de productos
    private int rowLayout; // layout para cada producto
    private Context context;

    // Proporciona una referencia a las vistas para cada elemento del conjunto de datos.
    // Los elementos más complejos pueden necesitar más de una vista, el view holder nos
    // permite proporcionarle acceso a todas las vistas a un elemento del conjunto de datos.
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView productTitle;
        TextView productDescription;
        TextView productStock;

        public ProductViewHolder(View v) {
            super(v);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.product_layout);
            productTitle = (TextView) v.findViewById(R.id.product_title);
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
        // colocamos los datos del producto
        Product item = products.get(position);
        holder.productTitle.setText(item.getMarca() + " " + item.getNombre());
        holder.productDescription.setText(item.getDescripcion());
        holder.productStock.setText(item.getDisponible().toString());

        // elegimos el efecto de deslizamiento
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // elegimos el modo de deslizamiento (se arrastra desde la derecha) y la vista que se mostrará
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.product_menu));

        // manejar diferentes eventos al deslizar
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                // when the SurfaceView totally cover the BottomView
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                // you are swiping
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                // when the BottomView totally show
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                // when user's hand released
            }
        });
    }

    // Devolvemos el tamaño del conjunto de datos.
    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.product_layout;
    }
}