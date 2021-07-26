package sg.edu.np.madassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AchievementActivity extends AppCompatActivity {
    private final static String TAG = "Achivement Activity";
    TextView achv, achv1, achv2, achv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        achv = findViewById(R.id.achv1);
        achv1 = findViewById(R.id.achv2);
        achv2 = findViewById(R.id.achv);
        achv3 = findViewById(R.id.achv3);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);
        DatabaseReference newRef = database.getReference("User");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    Boolean quizTaken = snapshot.child("takenAptQuiz").getValue(Boolean.class);
                    Log.v(TAG, "" + quizTaken);

                    if (quizTaken.equals(true)) {
                        achv.setText("You took the aptitude test for the first time!");
                    } else {
                        achv.setText("Try and take the aptitude test now.");
                        Log.v(TAG, "You have not achieved this achievement...");

                    }

                    Integer level = snapshot.child("level").getValue(Integer.class);

                    if (level > 3) {
                        achv1.setText("You've reached level 3! Let's keep on going!");

                    } else {
                        achv1.setText("Keep it up! Bring up your levels a bit more.");

                    }

                    if (!snapshot.child("friendslist").exists())
                    {
                        achv2.setText("Invite your friends and play together!");

                    }
                    else {
                        achv2.setText("Congrats! You have made a new friend.");

                    }

                    if (level > 5) {
                        achv3.setText("You've reached level 5! You have earned the title 'Beginner'");

                    } else {
                        achv3.setText("Don't worry. Keep trying your best and raise your level more.");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.v(TAG, "Something went wrong...");
            }

        });

        Button backbtn = findViewById(R.id.back1);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}