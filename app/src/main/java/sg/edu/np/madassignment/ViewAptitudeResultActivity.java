package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

import java.util.ArrayList;

public class ViewAptitudeResultActivity extends AppCompatActivity {
    RadarChart RadarChart;
    RadarData radarData;
    RadarDataSet radarDataSet;
    ArrayList radarEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aptitude_result);

//        TextView resultAnime = (TextView) findViewById(R.id.resultAnime);
//        TextView resultComputer = (TextView) findViewById(R.id.resultComputer);
//        TextView resultMath = (TextView) findViewById(R.id.resultMath);
//        TextView resultAnimal = (TextView) findViewById(R.id.resultAnimal);
//        TextView resultMyth = (TextView) findViewById(R.id.resultMyth);
//        TextView resultcartoon = (TextView) findViewById(R.id.resultCartoon);
//        TextView resultSports = (TextView) findViewById(R.id.resultSports);
//        TextView resultVideoGame = (TextView) findViewById(R.id.resultVideoGame);
//        User u = new User();

//         ArrayList<Integer> ScoreList = getIntent().getIntegerArrayListExtra("ScoreList");
//
//
//           resultAnime.setText(ScoreList.get(0));
//        resultComputer.setText(ScoreList.get(1));
//        resultMath.setText(ScoreList.get(2));
//       resultAnimal.setText(ScoreList.get(3));
//       resultMyth.setText(ScoreList.get(4));
//        resultcartoon.setText(ScoreList.get(5));
//        resultSports.setText(ScoreList.get(6));
//       resultVideoGame.setText(ScoreList.get(7));

        RadarChart = findViewById(R.id.RadarChart);
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(0, 0.21f));
        radarEntries.add(new RadarEntry(1, 0.12f));
        radarEntries.add(new RadarEntry(2, 0.20f));
        radarEntries.add(new RadarEntry(2, 0.52f));
        radarEntries.add(new RadarEntry(3, 0.29f));
        radarEntries.add(new RadarEntry(4, 0.62f));
        radarDataSet = new RadarDataSet(radarEntries, "");
        radarData = new RadarData(radarDataSet);
        RadarChart.setData(radarData);
        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(18f);


    }

}