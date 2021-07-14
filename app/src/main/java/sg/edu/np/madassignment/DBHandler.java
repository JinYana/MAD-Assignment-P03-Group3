package sg.edu.np.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE = "users.db";



    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Description TEXT, Password TEXT, Email TEXT, Level INTEGER, Profilepic String)";
        db.execSQL(create);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public ArrayList<User> getUsers()
    {
        ArrayList<User> list = new ArrayList<User> ();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        while(cursor.moveToNext())
        {
            User u = new User();
            u.id = cursor.getInt(0);
            u.username = cursor.getString(1);
            u.description = cursor.getString(2);
            u.password = cursor.getString(3);
            u.email = cursor.getString(4);
            u.level = cursor.getInt(5);
            u.profilepicture = cursor.getString(6);



            list.add(u);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addUser(User user)
    {
        ContentValues  values = new ContentValues();
        values.put("Username", user.getUsername());
        values.put("Password", user.getPassword());
        values.put("Email", user.getEmail());
        values.put("Description", user.getDescription());
        values.put("Level", user.getLevel());
        values.put("Profilepic", user.getProfilepicture());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("user", null, values);
        db.close();
    }

    public User findUser(String username) {
        String query = "SELECT * FROM user" + " WHERE Username" +
                "=\"" + username + "\"";



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();

        if (cursor.moveToFirst())
        {

            queryData.setId(cursor.getInt(0));
            queryData.setUsername(cursor.getString(1));
            queryData.setDescription(cursor.getString(2));
            queryData.setPassword(cursor.getString(3));
            queryData.setEmail(cursor.getString(4));
            queryData.setLevel(cursor.getInt(5));
            queryData.setProfilepicture(cursor.getString(6));
            cursor.close();

        }
        else
        {
            queryData = null;
        }
        db.close();
        return queryData;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Level", u.getLevel());
        values.put("Description", u.getDescription());
        values.put("Profilepic", u.getProfilepicture());

        int count = db.update("user", values, "id = ?", new String[]{ "" + u.id });

        db.close();
    }
}
