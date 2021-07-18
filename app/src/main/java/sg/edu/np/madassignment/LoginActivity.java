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

public class LoginActivity extends AppCompatActivity {

    Button loginButton;

    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView newSignup = findViewById(R.id.newsignup);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("hi");
        myRef.push().setValue("hello");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });





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
                //compare username and password
                if(isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString()))
                {
                    SharedPreferences.Editor editor = 	getSharedPreferences("Loggedin", MODE_PRIVATE).edit();
                    editor.putString("User", etUsername.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Valid Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public boolean isValidCredentials(String username, String password)
    {


        User dbUsername = dbHandler.findUser(username);
        if(dbUsername == null)
        {
            Toast.makeText(this, "User does not exist. Please Signup.", Toast.LENGTH_SHORT).show();

        }
        else
        {
            if(dbUsername.getUsername().equals(username) && dbUsername.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}