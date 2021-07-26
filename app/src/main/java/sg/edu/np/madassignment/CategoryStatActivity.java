package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_stat);

        Intent stats = getIntent();

        int attempts = stats.getIntExtra("attempts", 0);
        int correct = stats.getIntExtra("correct", 0);

        double attemptsd = stats.getIntExtra("attempts", 0);
        double correctd = stats.getIntExtra("correct", 0);

        String catname = stats.getStringExtra("cat");

        double accu = 0;

        int incorrect = attempts - correct;


        if(attempts > 0){
            accu = (correctd / attemptsd) * 100;
        }



        TextView title = findViewById(R.id.statstitle);
        TextView tried = findViewById(R.id.tried);
        TextView right = findViewById(R.id.right);
        TextView wrong = findViewById(R.id.incorrect);
        TextView accuracy = findViewById(R.id.accuracy);

        title.setText("Stats for: " + catname);

        tried.setText("Questions attempted: " + attempts);
        right.setText("Questions Answered Correctly: " + correct);
        wrong.setText("Question Answered Wrongly " + incorrect);
        accuracy.setText("Accuracy: " + accu + "%");



    }
}