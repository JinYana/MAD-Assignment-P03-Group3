package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private final static String TAG = "View Result";

    int totalscore = 0;
    int totalquestionsanswered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        totalscore = getIntent().getIntExtra("Score", 0);
        totalquestionsanswered = getIntent().getIntExtra("QuestionsAnswered", 0);

        TextView correct = findViewById(R.id.correct);
        TextView wrong = findViewById(R.id.wrong);

        correct.setText("Correct: " + totalscore);
        wrong.setText("Wrong: " + (totalquestionsanswered - totalscore));

    }

    @Override
    protected void onStart () {
        super.onStart();

        Log.v(TAG, "Start");
    }

    @Override
    protected void onResume () {
        super.onResume();

        Log.v(TAG, "Resume");
    }

    @Override
    protected void onPause () {
        super.onPause();

        Log.v(TAG, "Pause");
    }

    @Override
    protected void onStop () {
        super.onStop();

        Log.v(TAG, "Stop");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();

        Log.v(TAG, "Destroy");
    }
}