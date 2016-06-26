package usm.cc.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import usm.cc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MAIN ACTIVITY";
    // state = 0, UserFragment; state = 1, AddressFragment
    private int state = 0;
    private Button nextButton;
    private Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* UserFragment uFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(UserFragment.ARG_POSITION, position);
        uFragment.setArguments(args);
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();
        fTransaction.add(R.id.fragmentContainerLayout,uFragment);
        fTransaction.commit();*/
        nextButton = (Button) findViewById(R.id.next);
        exitButton = (Button) findViewById(R.id.exit);
        nextButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //FragmentManager fManager = getSupportFragmentManager();
        //FragmentTransaction fTransaction = fManager.beginTransaction();
        switch(v.getId()){
            case R.id.next:
                break;
            case R.id.exit:
                finishAffinity();
                break;


                /*if(state == 0){
                    fTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    fTransaction.replace(R.id.fragmentContainerLayout, new AddressFragment());
                    fTransaction.addToBackStack(null);
                    exitButton.setText(R.string.prev);
                    nextButton.setText(R.string.end);
                    state = 1;
                    fTransaction.commit();
                }
                else{
                    Intent i = new Intent(MainActivity.this, ProductListActivity.class);
                    startActivity(i);
                }
                break;

            case R.id.exit:
                if(state == 0){
                    this.finishAffinity();
                }
                else{
                    fTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                    fTransaction.replace(R.id.fragmentContainerLayout, new UserFragment());
                    exitButton.setText(R.string.exit);
                    nextButton.setText(R.string.next);
                    state = 0;
                    fTransaction.commit();
                }
                break;*/
        }
    }
}
