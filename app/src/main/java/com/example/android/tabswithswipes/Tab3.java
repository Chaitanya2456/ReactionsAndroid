package com.example.android.tabswithswipes;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class Tab3 extends Fragment{
    private TextView userName;
    private Button editProfile;
    private FirebaseAuth auth;
    private ImageView logout, friendsImage, activityImage;
    private TextView fullName;
    private TextView userBio;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab3, container, false);
        logout = (ImageView)v.findViewById(R.id.signOut);
        fullName = (TextView)v.findViewById(R.id.fullName);
        userBio = (TextView) v.findViewById(R.id.bioTextField);
        editProfile = (Button)v.findViewById(R.id.editProfile);
        friendsImage = (ImageView)v.findViewById(R.id.friendsFragment);
        activityImage = (ImageView)v.findViewById(R.id.activityFragment);
        auth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("LOGGING OUT");
                alertDialog.setMessage("Are you sure you want to log out?");
                alertDialog.setIcon(R.drawable.logout);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        auth.signOut();
                        startActivity(new Intent(getActivity(), loginActivity.class));
                        getActivity().finish();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_layout, new FriendsChildFragment());
        transaction.commit();

        activityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityImage.setImageResource(R.drawable.activity_selected);
                friendsImage.setImageResource(R.drawable.friends_unselected);
                android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_layout, new ActivityChildFragment());
                transaction.commit();

            }
        });

        friendsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendsImage.setImageResource(R.drawable.friends_selected);
                activityImage.setImageResource(R.drawable.activity_unselected);
                android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_layout, new FriendsChildFragment());
                transaction.commit();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", 0);
        if(preferences.contains("userName")&&!preferences.getString("userName","").isEmpty()){
            fullName.setText(preferences.getString("userName", fullName.getText().toString()));
        }
        if(preferences.contains("userBio")&&!preferences.getString("userBio","").isEmpty()){
            userBio.setText(preferences.getString("userBio", ""));
        }
    }
}
