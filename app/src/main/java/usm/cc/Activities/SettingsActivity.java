package usm.cc.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import usm.cc.Adapters.UserSettingsAdapter;
import usm.cc.Model.Info;
import usm.cc.R;
import usm.cc.misc.SettingsEdit;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<Info> infoSettings = new ArrayList<Info>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getInfo();
        setList();

    }
    private void getInfo() {
        SharedPreferences sp = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(),"Mostrando tu informacion "+sp.getString(LoginActivity.NAME,""),Toast.LENGTH_SHORT).show();
         infoSettings.add(new Info(getResources().getString(R.string.name), sp.getString(LoginActivity.NAME,"")));
         infoSettings.add(new Info("Email", sp.getString(LoginActivity.EMAIL,"")));
         infoSettings.add(new Info(getResources().getString(R.string.phone), sp.getString(LoginActivity.PHONE,"")));
         infoSettings.add(new Info(getResources().getString(R.string.address), sp.getString(LoginActivity.ADDRESS,"")));
    }

    public void setList() {

        ListView listViewUser = (ListView) findViewById(R.id.listViewUser);
        Info[] infoArray = infoSettings.toArray(new Info[0]);

        listViewUser.setAdapter(new UserSettingsAdapter(this, infoArray));
        listViewUser.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new SettingsEdit(v.getContext()).show();

                return false;
            }
        });
    }

}
