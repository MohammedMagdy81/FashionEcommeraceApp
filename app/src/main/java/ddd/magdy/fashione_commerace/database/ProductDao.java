package ddd.magdy.fashione_commerace.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ddd.magdy.fashione_commerace.model.ProductResponseItem;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProductItem(ProductResponseItem productItem);

    @Delete
    void deleteProductItem(ProductResponseItem productItem);

    @Query("SELECT *FROM ProductResponseItem")
    LiveData<List<ProductResponseItem>> getAllProductItem();
}
