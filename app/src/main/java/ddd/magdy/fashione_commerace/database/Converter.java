package ddd.magdy.fashione_commerace.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.model.Rating;

public class Converter {

    @TypeConverter
    public String fromRatingToGson(Rating item){
        return new Gson().toJson(item);
    }

    @TypeConverter
    public Rating fromGsonToRating(String stringRating){
        return new Gson().fromJson(stringRating,Rating.class);
    }
}
