package usm.cc.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import usm.cc.Adapters.ProductsAdapter;
import usm.cc.Adapters.ProductsCartAdapter;
import usm.cc.Model.Condom;
import usm.cc.Model.Product;
import usm.cc.Model.ProductsResponse;
import usm.cc.R;
import usm.cc.misc.RecyclerItemClickListener;
import usm.cc.misc.SnappingRecyclerView;
import usm.cc.network.ApiClient;
import usm.cc.network.ApiInterface;

public class ProductListActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ProductsCartAdapter productsCartAdapter;
    private String TAG = ProductListActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        SharedPreferences sp = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(),getResources().getString(R.string.welcome)+" "+sp.getString(LoginActivity.NAME,""),Toast.LENGTH_SHORT).show();
        // establecemos el tipo de layout del RecyclerView


        final ListView productsCartListView = (ListView) findViewById(R.id.products_cart);
        productsCartAdapter = new ProductsCartAdapter(this,productArrayList);
        productsCartListView.setAdapter(productsCartAdapter);
        final SnappingRecyclerView recyclerView = (SnappingRecyclerView) findViewById(R.id.product_slider);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // activamos la opción para centrar las tarjetas al terminar el desplazamiento
        recyclerView.setSnapEnabled(true);

        // para mejorar el rendimiento
        recyclerView.setHasFixedSize(true);

        // realizamos una solicitud HTTP asíncrona
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProductsResponse> call = apiService.getProducts();
        call.enqueue(new Callback<ProductsResponse>() {

            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                // obtenemos el listado de productos
                final List<Product> products = response.body().getData();
                // quitamos del listado aquellos que no tienen unidades disponibles
                for (Iterator<Product> iter = products.listIterator(); iter.hasNext(); ) {
                    Product product = iter.next();
                    if (product.getDisponible() <5) {
                        iter.remove();
                    }
                }
                final TextView currentPositionTextView = (TextView) findViewById(R.id.product_slider_current_position);
                TextView lastPositionTextView = (TextView) findViewById(R.id.product_slider_last_position);
                lastPositionTextView.setText(String.valueOf(products.size()));
                // ordenamos los productos por marca
                if (products.size() > 0) {
                    Collections.sort(products, new ComparatorProduct());
                }
                // mostramos los productos en el RecyclerView
                recyclerView.setAdapter(new ProductsAdapter(products, R.layout.product_slider_item, getApplicationContext()));
                // indicamos la posición de la tarjeta centrada, 1+ que la lista products
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        TextView p5 = (TextView) findViewById(R.id.product_button_5);
                        TextView p10 = (TextView) findViewById(R.id.product_button_10);
                        TextView p50 = (TextView) findViewById(R.id.product_button_50);
                        final Integer currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition() + 1;
                        if (currentPosition > 0) {
                            final Product currentProduct = products.get(currentPosition-1);
                            currentPositionTextView.setText(currentPosition.toString());
                            Log.d(products.get(currentPosition - 1).getNombre(), String.valueOf(products.get(currentPosition - 1).getDisponible()));
                            p5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(currentProduct.getActual()==0) {
                                        currentProduct.setActual(5);
                                        productArrayList.add(currentProduct);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                    else{
                                        currentProduct.setActual(currentProduct.getActual()+5);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                            p10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(currentProduct.getActual()==0) {
                                        currentProduct.setActual(10);
                                        productArrayList.add(currentProduct);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                    else{
                                        currentProduct.setActual(currentProduct.getActual()+10);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                            p50.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(currentProduct.getActual()==0) {
                                        currentProduct.setActual(50);
                                        productArrayList.add(currentProduct);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                    else{
                                        currentProduct.setActual(currentProduct.getActual()+50);
                                        productsCartAdapter.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // la solicitud falló, así que registramos el error
                Log.e(TAG, t.toString());
            }
        });
        ImageView settings = (ImageView) findViewById(R.id.icon_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ProductListActivity.this, SettingsActivity.class);
                startActivity(in);
            }
        });
        ImageView cart = (ImageView) findViewById(R.id.icon_shopping);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ProductListActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });
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
}
