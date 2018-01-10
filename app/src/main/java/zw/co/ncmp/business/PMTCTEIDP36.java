package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @uthor Tasu Muzinda
 */
@Table(name = "pmtcteid_p36")
public class PMTCTEIDP36 extends Model{

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    @Column(name = "date_created")
    public Date dateCreated;

    @SerializedName("datec")
    @Expose
    public String serverCreatedDate;

    @Expose
    @Column(name = "name")
    public String name;

    @Expose
    @Column(name = "period_id")
    public Period period;

    @Column
    @Expose
    public Long lessThanTwo;

    @Column
    @Expose
    public Long threeToTwelve;

    @Expose
    @Column
    public Long thirteenToTwentyFour;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public PMTCTEIDP36() {
        super();
    }

    public static PMTCTEIDP36 get(Long id) {
        return new Select().from(PMTCTEIDP36.class).where("Id = ?", id).executeSingle();
    }

    public static List<PMTCTEIDP36> getAll() {
        return new Select()
                .from(PMTCTEIDP36.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(PMTCTEIDP36.class)
                .count();
    }

    public static List<PMTCTEIDP36> getFilesToUpload() {
        return new Select()
                .from(PMTCTEIDP36.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(PMTCTEIDP36.class).execute();
    }

    public static PMTCTEIDP36 fromJson(JSONObject jsonObject) {
        PMTCTEIDP36 i = new PMTCTEIDP36();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<PMTCTEIDP36> fromJson(JSONArray jsonArray) {
        ArrayList<PMTCTEIDP36> list = new ArrayList<>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PMTCTEIDP36 business = PMTCTEIDP36.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getTotal(){
        return AppUtil.getLong(lessThanTwo) + AppUtil.getLong(threeToTwelve) + AppUtil.getLong(thirteenToTwentyFour);
    }
}
