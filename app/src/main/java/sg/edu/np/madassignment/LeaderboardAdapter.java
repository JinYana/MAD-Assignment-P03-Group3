package sg.edu.np.madassignment;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder>{
    ArrayList<User> data;


    public LeaderboardAdapter(ArrayList<User> input) {
        data = input;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.friendrecycler,
                parent,
                false
        );


        return new LeaderboardViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        User u = data.get(position);

        holder.name.setText(u.getUsername());
        holder.level.setText("Level" + String.valueOf(u.getLevel()));
        if(u.getProfilepicture().matches("")){
            holder.profileppic.setImageResource(R.drawable.user);
        }
        else {
            holder.profileppic.setImageURI(Uri.parse(u.getProfilepicture()));
        }




    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
