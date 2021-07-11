package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class MainPage extends AppCompatActivity {
    String token = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Log.v("Debug", "create");


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


        }, error -> Toast.makeText(MainPage.this, "something wrong", Toast.LENGTH_SHORT).show());

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

        Button logout = findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, LoginActivity.class);
                startActivity(intent);
            }
        });




        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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

                Intent intent = new Intent(MainPage.this, TriviaActivity.class);
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
        Log.v("Debug", "stop");
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