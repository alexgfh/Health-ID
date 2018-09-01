package com.adbhack.healthid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adbhack.healthid.models.Patient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewPatientActivity extends BaseActivity {

    private static final String TAG = "NewPatientActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText mTitleField;
    private EditText mBodyField;

    private EditText mPatientIDField;
    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        mTitleField = findViewById(R.id.field_title);

        mPatientIDField = findViewById(R.id.field_patient_id);
        mBodyField = findViewById(R.id.field_body);

        mSubmitButton = findViewById(R.id.fab_submit_post);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });
    }

    private void submitPost() {

        final String patientID = mPatientIDField.getText().toString();
        final String name = mTitleField.getText().toString();
        final String age = mBodyField.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(name)) {
            mTitleField.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(age)) {
            mBodyField.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]

        writeNewPost(patientID, name, age);
        /*
        mDatabase.child("adb-ultrahack/healthid/patients").child("02").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        writeNewPost(user.username, name, age);

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });*/
        // [END single_value_read]
    }

    private void setEditingEnabled(boolean enabled) {
        mTitleField.setEnabled(enabled);
        mBodyField.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String patientID, String name, String age) {
        // Create new patient at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        //String key = mDatabase.child("healthid/patients/").child(patientID).getKey();
        Patient patient = new Patient(null, name, age);
        Map<String, Object> postValues = patient.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("healthid/patients/" + patientID, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
