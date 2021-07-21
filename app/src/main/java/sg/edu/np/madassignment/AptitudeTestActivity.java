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

public class AptitudeTestActivity extends AppCompatActivity {
    Random random = new Random();
    int totalscore = 0;
    int totalquestionsanswered = 0;

    CountDownTimer cdt;

    int loopCat = 0 ;









    // add to list //pass to viewapt



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test);

        SharedPreferences prefs = 	getSharedPreferences("catGameinfo", MODE_PRIVATE);
        totalscore = prefs.getInt("AptScore", 0);
        totalquestionsanswered = prefs.getInt("AptQuestionsAnswered", 0);
        loopCat = prefs.getInt("loopCat",0);







        LottieAnimationView checkmark = findViewById(R.id.checkmark);
        checkmark.setVisibility(View.GONE);

        LottieAnimationView cross = findViewById(R.id.cross);
        cross.setVisibility(View.GONE);



        TextView question = findViewById(R.id.Aptquestion);
        TextView score = findViewById(R.id.score);
        score.setText( totalscore + "/4");

        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);
        Button answer4 = findViewById(R.id.answer4);

        ArrayList<Button> allbuttons = new ArrayList<Button>();
        allbuttons.add(answer1);
        allbuttons.add(answer2);
        allbuttons.add(answer3);
        allbuttons.add(answer4);


        ArrayList<Integer> categoryCode = new ArrayList<Integer>();
        categoryCode.add(31);//anime
        categoryCode.add(18);//computers
        categoryCode.add(19);//math
        categoryCode.add(27);//animals
        categoryCode.add(20);//mythology
        categoryCode.add(32);//cartoon
        categoryCode.add(21);//sports
        categoryCode.add(15);//video games



        ArrayList<Integer> eachCatScoreList =  new ArrayList<Integer>();







        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://opentdb.com/api.php?amount=4&type=multiple" + "&category=" + categoryCode.get(loopCat).toString() ;

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
                            if(totalquestionsanswered < 1){


                                SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                editor.putInt("AptScore", totalscore + 1);
                                editor.putInt("AptQuestionsAnswered", totalquestionsanswered + 1);
                                editor.apply();
                                startActivity(getIntent());
                            }
                            else {

                                SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                editor.putInt("loopCat" ,loopCat + 1);
                                editor.remove("AptScore");
                                editor.remove("AptQuestionsAnswered");
                                editor.putInt(String.valueOf(loopCat),totalscore);
                                editor.apply();

                                loopCat += 1;



                                if (loopCat>7){
                                    editor.putInt(String.valueOf(loopCat),totalscore);

                                    editor.apply();





                                    Intent intent  = new Intent(AptitudeTestActivity.this,ViewAptitudeResultActivity.class);

                                    startActivity(intent);


                                }

                                startActivity(getIntent());

                            }
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
                                if(totalquestionsanswered < 1){

                                    SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                    editor.putInt("AptQuestionsAnswered", totalquestionsanswered + 1);
                                    editor.apply();



                                    startActivity(getIntent());
                                }
                                else {
                                    SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                    editor.putInt("loopCat" ,loopCat + 1);
                                    editor.remove("AptScore");
                                    editor.remove("AptQuestionsAnswered");
                                    editor.putInt(String.valueOf(loopCat),totalscore);
                                    editor.apply();
                                    loopCat += 1;
                                    editor.putInt(String.valueOf(loopCat),totalscore);



                                    if (loopCat>7){


                                        Intent intent  = new Intent(AptitudeTestActivity.this,ViewAptitudeResultActivity.class);

                                        startActivity(intent);
                                    }

                                    startActivity(getIntent());

                                }

                            }
                        }.start();

                    }
                });

            }


        }, error -> Toast.makeText(AptitudeTestActivity.this, "something wrong", Toast.LENGTH_SHORT).show());

        queue.add(request);





    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "start");
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
        SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
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