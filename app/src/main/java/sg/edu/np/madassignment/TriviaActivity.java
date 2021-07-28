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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;


public class TriviaActivity extends AppCompatActivity {
    private final static String TAG = "Trivia Activity";

    Random random = new Random();
    int totalscore = 0;
    int totalquestionsanswered = 0;



    CountDownTimer cdt;



    int powerup1 = 1;
    int powerup2 = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        //getting user info
        SharedPreferences logprefs = 	getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User","");


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);



        //getting game info
        SharedPreferences gameprefs = 	getSharedPreferences("Gameinfo", MODE_PRIVATE);
        totalscore = gameprefs.getInt("Score", 0);
        totalquestionsanswered = gameprefs.getInt("QuestionsAnswered", 0);

        //storing number of times power up is used
        SharedPreferences powerupprefs = getSharedPreferences("powerupcount",MODE_PRIVATE);
        powerup1 = powerupprefs.getInt("powerup1count",1);
        powerup2 = powerupprefs.getInt("powerup2count",1);



        String token = gameprefs.getString("token", "");

        LottieAnimationView checkmark = findViewById(R.id.trophy);
        checkmark.setVisibility(View.GONE);

        LottieAnimationView cross = findViewById(R.id.cross);
        cross.setVisibility(View.GONE);

        LottieAnimationView bomb = findViewById(R.id.explosion);
        bomb.setVisibility(View.GONE);



        TextView question = findViewById(R.id.Aptquestion);


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



        //Show question progress
        ProgressBar questionprogress = findViewById(R.id.answerProgress);
        questionprogress.setMax(11);
        questionprogress.setProgress(totalquestionsanswered + 1);

        TextView questionNo = findViewById(R.id.questionNo);
        questionNo.setText("Q" +  totalquestionsanswered + 1);

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

        //get the category id chosen
        Intent receive = getIntent();

        String gameid = receive.getStringExtra("gameid");





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





            //display question
            question.setText(Html.fromHtml(questiontext));

            //To randomise position of the correct ans
            int randomisecorrect = random.nextInt(4);

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

                                //Adding user game score to firebase based on category;
                                if(gameid.equals("31")){
                                    myRef.child("gameAnimeanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameAnimecorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("18")){
                                    myRef.child("gameComputeranswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameComputercorrect").setValue(ServerValue.increment(1));

                                }
                                else if(gameid.equals("19")){
                                    myRef.child("gameMathanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameMathcorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("27")){
                                    myRef.child("gameAnimalsanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameAnimalscorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("20")){
                                    myRef.child("gameMythanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameMythcorrect").setValue(ServerValue.increment(1));

                                }
                                else if(gameid.equals("32")){
                                    myRef.child("gameCartoonanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameCartooncorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("21")){
                                    myRef.child("gameSportanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameSportcorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("15")){
                                    myRef.child("gameVideoGameanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameVideoGamecorrect").setValue(ServerValue.increment(1));
                                }

                                startActivity(getIntent());




                            }
                            else {




                                SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();

                                editor.putInt("Score", totalscore + 1);
                                editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                editor.apply();

                                SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                                powerupeditor.putInt("powerup1count", 1);
                                powerupeditor.putInt("powerup2count", 1);
                                powerupeditor.apply();

                                if(gameid.equals("31")){
                                    myRef.child("gameAnimeanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameAnimecorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("18")){
                                    myRef.child("gameComputeranswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameComputercorrect").setValue(ServerValue.increment(1));

                                }
                                else if(gameid.equals("19")){
                                    myRef.child("gameMathanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameMathcorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("27")){
                                    myRef.child("gameAnimalsanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameAnimalscorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("20")){
                                    myRef.child("gameMythanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameMythcorrect").setValue(ServerValue.increment(1));

                                }
                                else if(gameid.equals("32")){
                                    myRef.child("gameCartoonanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameCartooncorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("21")){
                                    myRef.child("gameSportanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameSportcorrect").setValue(ServerValue.increment(1));
                                }
                                else if(gameid.equals("15")){
                                    myRef.child("gameVideoGameanswered").setValue(ServerValue.increment(1));
                                    myRef.child("gameVideoGamecorrect").setValue(ServerValue.increment(1));
                                }





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


                ImageView powerUp1 = findViewById(R.id.powerUp1);


                powerUp1.setOnClickListener(new View.OnClickListener() {
                    int clickpowerup1 = 0;
                    @Override

                    public void onClick(View v) {


                        if (powerup1 == 1){
                           if(clickpowerup1 == 0){

                               bomb.setVisibility(View.VISIBLE) ;
                               cdt = new CountDownTimer(1000, 1000) {
                                   @Override
                                   public void onTick(long millisUntilFinished) {

                                   }

                                   @Override
                                   public void onFinish() {

                                       bomb.setVisibility(View.GONE);
                                       Integer powerupIncorrect = random.nextInt(3);

                                       Button showWronganswer = allbuttons.get(powerupIncorrect);

                                       showWronganswer.setText(" ");

                                       allbuttons.remove(powerupIncorrect);

                                       SharedPreferences.Editor powerupeditor = getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                                       powerupeditor.putInt("powerup1count", 0);

                                       powerupeditor.apply();

                                       clickpowerup1 +=1;


                                   }
                               }.start();










                           }

                           else if(clickpowerup1 == 1){
                               Toast usepowerup = Toast.makeText(TriviaActivity.this,"Power Up Used",Toast.LENGTH_SHORT);
                               usepowerup.show();


                           }






                        }

                        else if (powerup1 == 0){
                            Toast usepowerup = Toast.makeText(TriviaActivity.this,"Power Up Used",Toast.LENGTH_SHORT);
                            usepowerup.show();



                        }




                    }
                });



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

                                    if(gameid.equals("31")){
                                        myRef.child("gameAnimeanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("18")){
                                        myRef.child("gameComputeranswered").setValue(ServerValue.increment(1));


                                    }
                                    else if(gameid.equals("19")){
                                        myRef.child("gameMathanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("27")){
                                        myRef.child("gameAnimalsanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("20")){
                                        myRef.child("gameMythanswered").setValue(ServerValue.increment(1));


                                    }
                                    else if(gameid.equals("32")){
                                        myRef.child("gameCartoonanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("21")){
                                        myRef.child("gameSportanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("15")){
                                        myRef.child("gameVideoGameanswered").setValue(ServerValue.increment(1));

                                    }


                                    startActivity(getIntent());
                                }
                                else {

                                    SharedPreferences.Editor editor = 	getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                                    editor.putInt("QuestionsAnswered", totalquestionsanswered + 1);
                                    /*
                                    Intent intent = new Intent(TriviaActivity.this, Result.class);

                                    intent.putExtra("Score", totalscore + 1);
                                    intent.putExtra("QuestionsAnswered", totalquestionsanswered + 1);

                                    editor.clear(); */
                                    editor.apply();

                                    SharedPreferences.Editor powerupeditor = 	getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                                    powerupeditor.putInt("powerup1count", 1);
                                    powerupeditor.putInt("powerup2count", 1);
                                    powerupeditor.apply();

                                    if(gameid.equals("31")){
                                        myRef.child("gameAnimeanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("18")){
                                        myRef.child("gameComputeranswered").setValue(ServerValue.increment(1));


                                    }
                                    else if(gameid.equals("19")){
                                        myRef.child("gameMathanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("27")){
                                        myRef.child("gameAnimalsanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("20")){
                                        myRef.child("gameMythanswered").setValue(ServerValue.increment(1));


                                    }
                                    else if(gameid.equals("32")){
                                        myRef.child("gameCartoonanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("21")){
                                        myRef.child("gameSportanswered").setValue(ServerValue.increment(1));

                                    }
                                    else if(gameid.equals("15")){
                                        myRef.child("gameVideoGameanswered").setValue(ServerValue.increment(1));

                                    }



                                    Intent intent = new Intent(TriviaActivity.this, TriviaEndActivity.class);
                                    startActivity(intent);

                                }

                            }
                        }.start();

                    }
                });

            }


            ImageView powerUp2 = findViewById(R.id.powerUp2);
            powerUp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(powerup2 == 1){SharedPreferences.Editor powerupeditor = getSharedPreferences("powerupcount", MODE_PRIVATE).edit();
                        powerupeditor.putInt("powerup2count", 0);
                        powerupeditor.apply();
                        startActivity(getIntent());}

                    else{
                        Toast usepowerup = Toast.makeText(TriviaActivity.this,"Power Up Used",Toast.LENGTH_SHORT);
                        usepowerup.show();


                    }

                }
            });

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