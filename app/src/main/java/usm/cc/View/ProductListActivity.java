package usm.cc.View;

import android.accounts.NetworkErrorException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Adapters.ListViewAdapter;
import usm.cc.Model.Condom;
import usm.cc.Model.CondomDTO;
import usm.cc.Model.User;
import usm.cc.R;
import usm.cc.network.AARManager;
import usm.cc.network.apiConnection;
import usm.cc.network.BackendAPI;

public class ProductListActivity extends AppCompatActivity implements Callback<CondomDTO> {

    private String TAG = "ProductListActivity";
    ArrayList<Condom> listaProductos = new ArrayList<Condom>();
    private User usuario;
    private CondomListAdapter condomListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        usuario = new User();
        usuario = getIntent().getExtras().getParcelable("User");
        Log.d("usuario", usuario.name +" "+usuario.city );

        Log.d(TAG,"Entrando a retrofit");
        getCondoms();

        CondomDTO condomsArray = new CondomDTO();
        condomsArray.setProductos(listaProductos);

        ArrayList<ListViewItem> array = new ArrayList<ListViewItem>();

        Log.d("Condoms API", String.valueOf(condomsArray.getProductos().size()));

         for (int i = 0; i < condomsArray.getProductos().size(); i++) {
            ArrayList<ViewPagerItem> viewPagerItems = new ArrayList<ViewPagerItem>();

            viewPagerItems.add(new ViewPagerItem(condomsArray.getProductos().get(i)));

            viewPagerItems.add(new ViewPagerItem(condomsArray.getProductos().get(i)));

            array.add(new ListViewItem(Integer.toString(i), viewPagerItems));
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListViewAdapter(this, array));
    }

    private void getCondoms() {
        //Se hacen los llamados al webservice
/*
        AARManager am = new AARManager("http://45.55.159.85/api/get/productos");

        String cadena = "";
        try {
                cadena = am.getJsonData();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
        Log.d(TAG, cadena);*/

        Retrofit retrofit = apiConnection.getClient();
        BackendAPI backEndAPI = retrofit.create(BackendAPI.class);
        Call<CondomDTO> call = backEndAPI.getProductos();
        Log.d(TAG, "call realizado");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CondomDTO> call, Response<CondomDTO> response) {

        Log.d(TAG, "response ---"+ response);
        if(response.isSuccessful()){
            listaProductos.addAll(response.body().getProductos());
            condomListAdapter.notifyDataSetChanged();
            Log.d(TAG, "response exitoso");
        }

    }

    @Override
    public void onFailure(Call<CondomDTO> call, Throwable t) {

    }

/*
        Condom condom = new Condom();

        condom.setName("LifeStyle Ultra Delgado");

        condom.setStock("100+");

        condom.setDescription("más delgado que un preservativo de látex standard, otorgando una sensación más natural, más sensibilidad.");

        listaProductos.add(condom);

        condom = new Condom();

        condom.setName("LifeStyle Ultra Lubricado");

        condom.setStock("250+");

        condom.setDescription("El preservativo mas popular de Lifestyles, contiene extra lubricación para una mejor sensación.");

        listaProductos.add(condom);*/

}
