package sg.edu.np.madassignment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
//import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class ViewAptitudeResultActivity extends AppCompatActivity {
//    RadarChart RadarChart;
//    RadarData radarData;
//    RadarDataSet radarDataSet;
//    ArrayList radarEntries;



    String[] labels = {"Anime","Computers","Math","Animals","Mythology","Cartoon","Sports","VideoGames"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aptitude_result);

        SharedPreferences pref = getSharedPreferences("Loggedin",MODE_PRIVATE);
        String User = pref.getString("User","1");

        SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
        editor.remove("loopCat");
        editor.apply();
        SharedPreferences prefs = 	getSharedPreferences("catGameinfo", MODE_PRIVATE);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(User);

        myRef.child("takenAptQuiz").setValue(true);

        myRef.child("aptAnimeScore").setValue(prefs.getInt("0",0));
        myRef.child("aptComputerScore").setValue(prefs.getInt("1",0));
        myRef.child("aptMathScore").setValue(prefs.getInt("2",0));
        myRef.child("aptAnimalScore").setValue(prefs.getInt("3",0));
        myRef.child("aptMythScore").setValue(prefs.getInt("4",0));
        myRef.child("aptCartoonScore").setValue(prefs.getInt("5",0));
        myRef.child("aptSportScore").setValue(prefs.getInt("6",0));
        myRef.child("aptVideoGameScore").setValue(prefs.getInt("7",0));

        myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.exists()) {
                                                int aptanimescore = dataSnapshot.child("aptAnimeScore").getValue(int.class);
                                                int aptcomputerscore = dataSnapshot.child("aptComputerScore").getValue(int.class);
                                                int aptmathscore = dataSnapshot.child("aptMathScore").getValue(int.class);
                                                int aptanimalscore = dataSnapshot.child("aptAnimalScore").getValue(int.class);
                                                int aptmythscore = dataSnapshot.child("aptMythScore").getValue(int.class);
                                                int aptcartoonscore = dataSnapshot.child("aptCartoonScore").getValue(int.class);
                                                int aptsportscore = dataSnapshot.child("aptSportScore").getValue(int.class);
                                                int aptvideogamescore = dataSnapshot.child("aptVideoGameScore").getValue(int.class);






                                                SharedPreferences.Editor charteditor = 	getSharedPreferences("chartscore", MODE_PRIVATE).edit();
                                                charteditor.putInt("chartanime",aptanimescore);
                                                charteditor.putInt("chartcomputerscore",aptcomputerscore);
                                                charteditor.putInt("chartmathscore",aptmathscore);
                                                charteditor.putInt("chartanimalscore",aptanimalscore);
                                                charteditor.putInt("chartmythscore",aptmythscore);
                                                charteditor.putInt("chartcartoonscore",aptcartoonscore);
                                                charteditor.putInt("sportscore",aptsportscore);
                                                charteditor.putInt("chartvideogamescore",aptvideogamescore);
                                                charteditor.apply();


                                            }




                                        }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });


        ///////////


        ArrayList<String> xAxisValue = new ArrayList<String>();//X-axis data source
        RadarChart radarChart;//radar

        radarChart = findViewById(R.id.RadarChart);

        radarChart.getDescription().setEnabled(false);

        XAxis xAxis = radarChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(10);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//Set the label to center
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setTextColor(Color.WHITE);

        java.util.List<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(prefs.getInt("0",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("1",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("2",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("3",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("4",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("5",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("6",0)));
        radarEntries.add(new RadarEntry(prefs.getInt("7",0)));









        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "data one");
        // Solid fill area color
        radarDataSet.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        // Whether to fill the area solidly
        radarDataSet.setDrawFilled(true);
        RadarData radarData = new RadarData(radarDataSet);
        radarChart.setData(radarData);


        Button homebutton = findViewById(R.id.button5);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAptitudeResultActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });




    }

}