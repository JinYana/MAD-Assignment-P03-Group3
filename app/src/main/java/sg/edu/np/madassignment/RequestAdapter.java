package sg.edu.np.madassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder> {
        ArrayList<User> data;


        public RequestAdapter(ArrayList<User> input) {
            data = input;
        }

        @NonNull
        @Override
        public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View item = null;

            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.requestrecycler,
                    parent,
                    false
            );


            return new RequestViewHolder(item);
        }


        @Override
        public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
            User u = data.get(position);



            holder.name.setText(u.getUsername());
            holder.level.setText("Level" + String.valueOf(u.getLevel()));


            //onclick for accept friend request
            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences logprefs = v.getContext().getSharedPreferences("Loggedin", MODE_PRIVATE);
                    String username = logprefs.getString("User", "");
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    DatabaseReference newRef = database.getReference("User");



                    newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            ArrayList<String> childkey = new ArrayList<>();

                            for(DataSnapshot ds : snapshot.child(username).child("friendreq").getChildren()) {
                                String name = ds.getKey();
                                childkey.add(name);
                            }



                            for(int i = 0; i < childkey.size(); i++){
                                Log.v("TAG", ""+childkey.size());
                                Log.v("TAG", u.getUsername());
                                Log.v("TAG", snapshot.child(username).child("friendreq").child(childkey.get(i)).getValue(String.class));
                                String friendkey = snapshot.child(username).child("friendreq").child(childkey.get(i)).getValue(String.class);
                                Log.v("TAG", "" + friendkey.equals(u.getUsername()));

                                if(friendkey.equals(u.getUsername())){
                                    Log.v("TAG", "YO" + snapshot.child(username).child("friendreq").child(childkey.get(i)).getValue(String.class));

                                    newRef.child(username).child("friendreq").child(childkey.get(i)).removeValue();
                                    int friendcount = (int) snapshot.child(username).child("friendslist").getChildrenCount() + 1;
                                    int otherfriendcount = (int) snapshot.child(u.getUsername()).child("friendslist").getChildrenCount() + 1;
                                    newRef.child(username).child("friendslist").child(String.valueOf(friendcount)).setValue(u.getUsername());
                                    newRef.child(u.getUsername()).child("friendslist").child(String.valueOf(otherfriendcount)).setValue(username);

                                }
                            }





                            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                            v.getContext().startActivity(intent);


                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {

                        }
                    });







                }
            });

            holder.ignore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences logprefs = v.getContext().getSharedPreferences("Loggedin", MODE_PRIVATE);
                    String username = logprefs.getString("User", "");
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    DatabaseReference newRef = database.getReference("User");

                    newRef.child(username).child("friendslist").child(String.valueOf(position + 1)).removeValue();
                }
            });




        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

