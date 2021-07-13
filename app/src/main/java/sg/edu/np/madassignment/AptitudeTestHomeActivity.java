package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AptitudeTestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test_home);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.page_1:
                        Intent a = new Intent(AptitudeTestHomeActivity.this,CategoryActivity.class);
                        startActivity(a);


                        break;

                    case R.id.page_2:
                        break;

                    case R.id.page_3:
                        Intent b = new Intent(AptitudeTestHomeActivity.this, ProfileActivity.class);
                        startActivity(b);
                        break;





                }
                return false;
            }
        });


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