package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView newSignup = findViewById(R.id.newsignup);


        newSignup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

                return false;
            }
        });

        loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.UserName);
                EditText etPassword = findViewById(R.id.UserPassword);

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference myRef = database.getReference("User").child(etUsername.getText().toString());

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if(dataSnapshot.exists()){
                            String password = dataSnapshot.child("password").getValue(String.class);
                            String username = dataSnapshot.child("username").getValue(String.class);

                            //Load User's aptitude score
                            int aptanimescore = dataSnapshot.child("aptAnimeScore").getValue(int.class);
                            int aptcomputerscore = dataSnapshot.child("aptComputerScore").getValue(int.class);
                            int aptmathscore = dataSnapshot.child("aptMathScore").getValue(int.class);
                            int aptanimalscore = dataSnapshot.child("aptAnimalScore").getValue(int.class);
                            int aptmythscore = dataSnapshot.child("aptMythScore").getValue(int.class);
                            int aptcartoonscore = dataSnapshot.child("aptCartoonScore").getValue(int.class);
                            int aptsportscore = dataSnapshot.child("aptSportScore").getValue(int.class);
                            int aptvideogamescore = dataSnapshot.child("aptVideoGameScore").getValue(int.class);
                            SharedPreferences.Editor charteditor = 	getSharedPreferences("chartscore", MODE_PRIVATE).edit();
                            charteditor.putInt("chartanimescore",aptanimescore);
                            charteditor.putInt("chartcomputerscore",aptcomputerscore);
                            charteditor.putInt("chartmathscore",aptmathscore);
                            charteditor.putInt("chartanimalscore",aptanimalscore);
                            charteditor.putInt("chartmythscore",aptmythscore);
                            charteditor.putInt("chartcartoonscore",aptcartoonscore);
                            charteditor.putInt("chartsportscore",aptsportscore);
                            charteditor.putInt("chartvideogamescore",aptvideogamescore);
                            charteditor.apply();

                            if (username != null || password != null){




                                if (username.equals(etUsername.getText().toString()) && password.equals(etPassword.getText().toString())) {
                                    SharedPreferences.Editor editor = getSharedPreferences("Loggedin", MODE_PRIVATE).edit();
                                    editor.putString("User", etUsername.getText().toString());
                                    editor.apply();




                                    Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Valid Credentials", Toast.LENGTH_SHORT).show();

                                }

                                else {
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                }


                            }


                            else {
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                            }


                        }





                        else {
                            Toast.makeText(LoginActivity.this, "Please Make An Account", Toast.LENGTH_SHORT).show();

                        }
                          myRef.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });


            }
        });
    }


}