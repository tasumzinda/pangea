package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tasu
 */
@Table(name = "province")
public class Province extends Model{

    @SerializedName("id")
    @Expose
    @Column
    public Long serverId;

    @Expose
    @Column
    public String name;

    public Province(){
        super();
    }

    public static Province findByServerId(Long id){
        return new Select()
                .from(Province.class)
                .where("serverId = ?", id)
                .executeSingle();
    }

    public static Province findById(Long id){
        return new Select()
                .from(Province.class)
                .where("Id = ?", id)
                .executeSingle();
    }

    public static List<Province> getAll(){
        return new Select()
                .from(Province.class)
                .execute();
    }

    @Override
    public String toString() {
        return name;
    }
}
