package usm.cc.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;

import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Model.Condom;
import usm.cc.R;

public class ListaProductoActivity extends AppCompatActivity implements SwipeActionAdapter.SwipeActionListener{
    ArrayList<Condom> listaProductos = new ArrayList<Condom>();
    protected SwipeActionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        getCondoms();

        ListView listViewCondoms = (ListView) findViewById(R.id.listViewCondoms);
        Condom[] condomsArray = listaProductos.toArray(new Condom[0]);

        mAdapter = new SwipeActionAdapter(new CondomListAdapter(this,condomsArray));
        mAdapter.setSwipeActionListener(this)
                .setDimBackgrounds(true)
                .setListView(listViewCondoms);
        listViewCondoms.setAdapter(mAdapter);

        mAdapter.addBackground(SwipeDirection.DIRECTION_FAR_LEFT,R.layout.row_condom_buy_options)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT, R.layout.row_condom_buy_options);


        ImageView home = (ImageView) findViewById(R.id.home);
        if (home != null) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //attemptLogin();
                    Intent i = new Intent(ListaProductoActivity.this, CarritoActivity.class);
                    startActivity(i);

                }
            });
        }
        ImageView settings = (ImageView) findViewById(R.id.settings);
        if (settings != null) {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //attemptLogin();
                    Intent i = new Intent(ListaProductoActivity.this, SettingsActivity.class);
                    startActivity(i);

                }
            });
        }
        /*ListView listViewCondoms = (ListView) findViewById(R.id.listViewCondoms);
        listViewCondoms.setAdapter(mAdapter);
        listViewCondoms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaProductoActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });*/
    }

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

    @Override
    public boolean hasActions(int position, SwipeDirection direction){
        if(direction.isLeft()) return true;
        if(direction.isRight()) return true;
        return false;
    }

    @Override
    public boolean shouldDismiss(int position, SwipeDirection direction){
        return direction != SwipeDirection.DIRECTION_NORMAL_LEFT;
    }

    @Override
    public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
        for(int i=0;i<positionList.length;i++) {
            SwipeDirection direction = directionList[i];
            int position = positionList[i];
            String dir = "";

            switch (direction) {
                case DIRECTION_FAR_LEFT:
                    dir = "Far left";
                    break;
                case DIRECTION_NORMAL_LEFT:
                    dir = "Left";
                    break;
            }
            Toast.makeText(
                    this,
                    dir + " swipe Action triggered on " + mAdapter.getItem(position),
                    Toast.LENGTH_SHORT
            ).show();
            mAdapter.notifyDataSetChanged();
        }
    }

}
