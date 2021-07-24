package sg.edu.np.madassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText settingUser = findViewById(R.id.settingsusername);
        EditText settingPassword = findViewById(R.id.settingspassword);
        EditText settingConfirm = findViewById(R.id.settingdescription);
        EditText settingEmail = findViewById(R.id.settingemail);


    }
}