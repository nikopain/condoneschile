package usm.cc.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
import usm.cc.misc.SnappingRecyclerView;
import usm.cc.network.ApiClient;
import usm.cc.network.ApiInterface;

public class ProductListActivity extends AppCompatActivity {

    private String TAG = ProductListActivity.class.getSimpleName();
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        configureSlider();
        configureButtons();

        usuario = new User();
        usuario = getIntent().getExtras().getParcelable("User");
        Log.d("usuario", usuario.name + " " + usuario.city);


    }

    // Parar ordenar el listado de productos por marca.
    public class ComparatorProduct implements Comparator {
        public int compare(Object arg1, Object arg2) {
            Product product1 = (Product) arg1;
            Product product2 = (Product) arg2;

            int flag = product1.getMarca().compareTo(product2.getMarca());
            if (flag == 0) {
                return product1.getNombre().compareTo(product2.getNombre());
            } else {
                return flag;
            }
        }
    }

    private void configureButtons() {
        ImageButton boton_actualizar = (ImageButton) findViewById(R.id.button_refresh);
        ImageButton boton_historial = (ImageButton) findViewById(R.id.button_history);

        boton_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Presionaste el botón refrescar.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        boton_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Presionaste el botón historial.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void configureSlider() {
        // establecer el tipo de layout del RecyclerView
        final SnappingRecyclerView productSlider = (SnappingRecyclerView) findViewById(R.id.product_slider);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        productSlider.setLayoutManager(layoutManager);

        // activar opción para centrar las tarjetas al terminar el desplazamiento
        productSlider.setSnapEnabled(true);

        // para mejorar el rendimiento
        productSlider.setHasFixedSize(true);

        // realizar una solicitud HTTP asíncrona
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductsResponse> call = apiService.getProducts();

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                // obtener los productos
                final List<Product> products = response.body().getData();

                // quitar aquellos productos que no tienen unidades disponibles
                for (Iterator<Product> iter = products.listIterator(); iter.hasNext(); ) {
                    Product product = iter.next();
                    if (product.getDisponible() == 0) {
                        iter.remove();
                    }
                }

                // ordenar productos por marca
                if (products.size() > 0) {
                    Collections.sort(products, new ComparatorProduct());
                }

                // añadir productos al RecyclerView
                productSlider.setAdapter(new ProductsAdapter(products, R.layout.home_slider_item, getApplicationContext()));

                // mostrar posición actual del slider
                productSlider.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        Integer currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition() + 1;
                        Integer listSize = products.size();
                        TextView currentPositionTextView = (TextView) findViewById(R.id.product_slider_current_position);
                        TextView lastPositionTextView = (TextView) findViewById(R.id.product_slider_last_position);

                        if (currentPosition > 0) {
                            currentPositionTextView.setText(currentPosition.toString());
                            lastPositionTextView.setText(listSize.toString());
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // registrar error cuando la solicitud falla
                Log.e(TAG, t.toString());
            }
        });
    }
}
