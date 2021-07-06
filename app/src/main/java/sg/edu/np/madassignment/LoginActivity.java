package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    DBHandler dbHandler = new DBHandler(this, null, null, 1);

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
                //compare username and password
                if(isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString()))
                {
                    Intent intent = new Intent(LoginActivity.this, MainPage.class);
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