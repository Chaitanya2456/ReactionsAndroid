package com.example.android.tabswithswipes;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class FireBaseMethods {

    static  FirebaseDatabase  database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference();
    //DatabaseReference myRef2 = database.getReference("user_info");
    static int usernameexists = 0;
    static  FirebaseAuth auth = FirebaseAuth.getInstance();
    static boolean isUnique = true;


    public static void insertInDataBase(final String getEmailId, final String getusername, final String getFullName) {
        Log.d("Enter", "yes");
        Models user = new Models(getEmailId,getusername);
        Models2 userinfo = new Models2(getEmailId,getFullName,getusername);
        String UserID = auth.getCurrentUser().getUid();
        Log.d("userid",UserID);
        Log.d("tree", myRef.child("users").child("YkH8avdKK3TJ9h9YxsWq5zqPCTH2").child("email_adr").toString());
        myRef.child("users").child(UserID).setValue(user);
        myRef.child("user_info").child(UserID).setValue(userinfo);
        Log.d("added", "account is added in db");
        //return isUnique;
    }

    public static boolean CheckForUsernameInDataBase(final String getusername) {


        myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("check username unique", getusername);

                Models usertemp = new Models();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Log.d("snapshot", ds.toString());

                    usertemp.setUsername(ds.getValue(Models.class).getUsername());
                    Log.d("username of temp", usertemp.getUsername());
                    if(usertemp.getUsername().equals(getusername)){
                        Log.d("entring", "shit");
                        usernameexists = 1;
                        isUnique = false;
                        break;
                    }

                }

                /*if(usernameexists == 0){

                }else{
                    Log.d("usernameexists", "User name is taken");
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        return isUnique;
    }


    public static void reinit() {
        isUnique = true;
    }
}
