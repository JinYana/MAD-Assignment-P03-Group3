package sg.edu.np.madassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProgressActivity extends AppCompatActivity {
    private final static String TAG = "Progress Activity";

    Button backBtn;
    TextView animalSc, animeSc, cartoonSc, computerSc, mathSc, mythSc, sportSc, videoGameSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);
        DatabaseReference newRef = database.getReference("User");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String aptAnimalScore = snapshot.child("aptAnimalScore").getValue(Integer.class).toString();
                    String aptAnimeScore = snapshot.child("aptAnimeScore").getValue(Integer.class).toString();
                    String aptCartoonScore = snapshot.child("aptCartoonScore").getValue(Integer.class).toString();
                    String aptComputerScore = snapshot.child("aptComputerScore").getValue(Integer.class).toString();
                    String aptMathScore = snapshot.child("aptMathScore").getValue(Integer.class).toString();
                    String aptMythScore = snapshot.child("aptMythScore").getValue(Integer.class).toString();
                    String aptSportScore = snapshot.child("aptSportScore").getValue(Integer.class).toString();
                    String aptVideoGameScore = snapshot.child("aptVideoGameScore").getValue(Integer.class).toString();

                    Log.v(TAG, "" + aptAnimalScore + "\n" + aptAnimeScore + "\n" + aptCartoonScore + "\n" + aptComputerScore + "\n" + aptMathScore + "\n" + aptMythScore + "\n" + aptSportScore + "\n" + aptVideoGameScore);

                    animalSc = findViewById(R.id.animalScore);
                    animeSc = findViewById(R.id.animeScore);
                    cartoonSc = findViewById(R.id.cartoonScore);
                    computerSc = findViewById(R.id.compScore);
                    mathSc = findViewById(R.id.mathScore);
                    mythSc = findViewById(R.id.mythScore);
                    sportSc = findViewById(R.id.sportsScore);
                    videoGameSc = findViewById(R.id.gameScore);


                    animalSc.setText("Animal: " + aptAnimalScore);
                    animeSc.setText("Anime: " + aptAnimeScore);
                    cartoonSc.setText("Cartoon: " + aptCartoonScore);
                    computerSc.setText("Computer: " + aptComputerScore);
                    mathSc.setText("Math: " + aptMathScore);
                    mythSc.setText("Myth: " + aptMythScore);
                    sportSc.setText("Sports: " + aptSportScore);
                    videoGameSc.setText("Video Game: " + aptVideoGameScore);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        backBtn = findViewById(R.id.back2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgressActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}