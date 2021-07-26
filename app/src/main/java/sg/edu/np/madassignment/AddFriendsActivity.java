package sg.edu.np.madassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddFriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        LottieAnimationView friend = findViewById(R.id.friendanimation);
        float trophyscale = (float) 0.5;
        friend.setScale(trophyscale);

        EditText friendusername = findViewById(R.id.friendusername);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://mad-project-2-eeea1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("User");

        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        Button addfriend = findViewById(R.id.addfriendbutton);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                addfriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String targeteduser = String.valueOf(friendusername.getText());


                        //If targeted username is same as current user
                        if(!targeteduser.equals(username)){

                            //If targeted user is  not already in current user's friendslist
                            if(!snapshot.child(username).child("friendslist").child(targeteduser).exists()){

                                //If user has not already sent targeted user a friend request
                                if(!snapshot.child(targeteduser).child("friendreq").child(username).exists()){
                                    //If targeted user has already sent a friend request
                                    if(!snapshot.child(username).child("friendreq").child(targeteduser).exists()){

                                        //If targeted user does not exsist
                                        if(snapshot.child(targeteduser).exists()){
                                            myRef.child(targeteduser).child("friendreq").child(username).setValue(username);
                                            Intent intent = new Intent(AddFriendsActivity.this, ProfileActivity.class);
                                            Toast.makeText(AddFriendsActivity.this, "Friend request sent", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(AddFriendsActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(AddFriendsActivity.this, "User has already sent a request, check your request list", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else {
                                    Toast.makeText(AddFriendsActivity.this, "Already sent User friend request", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(AddFriendsActivity.this, "User is already your friend", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(AddFriendsActivity.this, "Cannot add yourself as friend", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}