package usm.cc.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import usm.cc.Model.Product;
import usm.cc.Model.ProductBasket;
import usm.cc.R;
import usm.cc.misc.RecyclerViewShopppingCart;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private Context context;
    private int rowLayout; // layout para cada producto

    private List<Product> products; // listado de productos del slider
    private List<ProductBasket> productsInBasket; // listado de productos
    private RecyclerViewShopppingCart shoppingCart; // para añadir productos al carro de compras
    private ShoppingCartAdapter shoppingAdapter; // para actualizar carro de compras

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productBrand;
        View productBrandBorder;
        TextView productBrandCircle;
        TextView productDescription;
        TextView productName;
        TextView productStock;

        Button buttonAddToCart;

        public ProductViewHolder(View v) {
            super(v);
            productBrand = (TextView) v.findViewById(R.id.product_brand);
            productBrandBorder = (View) v.findViewById(R.id.product_brand_border);
            productBrandCircle = (TextView) v.findViewById(R.id.product_brand_circle);
            productDescription = (TextView) v.findViewById(R.id.product_description);
            productName = (TextView) v.findViewById(R.id.product_name);
            productStock = (TextView) v.findViewById(R.id.product_stock);
            buttonAddToCart = (Button) v.findViewById(R.id.button_add_to_cart);
        }
    }

    public ProductsAdapter(List<Product> products, RecyclerViewShopppingCart shoppingCart, int rowLayout, Context context) {
        this.context = context;
        this.rowLayout = rowLayout;

        this.products = products;
        this.productsInBasket = new ArrayList<>();

        this.shoppingCart = shoppingCart;
        this.shoppingAdapter = new ShoppingCartAdapter(productsInBasket, R.layout.home_shopping_item, context);

        // vistas del carro de compras que se deben ocultar y mostrar según su contenido
        TextView emptyView = (TextView) ((Activity)context).getWindow().getDecorView().findViewById(R.id.shopping_cart_empty);
        TextView totalView = (TextView) ((Activity)context).getWindow().getDecorView().findViewById(R.id.shopping_cart_total);
        LinearLayout totalSectionView = (LinearLayout) ((Activity)context).getWindow().getDecorView().findViewById(R.id.shopping_cart_total_section);

        // configurar carro de compras
        shoppingAdapter.setTotalView(totalView);
        shoppingCart.setEmptyView(emptyView);
        shoppingCart.setTotalSectionView(totalSectionView);
        shoppingCart.setLayoutManager(new LinearLayoutManager(context));
        shoppingCart.setAdapter(shoppingAdapter);
        //shoppingCart.setNestedScrollingEnabled(false);
    }

    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final Product item = products.get(position);

        // replazar los datos
        holder.productBrand.setText(item.getBrand().toUpperCase());
        holder.productDescription.setText(item.getDescription());
        holder.productName.setText(item.getName());
        holder.productStock.setText(Integer.toString(item.getStock()));

        // asignar un color a la tarjeta según la marca del producto
        switch (item.getBrand().toLowerCase()) {
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

        // mostrar popup para añadir unidades
        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                final View dialogLayout = inflater.inflate(R.layout.home_shopping_dialog, null);

                // números que se mostrarán en el NumberPicker
                String[] numbers = new String[item.getStock() / 5];
                for (int i = 0; i < numbers.length; i++)
                    numbers[i] = Integer.toString(i * 5 + 5);

                // configurar NumberPicker
                final NumberPicker picker = (NumberPicker) dialogLayout.findViewById(R.id.number_picker);
                picker.setDisplayedValues(numbers);
                picker.setMaxValue(numbers.length - 1);
                picker.setMinValue(0);

                // configurar AlertDialog
                builder.setView(dialogLayout);
                builder.setTitle(R.string.shopping_cart_dialog_title_add_to_cart);
                builder.setNegativeButton(R.string.shopping_cart_dialog_button_cancel, null);

                // acción al presionar el bottón "Añadir"
                builder.setPositiveButton(R.string.shopping_cart_dialog_button_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean found = false;
                        int pickedValue = picker.getValue() * 5 + 5;
                        ProductBasket newProduct;

                        // modificar producto si es que ya está en el carro de compras
                        for (int i = 0; i < productsInBasket.size(); i++) {
                            ProductBasket product = productsInBasket.get(i);
                            if (product.getId() == item.getId()) {
                                found = true;
                                product.setUnits(pickedValue);
                                shoppingAdapter.notifyItemChanged(i);
                                break;
                            }
                        }

                        // añadir producto al carro de compras
                        if (!found) {
                            newProduct = new ProductBasket(item.getId(), item.getBrand(), item.getName(), item.getStock(), pickedValue);
                            //productsInBasket.add(0, newProduct);
                            //shoppingAdapter.notifyItemInserted(0);
                            productsInBasket.add(newProduct);
                            shoppingAdapter.notifyItemInserted(productsInBasket.size() - 1);
                            shoppingCart.scrollToPosition(0);
                        }

                        // actualizar total del pedido
                        shoppingAdapter.updateTotal();
                    }
                });

                builder.show();
            }
        });
    }

    // Devolvemos el tamaño del conjunto de datos.
    @Override
    public int getItemCount() {
        return products.size();
    }
}
