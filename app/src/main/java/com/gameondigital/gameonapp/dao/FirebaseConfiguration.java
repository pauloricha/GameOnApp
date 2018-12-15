package com.gameondigital.gameonapp.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by pmoreirr on 24/09/2017.
 */

public class FirebaseConfiguration {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;

    public static DatabaseReference getFirebase(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }
}
