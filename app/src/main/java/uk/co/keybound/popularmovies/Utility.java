package uk.co.keybound.popularmovies;

/**
 * Created by Admin on 22/09/2016.
 */
public class Utility {


    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
}
