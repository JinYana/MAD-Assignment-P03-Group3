package sg.edu.np.madassignment;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Bundle;
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


        SharedPreferences.Editor editor = 	getSharedPreferences("catGameinfo", MODE_PRIVATE).edit();
        editor.remove("loopCat");
        editor.apply();
        SharedPreferences prefs = 	getSharedPreferences("catGameinfo", MODE_PRIVATE);


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


    }

}