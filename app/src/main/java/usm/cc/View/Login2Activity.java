package usm.cc.View;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import usm.cc.Model.User;
import usm.cc.R;

import static android.Manifest.permission.READ_CONTACTS;

public class Login2Activity extends AppCompatActivity {


    private AutoCompleteTextView cityTextInput;
    private AutoCompleteTextView addressTextInput;
    private AutoCompleteTextView pCodeTextInput;
    private User usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        usuario = new User();
        Log.d("USUARIO LOGIN 2" , getIntent().getStringExtra("User") );
        setUI();
    }

    private void setUI() {

        addressTextInput = (AutoCompleteTextView) findViewById(R.id.address);
        cityTextInput = (AutoCompleteTextView) findViewById(R.id.city);
        pCodeTextInput = (AutoCompleteTextView) findViewById(R.id.postalCode);
        setUser();
        Button mEmailSignInButton = (Button) findViewById(R.id.save);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                Intent i = new Intent(Login2Activity.this, ProductListActivity.class);
                i.putExtra("User",(Parcelable) usuario);
                startActivity(i);

            }
        });
    }

    private void setUser() {
        usuario.setAddress(addressTextInput.getText().toString());
        usuario.setCity(cityTextInput.getText().toString());
        usuario.setPostalCode(pCodeTextInput.getText().toString());
    }
}
