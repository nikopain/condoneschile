package usm.cc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Model.Condom;
import usm.cc.R;

public class CarritoActivity extends AppCompatActivity {

    ArrayList<Condom> listaProductos = new ArrayList<Condom>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
       // getCondoms();
        //setListCondoms();

    }
/*
    private void getCondoms() {

        Condom condom = new Condom();

        condom.setName("LifeStyle Ultra Delgado");

        condom.setStock("100+");

        condom.setDescription("más delgado que un preservativo de látex standard, otorgando una sensación más natural, más sensibilidad.");

        listaProductos.add(condom);

        condom = new Condom();

        condom.setName("LifeStyle Ultra Lubricado");

        condom.setStock("250+");

        condom.setDescription("El preservativo mas popular de Lifestyles, contiene extra lubricación para una mejor sensación.");

        listaProductos.add(condom);
    }

    public void setListCondoms(){

        ListView listViewCondoms = (ListView) findViewById(R.id.listViewCondoms);
        Condom[] condomsArray = listaProductos.toArray(new Condom[0]);

        listViewCondoms.setAdapter(new CondomListAdapter(this, condomsArray));

        listViewCondoms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }*/
}