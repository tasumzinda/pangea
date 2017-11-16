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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 4/8/2016.
 */
@Table(name = "art_form")
public class ARTForm  extends Model {

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

    @Expose
    @Column(name = "numerator")
    public Long numerator;

    @Expose
    @Column(name = "denominator")
    public Long denominator;

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

    @Expose
    @Column(name = "maleLessThanOne1")
    public Long maleLessThanOne1;

    @Expose
    @Column(name = "femaleLessThanOne1")
    public Long femaleLessThanOne1;

    @Expose
    @Column(name = "maleOneToFour1")
    public Long maleOneToFour1;

    @Expose
    @Column(name = "femaleOneToFour1")
    public Long femaleOneToFour1;

    @Expose
    @Column(name = "maleFiveToNine1")
    public Long maleFiveToNine1;

    @Expose
    @Column(name = "femaleFiveToNine1")
    public Long femaleFiveToNine1;

    @Expose
    @Column(name = "maleTenToFourteen1")
    public Long maleTenToFourteen1;

    @Expose
    @Column(name = "femaleTenToFourteen1")
    public Long femaleTenToFourteen1;

    @Expose
    @Column(name = "maleFifteenToNineteen1")
    public Long maleFifteenToNineteen1;

    @Expose
    @Column(name = "femaleFifteenToNineteen1")
    public Long femaleFifteenToNineteen1;


    @Expose
    @Column(name = "maleTwentyToTwentyFour1")
    public Long maleTwentyToTwentyFour1;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour1")
    public Long femaleTwentyToTwentyFour1;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine1;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine1;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour1;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour1;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine1;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine1;

    @Column
    @Expose
    public Long maleFortyToFortyFour1;

    @Column
    @Expose
    public Long femaleFortyToFortyFour1;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine1;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine1;

    @Expose
    @Column(name = "maleFiftyPlus1")
    public Long maleFiftyPlus1;

    @Expose
    @Column(name = "femaleFiftyPlus1")
    public Long femaleFiftyPlus1;


    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    @Expose
    @Column(name = "newOnART")
    public Long newOnART;

    @Expose
    @Column(name = "alreadyOnART")
    public Long alreadyOnART;

    @Expose
    @Column(name = "denomNewOnART")
    public Long denomNewOnART;

    @Expose
    @Column(name = "denomAlreadyOnART")
    public Long denomAlreadyOnART;

    public ARTForm() {
        super();
    }

    public static ARTForm get(Long id) {
        return new Select().from(ARTForm.class).where("Id = ?", id).executeSingle();
    }

    public static ARTForm getARTForm(Long id) {
        return new Select().from(ARTForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<ARTForm> getAll() {
        return new Select()
                .from(ARTForm.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(ARTForm.class)
                .count();
    }

    public static List<ARTForm> getFilesToUpload() {
        return new Select()
                .from(ARTForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(ARTForm.class).execute();
    }

    public static ARTForm fromJson(JSONObject jsonObject) {
        ARTForm i = new ARTForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<ARTForm> fromJson(JSONArray jsonArray) {
        ArrayList<ARTForm> list = new ArrayList<ARTForm>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            ARTForm business = ARTForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<ARTForm>> LIST =
            new TypeToken<List<ARTForm>>() {
            };


    @Override
    public String toString() {
        return name;
    }

    public Long maleQuestion1() {
        return AppUtil.getLong(maleLessThanOne) + AppUtil.getLong(maleOneToFour) +
                AppUtil.getLong(maleFiveToNine) + AppUtil.getLong(maleTenToFourteen) +
                AppUtil.getLong(maleFifteenToNineteen) + AppUtil.getLong(maleTwentyToTwentyFour) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine) + AppUtil.getLong(maleThirtyToThirtyFour) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine) + AppUtil.getLong(maleFortyToFortyFour) +
                AppUtil.getLong(maleFortyFiveToFortyNine) + AppUtil.getLong(maleFiftyPlus);
    }

    public Long femaleQuestion1() {
        return AppUtil.getLong(femaleLessThanOne) + AppUtil.getLong(femaleOneToFour) +
                AppUtil.getLong(femaleFiveToNine) + AppUtil.getLong(femaleTenToFourteen) +
                AppUtil.getLong(femaleFifteenToNineteen) + AppUtil.getLong(femaleTwentyToTwentyFour) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine) + AppUtil.getLong(femaleThirtyToThirtyFour) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine) + AppUtil.getLong(femaleFortyToFortyFour) +
                AppUtil.getLong(femaleFortyFiveToFortyNine) + AppUtil.getLong(femaleFiftyPlus);
    }

    public Long maleQuestion2() {
        return AppUtil.getLong(maleLessThanOne1) + AppUtil.getLong(maleOneToFour1) +
                AppUtil.getLong(maleFiveToNine1) + AppUtil.getLong(maleTenToFourteen1) +
                AppUtil.getLong(maleFifteenToNineteen1) + AppUtil.getLong(maleTwentyToTwentyFour1) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine1) + AppUtil.getLong(maleThirtyToThirtyFour1) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine1) + AppUtil.getLong(maleFortyToFortyFour1) +
                AppUtil.getLong(maleFortyFiveToFortyNine1) + AppUtil.getLong(maleFiftyPlus1);
    }

    public Long femaleQuestion2() {
        return AppUtil.getLong(femaleLessThanOne1) + AppUtil.getLong(femaleOneToFour1) +
                AppUtil.getLong(femaleFiveToNine1) + AppUtil.getLong(femaleTenToFourteen1) +
                AppUtil.getLong(femaleFifteenToNineteen1) + AppUtil.getLong(femaleTwentyToTwentyFour1) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine1) + AppUtil.getLong(femaleThirtyToThirtyFour1) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine1) + AppUtil.getLong(femaleFortyToFortyFour1) +
                AppUtil.getLong(femaleFortyFiveToFortyNine1) + AppUtil.getLong(femaleFiftyPlus1);
    }

}

