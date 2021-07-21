package sg.edu.np.madassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileViewHolder> {
    ArrayList<User> data;


    public ProfileAdapter(ArrayList<User> input) {
        data = input;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler,
                parent,
                false
        );


        return new ProfileViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        User u = data.get(position);

        holder.name.setText(u.getUsername());
        holder.level.setText("Level" + String.valueOf(u.getLevel()));
        holder.profileppic.setImageURI(Uri.parse(u.getProfilepicture()));




    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
