package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().findItem(R.id.page_3).setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {


                    case R.id.page_1:
                        Intent b = new Intent(LeaderBoardActivity.this, CategoryActivity.class);
                        startActivity(b);


                        break;

                    case R.id.page_2:
                        Intent a = new Intent(LeaderBoardActivity.this, AptitudeTestHomeActivity.class);
                        startActivity(a);
                        break;

                    case R.id.page_3:


                    case R.id.page_4:
                        Intent c = new Intent(LeaderBoardActivity.this, ProfileActivity.class);
                        startActivity(c);

                        break;


                }
                return false;
            }
        });

        Context context = this;

        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference newRef = database.getReference("User");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                ArrayList<User> friendsList = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    friendsList.add(user);
                }


                RecyclerView recyclerView = findViewById(R.id.leaderboard);
                Collections.sort(friendsList);
                LeaderboardAdapter mAdapter = new LeaderboardAdapter(friendsList);

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

}




