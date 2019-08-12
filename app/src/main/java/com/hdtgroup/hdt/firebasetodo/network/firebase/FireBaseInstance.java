package com.hdtgroup.hdt.firebasetodo.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseInstance {
    private static DatabaseReference mDatabase = null;

    public static DatabaseReference getDatabaseReference() {
        if (null == mDatabase) {
            return FirebaseDatabase.getInstance().getReference();
        } else {
            return mDatabase;
        }
    }
}
