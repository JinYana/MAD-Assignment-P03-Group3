package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class TriviaActivity extends AppCompatActivity {
    Random random = new Random();
    int totalscore = 0;
    int totalquestionsanswered = 0;

    CountDownTimer cdt;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        SharedPreferences prefs = 	getSharedPreferences("Gameinfo", MODE_PRIVATE);
        totalscore = prefs.getInt("Score", 0);
        totalquestionsanswered = prefs.getInt("QuestionsAnswered", 0);
        String token = prefs.getString("token", "");

        LottieAnimationView checkmark = findViewById(R.id.trophy);
        checkmark.setVisibility(View.GONE);

        LottieAnimationView cross = findViewById(R.id.cross);
        cross.setVisibility(View.GONE);



        TextView question = findViewById(R.id.question);
        TextView score = findViewById(R.id.score);
        score.setText( totalscore + "/10");

        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);

        ArrayList<Button> allbuttons = new ArrayList<Button>();
        allbuttons.add(answer1);
        allbuttons.add(answer2);
        allbuttons.add(answer3);
        allbuttons.add(answer4);

        ImageButton close = findViewById(R.id.closebutton);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gameeditor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                gameeditor.putInt("QuestionsAnswered", 0);
                gameeditor.putInt("Score", 0);
                gameeditor.apply();
                Intent intent = new Intent(TriviaActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });



        Intent receive = getIntent();

        String gameid = receive.getStringExtra("gameid");

        if(gameid == null){
            Log.d("Debug", "wrong");

        }

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://opentdb.com/api.php?amount=1&category=" + gameid + "&type=multiple&token=" + token;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            JSONObject triviainfo ;
            String questiontext = " ";
            String correctans = " ";
            ArrayList<String> wrongans = new ArrayList<String>();


            try {
                //extracting values from the JSON response
                JSONArray results = response.getJSONArray("results");

                triviainfo = results.getJSONObject(0);
                questiontext = triviainfo.getString("question");
                correctans = triviainfo.getString("correct_answer");
                JSONArray incorrectans = triviainfo.getJSONArray("incorrect_answers");
                for(int i = 0; i <= 2; i++){
                    wrongans.add(incorrectans.getString(i));
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }
            question.setText(Html.fromHtml(questiontext));

            //To randomise position of the correct ans
            int randomisecorrect = random.nextInt(3);

            //Setting up correct ans button
            Button correctbutton = allbuttons.get(randomisecorrect);

            correctbutton.setText(Html.fromHtml(correctans));

        correctbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkmark.setVisibility(View.VISIBLE);
                    cdt = new CountDownTimer(500, 1000) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            if(totalquestionsanswered < 9){


                                SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                                editor.putInt("Score", totalscore + 1);
                                editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                editor.apply();
                                startActivity(getIntent());
                            }
                            else {
                                SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();

                                editor.putInt("Score", totalscore + 1);
                                editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                editor.apply();
                                Intent intent = new Intent(TriviaActivity.this, TriviaEndActivity.class);
                                startActivity(intent);
                            };
                        }
                    }.start();




                }
            });

            //Removing correct ans button from list
            allbuttons.remove(randomisecorrect);

            //Setting up wrong ans buttons
            for(int i = 0; i < allbuttons.size() ; i++){

                Button wrongbutton = allbuttons.get(i);


                wrongbutton.setText(Html.fromHtml(wrongans.get(i)));

                wrongbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        cross.setVisibility(View.VISIBLE);
                        cdt = new CountDownTimer(800, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                if(totalquestionsanswered < 9){

                                    SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                                    editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                    editor.apply();

                                    startActivity(getIntent());
                                }
                                else {
                                    SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                                    editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                    editor.apply();

                                    Intent intent = new Intent(TriviaActivity.this, TriviaEndActivity.class);
                                    startActivity(intent);
                                }

                            }
                        }.start();

                    }
                });

            }


        }, error -> Toast.makeText(TriviaActivity.this, "something wrong", Toast.LENGTH_SHORT).show());

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