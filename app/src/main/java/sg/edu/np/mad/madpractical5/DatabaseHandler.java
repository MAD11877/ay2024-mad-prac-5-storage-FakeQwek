

package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import java.util.Random;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FOLLOWED = "followed";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void generateRandomUsers(SQLiteDatabase db) {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Alex");
        names.add("Joseph");
        names.add("Leonardo");
        names.add("Maximillus");
        names.add("Caesar");
        names.add("Ricardo");
        names.add("Jackson");
        names.add("Markson");
        names.add("Marcus");
        names.add("Millie");
        names.add("Ruby");
        names.add("Lily");
        names.add("Rachel");
        names.add("Chloe");
        names.add("Ollivander");
        names.add("Olivia");
        names.add("Taylor");
        names.add("Marlow");
        names.add("Patricia");
        names.add("Benedict");
        names.add("Eggsy");
        names.add("Larryson");
        names.add("Lawrence");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        for (int i = 0; i <= 20; i++) {
            int randNo = rand.nextInt(23);
            String username = names.get(randNo);
            randNo = rand.nextInt(53);
            Boolean followed = null;
            String description = "";
            for (int x = 0; x <= 20; x++) {
                randNo = rand.nextInt(23);
                description += characters.charAt(randNo + x);
            }
            randNo = rand.nextInt(1);
            if (randNo == 1) {
                followed = true;
            }
            else {
                followed = false;
            }
            User user = new User(username, description, i, followed);

            addUser(user, db);

        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESCRIPTION+ " TEXT,"
                + COLUMN_FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        generateRandomUsers(db);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_ID, user.getID());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        db.insert(TABLE_USERS, null, values);

    }

    public void updateUser(int id, boolean followed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, followed);
        String clause = "id=?";
        String[] args = {String.valueOf(id)};
        db.update(TABLE_USERS, values, clause, args);

    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt((int)cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString((int)cursor.getColumnIndex(COLUMN_USERNAME));
            String description = cursor.getString((int)cursor.getColumnIndex(COLUMN_DESCRIPTION));
            String followed = cursor.getString((int)cursor.getColumnIndex(COLUMN_FOLLOWED));
            boolean isFollowed = true;
            if (followed == "true") {
                isFollowed = true;
            }
            else {
                isFollowed = false;
            }
            User user = new User(name, description, id, isFollowed);
            userList.add(user);
        }
        cursor.close();
        return userList;
    }



}
