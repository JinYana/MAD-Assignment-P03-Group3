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


public class SignupActivity extends AppCompatActivity {

    DBHandler dbHandler = new DBHandler(this, null , null, 1);
    public long maxid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText signupUser = findViewById(R.id.settingsusername);
        EditText signupPassword = findViewById(R.id.settingspassword);
        EditText signupConfirm = findViewById(R.id.confrimpassword);
        EditText signupEmail = findViewById(R.id.settingemail);

        Button signup = findViewById(R.id.signup);



        TextView signin = findViewById(R.id.signin);
        signin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                return false;
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbUsername = signupUser.getText().toString();
                String dbPassword = signupPassword.getText().toString();
                String dbConfrim = signupConfirm.getText().toString();
                String dbEmail = signupEmail.getText().toString();
                User dbUser = new User();

                boolean usernameOK = false;
                boolean paswordOK = false;
                boolean emailOK = false;


                User dbData = dbHandler.findUser(signupUser.getText().toString());

                if(dbData == null)
                {
                    dbUser.setUsername(dbUsername);
                    usernameOK = true;


                }
                else
                {
                    signupUser.setError("Username already exsists");

                }
                if(dbPassword.equals(dbConfrim)){

                    dbUser.setPassword(dbPassword);
                    paswordOK = true;
                }
                else {
                    signupConfirm.setError("Passwords do not match");
                }
                if(isValidEmail(dbEmail)){
                    dbUser.setEmail(dbEmail);
                    emailOK = true;
                }
                else {
                    signupEmail.setError("Email does not exsist");
                }


                if(usernameOK && paswordOK && emailOK){


                    //adding user info into database
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    DatabaseReference myRef = database.getReference().child("User");

                    //generating auto increment id
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                maxid = snapshot.getChildrenCount();
                                Log.d("TAG", "Value is: " + maxid);
                                SharedPreferences.Editor editor = 	getSharedPreferences("firebaseid", MODE_PRIVATE).edit();
                                editor.putInt("ID", (int) maxid);
                                editor.apply();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull  DatabaseError error) {

                        }
                    });

                    SharedPreferences logprefs = getSharedPreferences("firebaseid", MODE_PRIVATE);
                    int id = logprefs.getInt("ID", 0);




                    dbUser.setUsername(dbUsername);
                    dbUser.setPassword(dbPassword);
                    dbUser.setEmail(dbEmail);
                    dbUser.setDescription("-");
                    dbUser.setLevel(1);
                    dbUser.setProfilepicture("");
                    dbUser.setId(id + 1);
                    dbHandler.addUser(dbUser);

                    myRef.child(dbUsername).setValue(dbUser);


                    Toast.makeText(SignupActivity.this, "New User Created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

