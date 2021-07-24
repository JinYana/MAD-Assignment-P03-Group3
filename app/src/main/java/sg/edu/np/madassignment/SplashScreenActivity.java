package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class SplashScreenActivity extends Activity {
    private final static String TAG = "SplashScreen Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://uselessfacts.jsph.pl/random.json?language=en";

        TextView factsOfTheDay = findViewById(R.id.quoteSplash);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            String facts = "";

            try {
                facts =  response.getString("text");

            }
            catch (JSONException e) {
                e.printStackTrace();

            }

            // Display facts
            Log.v(TAG, "FACTS: " + facts);
            factsOfTheDay.setText(facts);

        }, error -> Toast.makeText(SplashScreenActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "starting");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "destroy");
        SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "restart");
    }
}