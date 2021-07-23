package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        ImageView choose = findViewById(R.id.choose);

        ObjectAnimator animation = ObjectAnimator.ofFloat(choose, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

        SharedPreferences pref = getSharedPreferences("Loggedin",MODE_PRIVATE);
        String User = pref.getString("User","1");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(User);




        Button GoTest = findViewById(R.id.takeAptQuiz);
        GoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){

                            Boolean takenAptQuiz = dataSnapshot.child("takenAptQuiz").getValue(Boolean.class);

                            if (takenAptQuiz.equals(true)){


                                Intent intent = new Intent(AptitudeTestHomeActivity.this, CategoryActivity.class);
                                startActivity(intent);








                            }

                            else{

                                SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                editor.putInt("loopCat" ,0);
                                editor.putInt("AptQuestionsAnswered", 0);

                                for(int i = 0; i < 8; i++){

                                    editor.putInt(String.valueOf(i), 0);


                                }

                                editor.apply();
                                Intent intent = new Intent(AptitudeTestHomeActivity.this, AptitudeTestActivity.class);
                                startActivity(intent);


                            }




                        }




                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });


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
