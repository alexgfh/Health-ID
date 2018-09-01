package com.adbhack.healthid.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class PatientsFragment extends PostListFragment {

    public PatientsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my posts
        return databaseReference.child("adb-ultrahack").child("healthid").child("patients")
                .child("01");
    }
}
