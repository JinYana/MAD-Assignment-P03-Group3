package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);

        Context context = this;

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");
        SharedPreferences refreshpref = getSharedPreferences("refresh", MODE_PRIVATE);
        boolean refresh = refreshpref.getBoolean("refresh", false);
        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference newRef = database.getReference("User");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                ArrayList<String> map = new ArrayList<>();
                for(DataSnapshot ur : snapshot.child(username).child("friendreq").getChildren()) {
                    String userreq = ur.getValue(String.class);
                    map.add(userreq);
                }




                if (map != null) {
                    ArrayList<User> friendsList = new ArrayList<>();
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        User user = ds.getValue(User.class);
                        friendsList.add(user);
                    }

                    ArrayList<User> friendreqlist = new ArrayList<>();
                    for(int i = 0; i < friendsList.size(); i++){
                        if(map.contains(friendsList.get(i).getUsername())){
                            friendreqlist.add(friendsList.get(i));
                        }
                    }
                    Log.v("hi", "" + friendreqlist.size());



                    RecyclerView recyclerView = findViewById(R.id.newfriendreq);
                    RequestAdapter mAdapter = new RequestAdapter(friendreqlist);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);

                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);

                    if(refresh == true){
                        startActivity(getIntent());
                    }



                }




            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }


        });




    }
}