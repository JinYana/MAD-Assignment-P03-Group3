package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    String token = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        Log.v("Debug", "create");
        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                //Load User's aptitude score
                int aptanimescore = dataSnapshot.child("aptAnimeScore").getValue(int.class);
                int aptcomputerscore = dataSnapshot.child("aptComputerScore").getValue(int.class);
                int aptmathscore = dataSnapshot.child("aptMathScore").getValue(int.class);
                int aptanimalscore = dataSnapshot.child("aptAnimalScore").getValue(int.class);
                int aptmythscore = dataSnapshot.child("aptMythScore").getValue(int.class);
                int aptcartoonscore = dataSnapshot.child("aptCartoonScore").getValue(int.class);
                int aptsportscore = dataSnapshot.child("aptSportScore").getValue(int.class);
                int aptvideogamescore = dataSnapshot.child("aptVideoGameScore").getValue(int.class);
                SharedPreferences.Editor charteditor = 	getSharedPreferences("chartscore", MODE_PRIVATE).edit();
                charteditor.putInt("chartanimescore",aptanimescore);
                charteditor.putInt("chartcomputerscore",aptcomputerscore);
                charteditor.putInt("chartmathscore",aptmathscore);
                charteditor.putInt("chartanimalscore",aptanimalscore);
                charteditor.putInt("chartmythscore",aptmythscore);
                charteditor.putInt("chartcartoonscore",aptcartoonscore);
                charteditor.putInt("chartsportscore",aptsportscore);
                charteditor.putInt("chartvideogamescore",aptvideogamescore);
                charteditor.apply();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //Setting up bottom nav bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().findItem(R.id.page_1).setChecked(true);
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
                        Intent b = new Intent(CategoryActivity.this, LeaderBoardActivity.class);
                        startActivity(b);
                        break;

                    case R.id.page_4:
                        Intent c = new Intent(CategoryActivity.this, ProfileActivity.class);
                        startActivity(c);
                        break;

                    case R.id.page_5:
                        Intent d = new Intent(CategoryActivity.this, LoginActivity.class);
                        startActivity(d);
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

        ArrayList<Integer> imageid = new ArrayList<Integer>();

        imageid.add(R.drawable.game);
        imageid.add(R.drawable.calculator);
        imageid.add(R.drawable.desktop);
        imageid.add(R.drawable.poseidon);
        imageid.add(R.drawable.gundam);
        imageid.add(R.drawable.koala);
        imageid.add(R.drawable.leaderborder);
        imageid.add(R.drawable.sports);

        //Games and Math
        Category category1 = new Category();
        category1.setCat1(" Games ");
        category1.setCat2("Math   ");
        category1.setCat1img(R.drawable.game);
        category1.setCat2img(R.drawable.calculator);
        category1.setCat1no("15");
        category1.setCat2no("19");

        //Computers and Mythology
        Category category2 = new Category();
        category2.setCat1("Computer        ");
        category2.setCat2("Mythology");
        category2.setCat1img(R.drawable.desktop);
        category2.setCat2img(R.drawable.poseidon);
        category2.setCat1no("18");
        category2.setCat2no("20");

        //Anime and Animals
        Category category3 = new Category();
        category3.setCat1("  Anime");
        category3.setCat2("Animals");
        category3.setCat1img(R.drawable.gundam);
        category3.setCat2img(R.drawable.koala);
        category3.setCat1no("31");
        category3.setCat2no("27");

        //Cartoons and Sports
        Category category4 = new Category();
        category4.setCat1("Cartoons");
        category4.setCat2("Sports   ");
        category4.setCat1img(R.drawable.leonardo);
        category4.setCat2img(R.drawable.sports);
        category4.setCat1no("32");
        category4.setCat2no("21");

        ArrayList<Category> catlist = new ArrayList<>();
        catlist.add(category1);
        catlist.add(category2);
        catlist.add(category3);
        catlist.add(category4);



        RecyclerView recyclerView = findViewById(R.id.catrec);
        CategoryAdapter mAdapter = new CategoryAdapter(catlist);




        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);







































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