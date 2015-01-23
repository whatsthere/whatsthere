package com.app.whatsthere;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.widget.LoginButton;




public class FaceBookLoginFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_facebook_login,container,false);
        TextView titlTextView = (TextView) view.findViewById(R.id.titleTextView);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       LoginButton loginButton = (LoginButton)view.findViewById(R.id.login_button);
       loginButton.setReadPermissions("public_profile");
    }
}
