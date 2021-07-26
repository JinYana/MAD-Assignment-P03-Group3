package sg.edu.np.madassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProgressActivity extends AppCompatActivity {
    private final static String TAG = "Progress Activity";

    Button backBtn;
    TextView animalSc, animeSc, cartoonSc, computerSc, mathSc, mythSc, sportSc, videoGameSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);
        DatabaseReference newRef = database.getReference("User");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    // gathering user's questions answered and question answered correctly from firebase
                    int Animalans = snapshot.child("gameAnimalanswered").getValue(Integer.class);
                    int Animalcor = snapshot.child("gameAnimalcorrect").getValue(Integer.class);
                    int Animeans = snapshot.child("gameAnimeanswered").getValue(Integer.class);
                    int Animecor = snapshot.child("gameAnimecorrect").getValue(Integer.class);
                    int Cartoonans = snapshot.child("gameCartoonanswered").getValue(Integer.class);
                    int Cartooncor = snapshot.child("gameCartooncorrect").getValue(Integer.class);
                    int Computerans = snapshot.child("gameComputeranswered").getValue(Integer.class);
                    int Computercor = snapshot.child("gameComputercorrect").getValue(Integer.class);
                    int Mathans = snapshot.child("gameMathanswered").getValue(Integer.class);
                    int Mathcor = snapshot.child("gameMathcorrect").getValue(Integer.class);
                    int Mythans = snapshot.child("gameMythanswered").getValue(Integer.class);
                    int Mythcor = snapshot.child("gameMythcorrect").getValue(Integer.class);
                    int Sportans = snapshot.child("gameSportanswered").getValue(Integer.class);
                    int Sportcor = snapshot.child("gameSportcorrect").getValue(Integer.class);
                    int VideoGameans = snapshot.child("gameVideoGameanswered").getValue(Integer.class);
                    int VideoGamecor = snapshot.child("gameVideoGamecorrect").getValue(Integer.class);


                    //Adding onclick listeners to all the category buttons
                    ImageButton game = findViewById(R.id.gameButton);
                    ImageButton computer = findViewById(R.id.computer);
                    ImageButton math = findViewById(R.id.math);
                    ImageButton myth = findViewById(R.id.mythology);
                    ImageButton anime = findViewById(R.id.anime);
                    ImageButton animal = findViewById(R.id.animal);
                    ImageButton cartoon = findViewById(R.id.cartoon);
                    ImageButton sport = findViewById(R.id.sport);

                    game.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", VideoGameans);
                            intent.putExtra("correct", VideoGamecor);
                            intent.putExtra("cat", "Video Games");

                            startActivity(intent);
                        }
                    });


                    computer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Computerans);
                            intent.putExtra("correct", Computercor);
                            intent.putExtra("cat", "Computers");

                            startActivity(intent);
                        }
                    });

                    math.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Mathans);
                            intent.putExtra("correct", Mathcor);
                            intent.putExtra("cat", "Math");

                            startActivity(intent);
                        }
                    });

                    myth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Mythans);
                            intent.putExtra("correct", Mythcor);
                            intent.putExtra("cat", "Mythology");

                            startActivity(intent);
                        }
                    });


                    anime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Animeans);
                            intent.putExtra("correct", Animecor);
                            intent.putExtra("cat", "Anime");

                            startActivity(intent);
                        }
                    });


                    animal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Animalans);
                            intent.putExtra("correct", Animalcor);
                            intent.putExtra("cat", "Animals");

                            startActivity(intent);
                        }
                    });


                    cartoon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Cartoonans);
                            intent.putExtra("correct", Cartooncor);
                            intent.putExtra("cat", "Cartoons");

                            startActivity(intent);
                        }
                    });


                    sport.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor gameeditor = getSharedPreferences("Gameinfo", MODE_PRIVATE).edit();
                            gameeditor.putInt("QuestionsAnswered", 0);
                            gameeditor.putInt("Score", 0);
                            gameeditor.apply();

                            Intent intent = new Intent(ProgressActivity.this, CategoryStatActivity.class);
                            intent.putExtra("attempts", Sportans);
                            intent.putExtra("correct", Sportcor);
                            intent.putExtra("cat", "Sports");

                            startActivity(intent);
                        }


                    });



                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }
}