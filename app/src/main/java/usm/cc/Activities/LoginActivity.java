package usm.cc.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import usm.cc.R;

public class LoginActivity extends AppCompatActivity {
    EditText nameText,lastnameText,phoneText,emailText,pcText,cityText,addressText;
    Button register,exit;
    //Establecemos las llaves para almacenar los datos del usuario y poder recuperarlos en cualquier actividad
    public static final String MyPREFERENCES =  "MyPrefs";
    public static final String NAME = "nameKey";
    public static final String LASTNAME = "lastnameKey";
    public static final String EMAIL = "emailKey";
    public static final String PHONE = "phoneKey";
    public static final String POSTALCODE = "postalcodeKey";
    public static final String CITY= "cityKey";;
    public static final String ADDRESS= "addresslKey";
    public static final String LOGGED_IN= "loggedinKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean(LOGGED_IN,false)){
            Intent in = new Intent(LoginActivity.this,ShoppingActivity.class);
            startActivity(in);
        }
        nameText = (EditText) findViewById(R.id.nameText);
        lastnameText = (EditText) findViewById(R.id.lastnameText);
        emailText = (EditText) findViewById(R.id.emailText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        addressText = (EditText) findViewById(R.id.addressText);
        cityText= (EditText) findViewById(R.id.cityText);
        pcText = (EditText) findViewById(R.id.postalcodeText);

        register = (Button) findViewById(R.id.register);
        exit = (Button) findViewById(R.id.exit);

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(nameText.length()>0 && lastnameText.length()>0 && emailText.length()>0
                        && cityText.length()>0 && addressText.length()>0 && phoneText.length()>0) {
                    String na = nameText.getText().toString();
                    String ln = lastnameText.getText().toString();
                    String em = emailText.getText().toString();
                    String city = cityText.getText().toString();
                    String ph = phoneText.getText().toString();
                    String adrs = addressText.getText().toString();
                    String pc = pcText.getText().toString();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(NAME, na);
                    editor.putString(LASTNAME, ln);
                    editor.putString(EMAIL, em);
                    editor.putString(CITY, city);
                    editor.putString(PHONE, ph);
                    editor.putString(ADDRESS, adrs);
                    editor.putString(POSTALCODE, pc);
                    //validar lo anterior
                    if (true)
                        editor.putBoolean(LOGGED_IN, true);
                    editor.commit();

                    Intent in = new Intent(LoginActivity.this, ShoppingActivity.class);
                    startActivity(in);
                }
                else{
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.fillFields),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void exit(View view){
        finish();
    }
}
