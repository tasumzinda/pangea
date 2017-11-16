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
 * Created by User on 3/20/2017.
 */
@Table(name = "tx_new")
public class TXNew extends Model {

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

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    @Expose
    @Column(name = "numerator")
    public Long numerator;

    @Expose
    @Column(name = "pregnant")
    public Long pregnant;

    @Expose
    @Column(name = "breastFeeding")
    public Long breastFeeding;

    @Expose
    @Column(name = "confirmedTB")
    public Long confirmedTB;

    /*@Expose
    @Column(name = "pwid")
    public Long pwid;

    @Expose
    @Column(name = "msim")
    public Long msim;

    @Expose
    @Column(name = "transGender")
    public Long transGender;

    @Expose
    @Column(name = "fsw")
    public Long fsw;

    @Expose
    @Column(name = "prisonAndEnclosedSettings")
    public Long prisonAndEnclosedSettings;*/

    @Expose
    @Column(name = "maleLessThanOne")
    public Long maleLessThanOne;

    @Expose
    @Column(name = "femaleLessThanOne")
    public Long femaleLessThanOne;

    @Expose
    @Column(name = "maleOneToFour")
    public Long maleOneToFour;

    @Expose
    @Column(name = "femaleOneToFour")
    public Long femaleOneToFour;

    @Expose
    @Column(name = "maleFiveToNine")
    public Long maleFiveToNine;

    @Expose
    @Column(name = "femaleFiveToNine")
    public Long femaleFiveToNine;

    @Expose
    @Column(name = "maleTenToFourteen")
    public Long maleTenToFourteen;

    @Expose
    @Column(name = "femaleTenToFourteen")
    public Long femaleTenToFourteen;

    @Expose
    @Column(name = "maleFifteenToNineteen")
    public Long maleFifteenToNineteen;

    @Expose
    @Column(name = "femaleFifteenToNineteen")
    public Long femaleFifteenToNineteen;


    @Expose
    @Column(name = "maleTwentyToTwentyFour")
    public Long maleTwentyToTwentyFour;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour")
    public Long femaleTwentyToTwentyFour;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine;

    @Column
    @Expose
    public Long maleFortyToFortyFour;

    @Column
    @Expose
    public Long femaleFortyToFortyFour;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine;

    @Expose
    @Column(name = "maleFiftyPlus")
    public Long maleFiftyPlus;

    @Expose
    @Column(name = "femaleFiftyPlus")
    public Long femaleFiftyPlus;

    public TXNew() {
        super();
    }

    public static TXNew get(Long id) {
        return new Select().from(TXNew.class).where("Id = ?", id).executeSingle();
    }

    public static TXNew getTXNew(Long id) {
        return new Select().from(TXNew.class).where("serverId = ?", id).executeSingle();
    }

    public static List<TXNew> getAll() {
        return new Select()
                .from(TXNew.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(TXNew.class)
                .count();
    }

    public static List<TXNew> getFilesToUpload() {
        return new Select()
                .from(TXNew.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(TXNew.class).execute();
    }

    public static TXNew fromJson(JSONObject jsonObject) {
        TXNew i = new TXNew();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<TXNew> fromJson(JSONArray jsonArray) {
        ArrayList<TXNew> list = new ArrayList<TXNew>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            TXNew business = TXNew.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<TXNew>> LIST =
            new TypeToken<List<TXNew>>() {
            };


    @Override
    public String toString() {
        return name;
    }


    public Long male() {
        return AppUtil.getLong(maleLessThanOne) + AppUtil.getLong(maleOneToFour) +
                AppUtil.getLong(maleFiveToNine) + AppUtil.getLong(maleTenToFourteen) +
                AppUtil.getLong(maleFifteenToNineteen) + AppUtil.getLong(maleTwentyToTwentyFour) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine) + AppUtil.getLong(maleThirtyToThirtyFour) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine) + AppUtil.getLong(maleFortyToFortyFour) +
                AppUtil.getLong(maleFortyFiveToFortyNine) + AppUtil.getLong(maleFiftyPlus);
    }

    public Long female() {
        return AppUtil.getLong(femaleLessThanOne) + AppUtil.getLong(femaleOneToFour) +
                AppUtil.getLong(femaleFiveToNine) + AppUtil.getLong(femaleTenToFourteen) +
                AppUtil.getLong(femaleFifteenToNineteen) + AppUtil.getLong(femaleTwentyToTwentyFour) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine) + AppUtil.getLong(femaleThirtyToThirtyFour) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine) + AppUtil.getLong(femaleFortyToFortyFour) +
                AppUtil.getLong(femaleFortyFiveToFortyNine) + AppUtil.getLong(femaleFiftyPlus);
    }
}
