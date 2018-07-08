package com.example.android.tabswithswipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class Tab3 extends Fragment{
    private TextView userName;
    private Button signOut;
    private FirebaseAuth auth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab3, container, false);
        userName = (TextView)v.findViewById(R.id.userName);
        signOut = (Button)v.findViewById(R.id.signOut);
        auth = FirebaseAuth.getInstance();
        final SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", 0);
        if(preferences.contains("userName")){
              userName.setText(preferences.getString("userName", "Tab3"));}
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Signed out", Toast.LENGTH_SHORT);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                auth.signOut();
                startActivity(new Intent(getActivity(), loginActivity.class));
                getActivity().finish();
            }
        });
        return v;
    }
}
