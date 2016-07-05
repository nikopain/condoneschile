package usm.cc.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import usm.cc.Adapters.ProductsAdapter;
import usm.cc.Model.Product;
import usm.cc.Model.ProductsResponse;
import usm.cc.Model.User;
import usm.cc.R;
import usm.cc.misc.RecyclerViewShopppingCart;
import usm.cc.misc.RecyclerViewProductList;
import usm.cc.network.ApiClient;
import usm.cc.network.ApiInterface;

public class ShoppingActivity extends AppCompatActivity {

    private String TAG = ShoppingActivity.class.getSimpleName();
    private User usuario;

    private BottomBar bottomBar; // barra de navegación inferior
    private LinearLayout topPanel; // contenedor del menu superior y del slider de productos
    private ProgressDialog progress; // mensaje de carga

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // SharedPreferences sp = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        // Toast.makeText(getApplicationContext(), getResources().getString(R.string.welcome) + " " + sp.getString(LoginActivity.NAME, ""), Toast.LENGTH_SHORT).show();

        // Configurar barra de navegación inferior.
        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItems(R.menu.bottom_bar_menu);
        bottomBar.getBar().setBackgroundResource(R.drawable.background_top_bar);
        bottomBar.setActiveTabColor("#0060ff");
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                // The user selected item number one.
                if(menuItemId == R.id.bottom_bar_item_settings){
                    Intent i = new Intent(ShoppingActivity.this, SettingsActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
            }

            @Override
            public void onMenuTabReSelected(final int position) {
                if (position == 0) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        topPanel = (LinearLayout) findViewById(R.id.shopping_top_panel);

        initLoader();
        initTopBar();
        initSliders();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        bottomBar.onSaveInstanceState(outState);
    }

    // Parar ordenar el listado de productos por marca.
    public class ComparatorProduct implements Comparator {
        public int compare(Object arg1, Object arg2) {
            Product product1 = (Product) arg1;
            Product product2 = (Product) arg2;

            int flag = product1.getBrand().compareTo(product2.getBrand());
            if (flag == 0) {
                return product1.getName().compareTo(product2.getName());
            } else {
                return flag;
            }
        }
    }

    private void initLoader() {
        // Mostrar mensaje de carga.
        progress = new ProgressDialog(this);
        progress.setTitle(R.string.shopping_cart_loader_title);
        progress.setMessage(getString(R.string.shopping_cart_loader_message));
        progress.setCancelable(false);

        // Modificar posición del mensaje de carga.
        progress.getWindow().setGravity(Gravity.TOP);
        WindowManager.LayoutParams params = progress.getWindow().getAttributes();
        params.y = 120;
        progress.getWindow().setAttributes(params);

        progress.show();
    }

    private void initTopBar() {
        ImageButton buttonRefresh = (ImageButton) findViewById(R.id.button_refresh);
        ImageButton buttonHistory = (ImageButton) findViewById(R.id.button_history);

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mostrar historial de pedidos.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void initSliders() {
        final RecyclerViewProductList productSlider = (RecyclerViewProductList) findViewById(R.id.product_slider);
        final RecyclerViewShopppingCart shoppingCart = (RecyclerViewShopppingCart) findViewById(R.id.shopping_cart_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        productSlider.setLayoutManager(layoutManager);
        productSlider.setSnapEnabled(true); // centrar tarjetas al terminar desplazamiento
        productSlider.setHasFixedSize(true); // para mejorar el rendimiento

        // realizar una solicitud HTTP asíncrona
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductsResponse> call = apiService.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                // obtener los productos
                final List<Product> products = response.body().getData();

                // quitar aquellos productos que no tienen unidades suficientes (mínimo 5 unidades)
                for (Iterator<Product> iter = products.listIterator(); iter.hasNext(); ) {
                    Product product = iter.next();
                    if (product.getStock() < 5) {
                        iter.remove();
                    } else {
                        // corregir nombres
                        product.setStock((product.getStock() / 5) * 5);
                        product.setName(product.getName().substring(0, 1).toUpperCase() + product.getName().substring(1));
                    }
                }

                // ordenar productos por marca
                if (products.size() > 0) {
                    Collections.sort(products, new ComparatorProduct());
                }

                // añadir productos al slider e inicializar carro de compras (dentro de ProductsAdapter)
                productSlider.setAdapter(new ProductsAdapter(products, shoppingCart, R.layout.activity_shopping_slider_item, ShoppingActivity.this));

                // mostrar posición actual del slider
                productSlider.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        Integer currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition() + 1;
                        Integer listSize = products.size();
                        TextView currentPositionView = (TextView) findViewById(R.id.product_slider_current_position);
                        TextView lastPositionView = (TextView) findViewById(R.id.product_slider_last_position);

                        if (currentPosition > 0) {
                            currentPositionView.setText(currentPosition.toString());
                            lastPositionView.setText(listSize.toString());
                        }
                    }
                });

                // ocultar mensaje de carga y mostrar panel superior
                progress.dismiss();
                topPanel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e(TAG, t.toString()); // registrar errores
            }
        });
    }
}