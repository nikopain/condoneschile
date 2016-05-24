package usm.cc.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
import usm.cc.misc.ProductListDecoration;
import usm.cc.network.ApiClient;
import usm.cc.network.ApiInterface;

public class ProductListActivity extends AppCompatActivity {

    private String TAG = ProductListActivity.class.getSimpleName();
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        usuario = new User();
        usuario = getIntent().getExtras().getParcelable("User");
        Log.d("usuario", usuario.name + " " + usuario.city);

        // establecemos el tipo de layout del RecyclerView y agregamos los divisores de la lista
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ProductListDecoration(this, LinearLayoutManager.VERTICAL));

        // para mejorar el rendimiento
        recyclerView.setHasFixedSize(true);

        // realizamos una solicitud HTTP asíncrona
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductsResponse> call = apiService.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {

            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                // obtenemos el listado de productos
                List<Product> products = response.body().getData();

                // quitamos del listado aquellos que no tienen unidades disponibles
                for (Iterator<Product> iter = products.listIterator(); iter.hasNext(); ) {
                    Product product = iter.next();
                    if (product.getDisponible() == 0) {
                        iter.remove();
                    }
                }

                // mostramos los productos en el RecyclerView
                recyclerView.setAdapter(new ProductsAdapter(products, R.layout.list_item_product, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // la solicitud falló, así que registramos el error
                Log.e(TAG, t.toString());
            }
        });
    }
}
