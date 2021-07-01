package sg.edu.np.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE = "users.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE user (name TEXT, description TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT, followed INTEGER)";
        db.execSQL(create);

        for(int i=0; i<20; i++)
        {
            ContentValues c = new ContentValues();
            c.put("name", "Name" + new Random().nextInt());
            c.put("description","Description " + new Random().nextInt());
            db.insert("user", null, c);
        }
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
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = cursor.getInt(2);
            u.followed = cursor.getInt(3)==0?false:true;

            list.add(u);
        }
        cursor.close();
        db.close();
        return list;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followed", u.followed);
        int count = db.update("user", values, "id = ?", new String[]{ "" + u.id });

        db.close();
    }
}
