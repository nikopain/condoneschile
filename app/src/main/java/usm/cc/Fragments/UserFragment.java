package usm.cc.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import usm.cc.R;

public class UserFragment extends Fragment {
    public UserFragment() {
        // Required empty public constructor
    }
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mLastnameView;
    private AutoCompleteTextView mNameView;
    private EditText mPhoneView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}
