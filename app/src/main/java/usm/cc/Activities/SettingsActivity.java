package usm.cc.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

import usm.cc.Adapters.UserSettingsAdapter;
import usm.cc.Model.Info;
import usm.cc.R;
import usm.cc.misc.SettingsEdit;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<Info> infoSettings = new ArrayList<Info>();
    private BottomBar bottomBar; // barra de navegación inferior

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getInfo();
        setList();

        // Configurar barra de navegación inferior.
        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.noNavBarGoodness();
        bottomBar.setItems(R.menu.bottom_bar_menu);
        bottomBar.getBar().setBackgroundResource(R.drawable.background_top_bar);
        bottomBar.setActiveTabColor("#0060ff");
        bottomBar.setDefaultTabPosition(2);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                // The user selected item number one.
                if(menuItemId == R.id.bottom_bar_item_shopping){
                    Intent i = new Intent(SettingsActivity.this, ShoppingActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                }
            }

            @Override
            public void onMenuTabReSelected(final int position) {
                if (position == 0) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });
    }
    private void getInfo() {
        SharedPreferences sp = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
         infoSettings.add(new Info(getResources().getString(R.string.register_field_first_name), sp.getString(LoginActivity.NAME,"")));
         infoSettings.add(new Info("Email", sp.getString(LoginActivity.EMAIL,"")));
         infoSettings.add(new Info(getResources().getString(R.string.register_field_phone), sp.getString(LoginActivity.PHONE,"")));
         infoSettings.add(new Info(getResources().getString(R.string.register_field_address), sp.getString(LoginActivity.ADDRESS,"")));
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
