package com.example.android.tabswithswipes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FriendsChildFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View v = (View) inflater.inflate(R.layout.friends_child_fragment, container, false);
        return v;
    }
}
