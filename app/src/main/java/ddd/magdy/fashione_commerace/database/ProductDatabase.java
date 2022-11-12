package ddd.magdy.fashione_commerace.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ddd.magdy.fashione_commerace.utils.Constant;

@Database(entities = {ProductItem.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    public static ProductDatabase db = null;

    public static ProductDatabase getInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, ProductDatabase.class, Constant.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;

    }
}
