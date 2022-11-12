package ddd.magdy.fashione_commerace.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductItem {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String image;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String desc;
    @ColumnInfo
    private int count;
    @ColumnInfo
    private double price;

    public ProductItem(int id, String image, String title, String desc, int count, double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }
}

