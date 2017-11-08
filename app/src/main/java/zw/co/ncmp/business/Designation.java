package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tdhlakama on 2/6/2016.
 */
@Table(name = "designation")
public class Designation extends Model {

    @Expose
    @Column(name = "name", unique = true)
    public String name;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    public static Designation get(Long id) {
        return new Select().from(Designation.class).where("Id = ?", id).executeSingle();
    }

    public static Designation getDesignation(Long id) {
        return new Select().from(Designation.class).where("serverId = ?", id).executeSingle();
    }

    public static List<Designation> getAll() {
        return new Select()
                .from(Designation.class)
                .orderBy("name ASC")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(Designation.class).execute();
    }

    @Override
    public String toString() {
        return name;
    }


}
