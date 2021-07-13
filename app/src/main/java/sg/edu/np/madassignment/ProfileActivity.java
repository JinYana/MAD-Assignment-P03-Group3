package sg.edu.np.madassignment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this, null, null, 1);
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //getting user info
        SharedPreferences logprefs = getSharedPreferences("Loggedin", MODE_PRIVATE);
        String username = logprefs.getString("User", "");

        User user = dbHandler.findUser(username);

        //Displaying user's username
        TextView titleUsername = findViewById(R.id.username);
        titleUsername.setText(user.username);

        //Displaying user's description
        TextView desc = findViewById(R.id.description);
        desc.setText(user.description);

        //Displaying user's level
        TextView level = findViewById(R.id.level);
        level.setText(String.valueOf(user.level));

        //Converting string to uri to set profile picture
        ImageView pp = findViewById(R.id.profilepicture);
        Uri uri = Uri.parse(user.profilepicture);
        pp.setImageURI(uri);

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
                            pp.setImageURI(uri);



                            user.setProfilepicture(uri.toString());

                            dbHandler.updateUser(user);
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



