package sg.edu.np.madassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
                        achv.setText("APTITUDE!");
                    }

                    else if (quizTaken.equals(false)) {
                        achv.setText("APTITUDE!(LOCKED)");
                        //set image black if havent get achievements
                       ImageView imageachv1 = findViewById(R.id.imageViewachv1);
                       imageachv1.setImageResource(R.drawable.lock);

                    }


                    Integer level = snapshot.child("level").getValue(Integer.class);

                    if (level >= 3) {
                        achv1.setText("GETTING THERE!");

                    }

                    else if (level < 3){
                        achv1.setText("GETTING THERE!(LOCKED)");
                        ImageView imageachv2 = findViewById(R.id.imageViewachv2);
                        imageachv2.setImageResource(R.drawable.lock);
                    }

                    if (snapshot.child("friendslist").exists())
                    {
                        achv2.setText("SOCIAL BUTTERFLY!");



                    }

                    else if (!snapshot.child("friendslist").exists()){
                        achv2.setText("SOCIAL BUTTERFLY!(LOCKED)");
                        //set image black
                        ImageView imageachv3 = findViewById(R.id.imageViewachv3);
                        imageachv3.setImageResource(R.drawable.lock);


                    }


                    if (level >= 5) {
                        achv3.setText("NOVICE!");

                    }

                    else if (level < 5){
                        achv3.setText("NOVICE!(LOCKED)");
                        //set image black
                        ImageView imageachv4 = findViewById(R.id.imageViewachv4);
                        imageachv4.setImageResource(R.drawable.lock);

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