package usm.cc.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Model.Condom;
import usm.cc.R;

public class CarritoActivity extends AppCompatActivity {

    ArrayList<Condom> listaProductos = new ArrayList<Condom>();
    private int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        getCondoms();
        setListCondoms();
        getTotal();
        TextView costoTotal = (TextView) findViewById(R.id.costoTotal);
        costoTotal.setText("Costo Total: $"+ String.valueOf(total));

        ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarritoActivity.this, ProductListActivity.class);
                startActivity(i);
            }
        });
        ImageView settings = (ImageView) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarritoActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        });
    }
    private void getTotal(){
        int preciounitario= 300;
        for( int i = 0 ; i< listaProductos.size(); i++){
            total += listaProductos.get(i).getVendido()*preciounitario;
        }
    }
    private void getCondoms() {

        Condom condom = new Condom();

        condom.setNombre("LifeStyle Ultra Delgado");

        condom.setVendido(100);

        listaProductos.add(condom);

        condom = new Condom();

        condom.setNombre("LifeStyle Ultra Lubricado");

        condom.setVendido(250);

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
    }
}
