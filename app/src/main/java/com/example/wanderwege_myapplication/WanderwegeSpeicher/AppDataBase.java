package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Wanderweg.class, Sightseeing.class, Rating.class, Comment.class, MarkOfSightseeing.class, MarkOfWanderweg.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract WanderwegDao wanderwegDao();
    public abstract SightseeingDao sightseeingDao();
    public abstract RatingDao ratingDao();
    public abstract CommentDao commentDao();
    public abstract MarkOfSightseeingDao markOfSightseeingDao();
    public abstract MarkOfWanderwegDao markOfWanderwegDao();

    private static  AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "app_database")
                    .addCallback(roomCallback) // Add the callback
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertInitialDataAsyncTask(instance).execute();
        }
    };

    private static class InsertInitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private WanderwegDao wanderwegDao;
        private SightseeingDao sightseeingDao;
        private RatingDao ratingDao;
        private CommentDao commentDao;
        private MarkOfSightseeingDao markOfSightseeingDao;
        private MarkOfWanderwegDao markOfWanderwegDao;

        private InsertInitialDataAsyncTask(AppDataBase database) {
            wanderwegDao = database.wanderwegDao();
            sightseeingDao = database.sightseeingDao();
            ratingDao = database.ratingDao();
            commentDao = database.commentDao();
            markOfSightseeingDao = database.markOfSightseeingDao();
            markOfWanderwegDao = database.markOfWanderwegDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}