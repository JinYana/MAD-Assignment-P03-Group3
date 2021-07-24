package sg.edu.np.madassignment;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendsViewHolder> {
    ArrayList<User> data;


    public FriendAdapter(ArrayList<User> input) {
        data = input;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.friendrecycler,
                parent,
                false
        );


        return new FriendsViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
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
