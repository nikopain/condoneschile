package usm.cc.misc;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import usm.cc.R;

/**
 * Created by niko on 04/07/2016.
 */
public class SettingsEdit extends Dialog implements View.OnClickListener {
    private EditText editText;
    public SettingsEdit(Context context){
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text_dialog);
        Button btnOk = (Button) findViewById(R.id.button_ok);
        editText = (EditText) findViewById(R.id.edit_text);
        btnOk.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        editText.getText().toString();
        dismiss();
    }
}
