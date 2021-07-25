package sg.edu.np.madassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FriendAdapter extends RecyclerView.Adapter<FriendsViewHolder> {
    ArrayList<User> data;
    Context context;


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

        context = parent.getContext();


        return new FriendsViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        User u = data.get(position);

        holder.name.setText(u.getUsername());
        holder.level.setText("Level" + String.valueOf(u.getLevel()));


        SharedPreferences logprefs = context.getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");



        if(u.getProfilepicture().matches("")){
            holder.profileppic.setImageResource(R.drawable.user);
        }
        else {
            StorageReference storage = FirebaseStorage.getInstance("gs://mad-project-2-eeea1.appspot.com/").getReference();
            StorageReference pathReference = storage.child(u.getUsername() + ".jpg");
            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    Picasso.with(context).load(uri).into(holder.profileppic);

                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
