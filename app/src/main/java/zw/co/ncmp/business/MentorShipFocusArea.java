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
@Table(name = "focus_area")
public class MentorShipFocusArea extends Model {

    @Expose
    @Column(name = "name", unique = true)
    public String name;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    public static MentorShipFocusArea get(Long id) {
        return new Select().from(MentorShipFocusArea.class).where("Id = ?", id).executeSingle();
    }

    public static MentorShipFocusArea get(String name) {
        return new Select().from(MentorShipFocusArea.class).where("name = ?",name).executeSingle();
    }

    public static MentorShipFocusArea getFocusArea(Long id) {
        return new Select().from(MentorShipFocusArea.class).where("serverId = ?", id).executeSingle();
    }

    public static List<MentorShipFocusArea> getAll() {
        return new Select()
                .from(MentorShipFocusArea.class)
                .orderBy("name ASC")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(MentorShipFocusArea.class).execute();
    }

    @Override
    public String toString() {
        return name;
    }

}
