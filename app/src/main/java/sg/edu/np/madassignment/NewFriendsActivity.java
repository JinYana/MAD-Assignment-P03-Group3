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
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.view.View.GONE;

public class NewFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);

        Context context = this;

        TextView noreq = findViewById(R.id.noreq);
        noreq.setVisibility(View.GONE);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

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




                if (!map.isEmpty()) {
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




                    RecyclerView recyclerView = findViewById(R.id.newfriendreq);
                    RequestAdapter mAdapter = new RequestAdapter(friendreqlist);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);

                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);





                }
                else {
                    noreq.setVisibility(View.VISIBLE);
                }




            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }


        });




    }
}