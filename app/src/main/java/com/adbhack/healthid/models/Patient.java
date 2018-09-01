package com.adbhack.healthid.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Patient {

    public String uid;
    public String patientID;
    public String name;
    public String age;
    public Map<String, Boolean> stars = new HashMap<>();

    public Patient() {
        // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    }

    public Patient(String uid, String patientID, String name, String age) {
        this.uid = uid;
        this.patientID = patientID;
        this.name = name;
        this.age = age;
    }
    public Patient(String patientID, String name, String age) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("patientID", patientID);
        result.put("name", name);
        result.put("age", age);
        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
