package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AptitudeTestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude_test_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.page_1:
                        Intent a = new Intent(AptitudeTestHomeActivity.this,CategoryActivity.class);
                        startActivity(a);

                        break;

                    case R.id.page_2:
                        break;

                    case R.id.page_3:
                        Intent b = new Intent(AptitudeTestHomeActivity.this, LeaderBoardActivity.class);
                        startActivity(b);
                        break;

                    case R.id.page_4:
                        Intent c = new Intent(AptitudeTestHomeActivity.this, ProfileActivity.class);
                        startActivity(c);
                        break;

                    case R.id.page_5:
                        Intent d = new Intent(AptitudeTestHomeActivity.this, LoginActivity.class);
                        startActivity(d);
                        break;





                }
                return false;
            }
        });
        ImageView choose = findViewById(R.id.choose);

        ObjectAnimator animation = ObjectAnimator.ofFloat(choose, "rotationY", 0.0f, 360f);
        animation.setDuration(3600);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

        SharedPreferences pref = getSharedPreferences("Loggedin",MODE_PRIVATE);
        String User = pref.getString("User","1");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(User);

        String[] labels = {"Anime","Computers","Math","Animals","Mythology","Cartoon","Sports","VideoGames"};
        SharedPreferences prefs = 	getSharedPreferences("catGameinfo", MODE_PRIVATE);










        SharedPreferences chartpref = 	getSharedPreferences("chartscore", MODE_PRIVATE);
        int chartanimescore = chartpref.getInt("chartanimescore",0);
        int chartcomputerscore = chartpref.getInt("chartcomputerscore",0);
        int chartmathscore = chartpref.getInt("chartmathscore",0);
        int chartanimalscore = chartpref.getInt("chartanimalscore",0);
        int chartmythcore = chartpref.getInt("chartmythscore",0);
        int chartcartoonscore = chartpref.getInt("chartcartoonscore",0);
        int chartsportscore = chartpref.getInt("chartsportscore",0);
        int chartvideogamescore = chartpref.getInt("chartvideogamescore",0);





        ArrayList<String> xAxisValue = new ArrayList<String>();//X-axis data source
        RadarChart radarChart;//radar

        radarChart = findViewById(R.id.RadarChart);
        radarChart.getLegend().setEnabled(false);

        radarChart.getDescription().setEnabled(false);

        XAxis xAxis = radarChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//Set the label to center
        radarChart.getYAxis().setAxisMaximum(4f);
        radarChart.getYAxis().setAxisMinimum(0f);
        radarChart.getYAxis().setLabelCount(5 , true);


        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        xAxis.setTextColor(Color.WHITE);

        java.util.List<RadarEntry> radarEntries = new ArrayList<>();



        radarEntries.add(new RadarEntry(chartanimescore));
        radarEntries.add(new RadarEntry(chartcomputerscore));
        radarEntries.add(new RadarEntry(chartmathscore));
        radarEntries.add(new RadarEntry(chartanimalscore));
        radarEntries.add(new RadarEntry(chartmythcore));
        radarEntries.add(new RadarEntry(chartcartoonscore));
        radarEntries.add(new RadarEntry(chartsportscore));
        radarEntries.add(new RadarEntry(chartvideogamescore));


        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "");
        // Solid fill area color
        radarDataSet.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        // Whether to fill the area solidly
        radarDataSet.setDrawFilled(true);
        RadarData radarData = new RadarData(radarDataSet);
        radarChart.setData(radarData);




        Button GoTest = findViewById(R.id.takeAptQuiz);
        GoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){



                            Boolean takenAptQuiz = dataSnapshot.child("takenAptQuiz").getValue(Boolean.class);

                            if (takenAptQuiz.equals(true)){



                                Toast.makeText(AptitudeTestHomeActivity.this, "Done Aptitude Quiz!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(AptitudeTestHomeActivity.this, CategoryActivity.class);
                                startActivity(intent);






                            }

                            else{

                                SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
                                editor.putInt("loopCat" ,0);
                                editor.putInt("AptQuestionsAnswered", 0);
                                editor.putInt("RealQuestionsAnswered", 0);

                                for(int i = 0; i < 8; i++){

                                    editor.putInt(String.valueOf(i), 0);


                                }

                                editor.apply();

                                Intent intent = new Intent(AptitudeTestHomeActivity.this, AptitudeTestActivity.class);
                                startActivity(intent);


                            }





                        }
                        myRef.removeEventListener(this);




                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });


            }
        });
















    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
