package ddd.magdy.fashione_commerace.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void addProductItem(ProductItem productItem);

    @Delete
    void deleteProductItem(ProductItem productItem);

    @Query("SELECT *FROM ProductItem")
    LiveData<List<ProductItem>> getAllProductItem();
}
