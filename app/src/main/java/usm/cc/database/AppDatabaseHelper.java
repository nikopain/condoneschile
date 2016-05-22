package usm.cc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by niko on 10/05/2016.
 */
public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "siga_app.db";
    private static final int DB_VERSION = 2;
    private static final String CREATE_TABLE_USER ="CREATE TABLE " +
            "usuario (nombre TEXT, apellido TEXT,email TEXT, telefono TEXT, direccion TEXT, ciudad TEXT, codigo_postal TEXT)";

    public static final String TABLE_USER= "usuario";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_APELLIDO = "descripcion";
    public static final String COLUMN_EMAIL = "profesor";
    public static final String COLUMN_TELEFONO = "codigo";;
    public static final String COLUMN_DIRECCION = "descripcion";
    public static final String COLUMN_CIUDAD = "profesor";
    public static final String COLUMN_CODIGO_POSTAL = "codigo";

    public AppDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_TABLE_USER);
        register(database);
    }

    private void register(SQLiteDatabase database) {

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, "Inteligencia Artificial");
        cv.put(COLUMN_APELLIDO, "computador vs humanos");
        cv.put(COLUMN_EMAIL, "Profesora Elizabeth");
        cv.put(COLUMN_TELEFONO, "Inteligencia Artificial");
        cv.put(COLUMN_DIRECCION, "computador vs humanos");
        cv.put(COLUMN_CIUDAD, "Profesora Elizabeth");
        cv.put(COLUMN_CODIGO_POSTAL, "INF-001");

        database.insert(TABLE_USER, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldDBVersion, int newDBVersion) {

    }
}
