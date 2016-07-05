package usm.cc.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import usm.cc.Model.ProductBasket;
import usm.cc.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ShoppingCartAdapter extends RecyclerSwipeAdapter<ShoppingCartAdapter.ProductViewHolder> {

    private int rowLayout; // layout para cada producto
    private Context context;

    private List<ProductBasket> productsInBasket; // listado de productos
    Button buttonSendOrder; // botón para enviarel pedido
    TextView totalView; // total del carro de compras

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView productBrandCircle;
        TextView productName;
        TextView productUnits;
        ImageButton buttonDelete;
        ImageButton buttonEdit;

        public ProductViewHolder(View v) {
            super(v);
            swipeLayout = (SwipeLayout) v.findViewById(R.id.product_layout);
            productBrandCircle = (TextView) v.findViewById(R.id.product_brand_circle);
            productName = (TextView) v.findViewById(R.id.product_name);
            productUnits = (TextView) v.findViewById(R.id.product_units);
            buttonDelete = (ImageButton) v.findViewById(R.id.button_delete);
            buttonEdit = (ImageButton) v.findViewById(R.id.button_edit);
        }
    }

    public ShoppingCartAdapter(final List<ProductBasket> productsInBasket, int rowLayout, final Context context) {
        this.rowLayout = rowLayout;
        this.context = context;

        this.productsInBasket = productsInBasket;
        this.buttonSendOrder = (Button) ((Activity)context).getWindow().getDecorView().findViewById(R.id.button_send_order);

        buttonSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // vaciar carro de compras
                productsInBasket.clear();
                notifyDataSetChanged();

                // mostrar AlertDialog para confirmar el envío
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.shopping_cart_dialog_title_order_sent);
                builder.setMessage(R.string.shopping_cart_dialog_message_order_sent);
                builder.setPositiveButton(R.string.shopping_cart_dialog_button_accept, null);
                builder.show();
            }
        });
    }

    @Override
    public ShoppingCartAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        final ProductBasket item = productsInBasket.get(position);
        holder.productName.setText(item.getName());
        holder.productUnits.setText(Integer.toString(item.getUnits()));

        // asignar un color y letra al círculo según la marca del producto
        switch (item.getBrand().toLowerCase()) {
            case "crown":
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_crown);
                holder.productBrandCircle.setText(R.string.brand_circle_crown);
                break;

            case "durex":
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_durex);
                holder.productBrandCircle.setText(R.string.brand_circle_durex);
                break;

            case "lifestyles":
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_lifestyles);
                holder.productBrandCircle.setText(R.string.brand_circle_lifestyles);
                break;

            case "one":
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_one);
                holder.productBrandCircle.setText(R.string.brand_circle_one);
                break;

            case "trojan":
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_trojan);
                holder.productBrandCircle.setText(R.string.brand_circle_trojan);
                break;

            default:
                holder.productBrandCircle.setBackgroundResource(R.drawable.circle_unknown);
                holder.productBrandCircle.setText(R.string.brand_circle_unknown);
                break;
        }

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsInBasket.remove(position);
                notifyDataSetChanged();
                notifyItemRangeChanged(position, productsInBasket.size());
                updateTotal();
            }
        });

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                final View dialog_layout = inflater.inflate(R.layout.activity_shopping_dialog, null);

                // números que se mostrarán en el NumberPicker
                String[] numbers = new String[item.getStock() / 5];
                for (int i = 0; i < numbers.length; i++)
                    numbers[i] = Integer.toString(i * 5 + 5);

                // configurar NumberPicker
                final NumberPicker picker = (NumberPicker) dialog_layout.findViewById(R.id.number_picker);
                picker.setDisplayedValues(numbers);
                picker.setMaxValue(numbers.length - 1);
                picker.setMinValue(0);
                picker.setValue((item.getUnits() - 5) / 5);

                // configurar AlertDialog
                builder.setView(dialog_layout);
                builder.setTitle(R.string.shopping_cart_dialog_title_add_to_cart);
                builder.setNegativeButton(R.string.shopping_cart_dialog_button_cancel, null);

                // acción al presionar el bottón "Modificar"
                builder.setPositiveButton(R.string.shopping_cart_dialog_button_edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int pickedValue = picker.getValue() * 5 + 5;
                        item.setUnits(pickedValue);
                        notifyItemChanged(position);
                        updateTotal();
                    }
                });

                builder.show();
            }
        });

        // elegir efecto de deslizamiento
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // definir las vista que se mostrarán según el tipo de deslizamiento
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
                //closeAllExcept(layout);
                closeAllItems();
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
        return productsInBasket.size();
    }

    // Para activar las animaciones al utilizar notifyDataSetChanged().
    @Override
    public long getItemId(int position) {
        return productsInBasket.get(position).hashCode();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.product_layout;
    }


    public void updateTotal() {
        int totalUnits = 0;
        int newTotal;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("es","ES"));

        if (totalView != null && context != null) {
            for (ProductBasket item : productsInBasket) {
                totalUnits += item.getUnits();
            }

            // por el momento asumimos que cada unidad cuesta $300
            newTotal = totalUnits * 300;
            this.totalView.setText(context.getString(R.string.shopping_cart_total, numberFormat.format(newTotal)));
        }
    }

    public void setTotalView(TextView totalView) {
        this.totalView = totalView;
        this.totalView.setText("0");
    }
}
