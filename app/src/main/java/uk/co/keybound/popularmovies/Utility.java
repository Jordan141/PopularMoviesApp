package uk.co.keybound.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import uk.co.keybound.popularmovies.data.MovieContract;

/**
 * Created by Admin on 22/09/2016.
 */
public class Utility {


    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
    public static int isFavorited(Context context, int id) {
        Cursor cursor = context.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                null,   // projection
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", // selection
                new String[] { Integer.toString(id) },   // selectionArgs
                null    // sort order
        );
        int numRows = 0;
        try {
            numRows = cursor.getCount();
            cursor.close();
        }catch (NullPointerException e){
            Log.w(Utility.class.getSimpleName(),e.getMessage(),e);
            e.printStackTrace();
        }
        return numRows;
    }
}
