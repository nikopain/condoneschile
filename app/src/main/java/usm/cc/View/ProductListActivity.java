package usm.cc.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import usm.cc.Adapters.ListViewAdapter;
import usm.cc.Model.Condom;
import usm.cc.Model.CondomDTO;
import usm.cc.Model.User;
import usm.cc.R;
import usm.cc.network.apiConnection;
import usm.cc.network.BackendAPI;

public class ProductListActivity extends AppCompatActivity {

    private String TAG = "ProductListActivity";
    ArrayList<Condom> listaProductos = new ArrayList<Condom>();
    private ListViewAdapter lvA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ListView listView = (ListView) findViewById(R.id.listViewCondoms);
        lvA = new ListViewAdapter(this,listaProductos);
        listView.setAdapter(lvA);
        getCondoms();

        ImageView cart = (ImageView) findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductListActivity.this, CarritoActivity.class);
                startActivity(i);
            }
        });
        ImageView settings = (ImageView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductListActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    private void getCondoms() {

        Retrofit retrofit = apiConnection.getClient();
        BackendAPI backEndAPI = retrofit.create(BackendAPI.class);
        Call<CondomDTO> call = backEndAPI.getProductos();

        call.enqueue(new Callback<CondomDTO>() {
            @Override
            public void onResponse(Call<CondomDTO> call, Response<CondomDTO> response) {
                listaProductos.addAll(response.body().getProductos());
                lvA.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<CondomDTO> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}
