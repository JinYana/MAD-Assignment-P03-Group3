package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class TriviaEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_end);

        LottieAnimationView trophy = findViewById(R.id.trophy);
        float trophyscale =(float) 0.5;
        trophy.setScale(trophyscale);

        LottieAnimationView firework = findViewById(R.id.firework);
        float fireworkscale =(float) 0.25;
        firework.setScale(fireworkscale);

        Button backtohome = findViewById(R.id.backtohome);

        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaEndActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });


        //Adding levels to user account
        SharedPreferences prefs = 	getSharedPreferences("Gameinfo", MODE_PRIVATE);

        TextView score = findViewById(R.id.totalscore);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);
        HashMap<String, Object> updates = new HashMap<String,Object>();


        int playerscore = prefs.getInt("Score", 0);

        if(playerscore < 3){
            updates.put("level", ServerValue.increment(1));

        }
        else if(playerscore < 5){
            updates.put("level", ServerValue.increment(2));

        }
        else {
            updates.put("level", ServerValue.increment(3));

        }

        myRef.push().updateChildren(updates);





        score.setText("Score: " + playerscore + "/10");
        SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
        gameeditor.putInt("Score", 0);
    }
}