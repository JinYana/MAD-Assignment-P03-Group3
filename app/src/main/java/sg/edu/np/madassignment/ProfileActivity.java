package sg.edu.np.madassignment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    public AlertDialog.Builder dialogBuilder;
    public AlertDialog dialog;


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
                        Intent a = new Intent(ProfileActivity.this, AptitudeTestHomeActivity.class);
                        startActivity(a);
                        break;

                    case R.id.page_3:

                        break;

                }
                return false;
            }
        });

        Context context = this;

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");
        //getting user info from firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(username);
        DatabaseReference newRef = database.getReference("User");

        newRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                GenericTypeIndicator<ArrayList<String>> genericTypeIndicator = new GenericTypeIndicator<ArrayList<String>>() {
                };
                GenericTypeIndicator<User> genericUserIndicator = new GenericTypeIndicator<User>() {
                };
                ArrayList<String> map = snapshot.child(username).child("friendslist").getValue(genericTypeIndicator);
                if (map != null) {
                    ArrayList<User> friendsList = new ArrayList<>();
                    for (int i = 1; i < map.size(); i++) {
                        User user = snapshot.child(map.get(i)).getValue(User.class);


                        friendsList.add(user);
                    }

                    RecyclerView recyclerView = findViewById(R.id.friendslist);
                    FriendAdapter mAdapter = new FriendAdapter(friendsList);

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);

                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                } else {

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

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


                TextView frireqcount = findViewById(R.id.frireqcount);
                frireqcount.setText(String.valueOf(snapshot.child("friendreq").getChildrenCount()));

                if (picture.matches("-")) {
                    ImageView pp = findViewById(R.id.profilepicture);
                    pp.setImageResource(R.drawable.user);
                } else {

                    Intent i = new Intent();

                    int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                    StorageReference storage = FirebaseStorage.getInstance("gs://mad-project-2-eeea1.appspot.com/").getReference();
                    StorageReference pathReference = storage.child(username + ".jpg");
                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ImageView pp = findViewById(R.id.profilepicture);
                            Picasso.with(context).load(uri).into(pp);
                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

                            ContentResolver cr = getApplicationContext().getContentResolver();
                            cr.takePersistableUriPermission(uri, flag);
                            ImageView pp = findViewById(R.id.profilepicture);
                            pp.setImageURI(uri);
                            //updating profile pic to firebase
                            Uploadimagetofirebase(uri);


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

        Button editDesc = findViewById(R.id.editdesc);
        editDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createdescriptiondialog();
            }
        });



        ImageButton addfriends = findViewById(R.id.addfriends);
        addfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AddFriendsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton frireq = findViewById(R.id.friendreq);
        frireq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, NewFriendsActivity.class);
                startActivity(intent);
            }
        });

        Button statsButton = findViewById(R.id.stats);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });

        Button achmtButton = findViewById(R.id.achievement);
        achmtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AchievementActivity.class);
                startActivity(intent);
            }
        });



    }
    public void createdescriptiondialog(){
        dialogBuilder =  new AlertDialog.Builder(this);
        final View popupdescriptionView = getLayoutInflater().inflate(R.layout.popup,null);

        EditText newdescription = popupdescriptionView.findViewById(R.id.popupdescription);

        Button saveDesc = popupdescriptionView.findViewById(R.id.savedescription);
        Button goBack = popupdescriptionView.findViewById(R.id.back);

        dialogBuilder.setView(popupdescriptionView);
        dialog = dialogBuilder.create();
        dialog.show();

        SharedPreferences pref = getSharedPreferences("Loggedin",MODE_PRIVATE);
        String User = pref.getString("User","1");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User").child(User);

        saveDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView profdesc =  findViewById(R.id.description);
                profdesc.setText(newdescription.getText());


                myRef.child("description").setValue(newdescription.getText().toString());

                dialog.dismiss();



            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });











    }

    private void Uploadimagetofirebase(Uri uri){

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        StorageReference storage = FirebaseStorage.getInstance("gs://mad-project-2-eeea1.appspot.com/").getReference();

        StorageReference fileref = storage.child(username + ".jpg");



        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });





    }
}









