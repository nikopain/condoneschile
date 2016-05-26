package usm.cc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import usm.cc.Adapters.CondomListAdapter;
import usm.cc.Adapters.UserSettingsAdapter;
import usm.cc.Model.Condom;
import usm.cc.Model.Info;
import usm.cc.R;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<Info> infoSettings = new ArrayList<Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getInfo();
        setListCondoms();

    }
    private void getInfo() {

         infoSettings.add(new Info("Nombre", "Diego Maldonado"));
         infoSettings.add(new Info("Email", "diego.maldonado@alumnos.usm.cl"));
         infoSettings.add(new Info("Teléfono", "+56 9 77965625"));
         infoSettings.add(new Info("Dirección", "Pasaje el Acuario, Santiago"));
    }

    public void setListCondoms(){

        ListView listViewCondoms = (ListView) findViewById(R.id.listViewUser);
        Info[] infoArray =  infoSettings.toArray(new Info[0]);

        listViewCondoms.setAdapter(new UserSettingsAdapter(this, infoArray));

        listViewCondoms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }
}
