package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BASEDEDATOS = "Registros.db";
    public static final String TABLA_REGISTROS = "tabla_registros";

    public DbHelper(@Nullable Context context) {
        super(context, NOMBRE_BASEDEDATOS, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLA_REGISTROS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, correo TEXT, clave TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
