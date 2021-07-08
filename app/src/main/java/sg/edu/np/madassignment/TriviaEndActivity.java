package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

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
                Intent intent = new Intent(TriviaEndActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = 	getSharedPreferences("Gameinfo", MODE_PRIVATE);

        TextView score = findViewById(R.id.totalscore);

        int playerscore = prefs.getInt("Score", 0);

        score.setText("Score: " + playerscore + "/10");
    }
}