package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class AptitudeTestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test_home);
        ImageView choose = findViewById(R.id.choose);

        ObjectAnimator animation = ObjectAnimator.ofFloat(choose, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();


        Button GoTest = findViewById(R.id.takeAptQuiz);
        GoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AptitudeTestHomeActivity.this, AptitudeTestActivity.class);
                startActivity(intent);
            }
        });


        Button noTest = findViewById(R.id.dontTakeAptQuiz);
        noTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AptitudeTestHomeActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });





    }
}