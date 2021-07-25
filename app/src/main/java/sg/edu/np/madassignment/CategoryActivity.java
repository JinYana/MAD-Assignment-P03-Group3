package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

public class CategoryActivity extends AppCompatActivity {
    String token = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        Log.v("Debug", "create");


        //Setting up bottom nav bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(0).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {


                    case R.id.page_1:


                        break;

                    case R.id.page_2:
                        Intent a = new Intent(CategoryActivity.this,AptitudeTestHomeActivity.class);
                        startActivity(a);
                        break;

                    case R.id.page_3:
                        Intent b = new Intent(CategoryActivity.this, ProfileActivity.class);
                        startActivity(b);
                        break;

                }
                return false;
            }
        });


        // Instantiate the RequestQueue
        // Requesting session token
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api_token.php?command=request";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {


            try {
                //extracting values from the JSON response
                token = response.getString("token");

                SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putString("token", token);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> Toast.makeText(CategoryActivity.this, "something wrong", Toast.LENGTH_SHORT).show());

        queue.add(request);

        //Adding onclick listeners to all the category buttons
        ImageButton game = findViewById(R.id.gameButton);
        ImageButton computer = findViewById(R.id.computer);
        ImageButton math= findViewById(R.id.math);
        ImageButton myth = findViewById(R.id.mythology);
        ImageButton anime = findViewById(R.id.anime);
        ImageButton animal = findViewById(R.id.animal);
        ImageButton cartoon = findViewById(R.id.cartoon);
        ImageButton sport = findViewById(R.id.sport);














        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "15");

                startActivity(intent);
            }
        });


        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "18");

                startActivity(intent);
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "19");

                startActivity(intent);
            }
        });

        myth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "20");

                startActivity(intent);
            }
        });


        anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "31");

                startActivity(intent);
            }
        });


        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "27");

                startActivity(intent);
            }
        });


        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "32");

                startActivity(intent);
            }
        });


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                powerupeditor.putInt("powerup1count", 1);
                powerupeditor.putInt("powerup2count", 1);
                powerupeditor.apply();

                Intent intent = new Intent(CategoryActivity.this, TriviaActivity.class);
                intent.putExtra("gameid", "21");

                startActivity(intent);
            }
        });
























    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Debug", "sassastop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Debug", "destroy");


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("Debug", "restart");
    }


}