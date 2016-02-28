package com.kankana.kankana;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * This class helps open, create, and upgrade the database file.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public final Context fContext;
    final static int DB_VERSION = 1;
    final static String DB_NAME = "kankanagurukuladb.s3db";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alldefinitions ("
                + "english TEXT PRIMARY KEY,"
                + "kannada TEXT,"
                + "kannadaenglish TEXT,"
                + "category TEXT"
                + ");");

        //Add default records to animals
        ContentValues _Values = new ContentValues();
        //Get xml resource file
        Resources res = fContext.getResources();

        //Open xml file
        XmlResourceParser _xml = res.getXml(R.xml.kankana_gurukula);
        try
        {
            //Check for end of document
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) &&(_xml.getName().equals("record"))){
                    //Record tag found, now get values and insert record
                    String english = _xml.getAttributeValue(null, definitions.ENGLISH);
                    String kannada = _xml.getAttributeValue(null, definitions.KANNADA);
                    String kannadaenglish = _xml.getAttributeValue(null, definitions.KANNADAENGLISH);
                    String category = _xml.getAttributeValue(null, definitions.CATEGORY);
                    _Values.put(definitions.ENGLISH, english);
                    _Values.put(definitions.KANNADA, kannada);
                    _Values.put(definitions.KANNADAENGLISH, kannadaenglish);
                    _Values.put(definitions.CATEGORY, category);
                    db.insert("alldefinitions", null, _Values);
                }
                eventType = _xml.next();
            }
        }
        //Catch errors
        catch (XmlPullParserException e)
        {

        }
        catch (IOException e)
        {

        }
        finally
        {
            //Close the xml file
            _xml.close();
        }
    }

    /* Update database to latest version */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Crude update, make sure to implement a correct one when needed.
        db.execSQL("DROP TABLE IF EXISTS alldefinitions");
        onCreate(db);
    }
}