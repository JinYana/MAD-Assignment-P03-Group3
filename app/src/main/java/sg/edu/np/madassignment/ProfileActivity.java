package sg.edu.np.madassignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(2).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {


                    case R.id.page_1:
                        Intent b = new Intent(ProfileActivity.this, CategoryActivity.class);
                        startActivity(b);


                        break;

                    case R.id.page_2:
                        Intent a = new Intent(ProfileActivity.this,AptitudeTestHomeActivity.class);
                        startActivity(a);
                        break;

                    case R.id.page_3:

                        break;

                }
                return false;
            }
        });

        //getting user info
        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");




        
        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                //Displaying user's username
                String username = snapshot.child("username").getValue(String.class);
                TextView titleUsername = findViewById(R.id.username);
                titleUsername.setText(username);

                //Displaying user's description
                String description = snapshot.child("description").getValue(String.class);
                TextView desc = findViewById(R.id.description);
                desc.setText(description);

                //Displaying user's level
                Integer userlevel = snapshot.child("level").getValue(Integer.class);
                TextView level = findViewById(R.id.level);
                level.setText("Level: " + userlevel);

                //Converting string to uri to set profile picture
                String picture = snapshot.child("profilepicture").getValue(String.class);
                
                ImageView pp = findViewById(R.id.profilepicture);
                Uri uri = Uri.parse(picture);
                pp.setImageURI(uri);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        

        ActivityResultLauncher<Intent> imageActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                           Uri uri = data.getData();
                           int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;

                            ContentResolver cr = getContentResolver();
                          cr.takePersistableUriPermission(uri, flag);
                            ImageView pp = findViewById(R.id.profilepicture);
                            pp.setImageURI(uri);

                            //updating profile pic to firebase
                            
                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
                            DatabaseReference myRef = database.getReference("User").child(username).child("profilepicture");
                            myRef.setValue(uri.toString());


                        }

                    }


                });


        Button button = findViewById(R.id.photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_OPEN_DOCUMENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);

                imageActivityResultLauncher.launch(i);
            }
        });


    }
}



