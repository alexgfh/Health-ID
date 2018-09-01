package com.adbhack.healthid.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adbhack.healthid.R;
import com.adbhack.healthid.models.Patient;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.post_title);
        authorView = itemView.findViewById(R.id.post_author);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.post_num_stars);
        bodyView = itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(Patient patient, View.OnClickListener starClickListener) {
        titleView.setText(patient.name);
        authorView.setText(patient.patientID);
        bodyView.setText(patient.age);

        starView.setOnClickListener(starClickListener);
    }
}
