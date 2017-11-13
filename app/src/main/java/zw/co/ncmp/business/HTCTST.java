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
 * Created by User on 3/16/2017.
 */
@Table(name = "htc_tst")
public class HTCTST extends Model {

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
    @Column(name = "pitcPositiveLessThan5")
    public Long pitcPositiveLessThan5;

    @Expose
    @Column(name = "pitcNegativeLessThan5")
    public Long pitcNegativeLessThan5;

    @Expose
    @Column(name = "malnutritionPositiveLessThan5")
    public Long malnutritionPositiveLessThan5;

    @Expose
    @Column(name = "malnutritionNegativeLessThan5")
    public Long malnutritionNegativeLessThan5;

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

    @Expose
    @Column(name = "maleLessThanOne2")
    public Long maleLessThanOne2;

    @Expose
    @Column(name = "femaleLessThanOne2")
    public Long femaleLessThanOne2;

    @Expose
    @Column(name = "maleOneToFour2")
    public Long maleOneToFour2;

    @Expose
    @Column(name = "femaleOneToFour2")
    public Long femaleOneToFour2;

    @Expose
    @Column(name = "maleFiveToNine2")
    public Long maleFiveToNine2;

    @Expose
    @Column(name = "femaleFiveToNine2")
    public Long femaleFiveToNine2;

    @Expose
    @Column(name = "maleTenToFourteen2")
    public Long maleTenToFourteen2;

    @Expose
    @Column(name = "femaleTenToFourteen2")
    public Long femaleTenToFourteen2;

    @Expose
    @Column(name = "maleFifteenToNineteen2")
    public Long maleFifteenToNineteen2;

    @Expose
    @Column(name = "femaleFifteenToNineteen2")
    public Long femaleFifteenToNineteen2;


    @Expose
    @Column(name = "maleTwentyToTwentyFour2")
    public Long maleTwentyToTwentyFour2;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour2")
    public Long femaleTwentyToTwentyFour2;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine2;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine2;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour2;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour2;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine2;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine2;

    @Column
    @Expose
    public Long maleFortyToFortyFour2;

    @Column
    @Expose
    public Long femaleFortyToFortyFour2;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine2;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine2;

    @Expose
    @Column(name = "maleFiftyPlus2")
    public Long maleFiftyPlus2;

    @Expose
    @Column(name = "femaleFiftyPlus2")
    public Long femaleFiftyPlus2;

    @Expose
    @Column(name = "maleLessThanOne3")
    public Long maleLessThanOne3;

    @Expose
    @Column(name = "femaleLessThanOne3")
    public Long femaleLessThanOne3;

    @Expose
    @Column(name = "maleOneToFour3")
    public Long maleOneToFour3;

    @Expose
    @Column(name = "femaleOneToFour3")
    public Long femaleOneToFour3;

    @Expose
    @Column(name = "maleFiveToNine3")
    public Long maleFiveToNine3;

    @Expose
    @Column(name = "femaleFiveToNine3")
    public Long femaleFiveToNine3;

    @Expose
    @Column(name = "maleTenToFourteen3")
    public Long maleTenToFourteen3;

    @Expose
    @Column(name = "femaleTenToFourteen3")
    public Long femaleTenToFourteen3;

    @Expose
    @Column(name = "maleFifteenToNineteen3")
    public Long maleFifteenToNineteen3;

    @Expose
    @Column(name = "femaleFifteenToNineteen3")
    public Long femaleFifteenToNineteen3;


    @Expose
    @Column(name = "maleTwentyToTwentyFour3")
    public Long maleTwentyToTwentyFour3;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour3")
    public Long femaleTwentyToTwentyFour3;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine3;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine3;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour3;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour3;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine3;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine3;

    @Column
    @Expose
    public Long maleFortyToFortyFour3;

    @Column
    @Expose
    public Long femaleFortyToFortyFour3;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine3;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine3;

    @Expose
    @Column(name = "maleFiftyPlus3")
    public Long maleFiftyPlus3;

    @Expose
    @Column(name = "femaleFiftyPlus3")
    public Long femaleFiftyPlus3;

    @Expose
    @Column(name = "maleLessThanOne4")
    public Long maleLessThanOne4;

    @Expose
    @Column(name = "femaleLessThanOne4")
    public Long femaleLessThanOne4;

    @Expose
    @Column(name = "maleOneToFour4")
    public Long maleOneToFour4;

    @Expose
    @Column(name = "femaleOneToFour4")
    public Long femaleOneToFour4;

    @Expose
    @Column(name = "maleFiveToNine4")
    public Long maleFiveToNine4;

    @Expose
    @Column(name = "femaleFiveToNine4")
    public Long femaleFiveToNine4;

    @Expose
    @Column(name = "maleTenToFourteen4")
    public Long maleTenToFourteen4;

    @Expose
    @Column(name = "femaleTenToFourteen4")
    public Long femaleTenToFourteen4;

    @Expose
    @Column(name = "maleFifteenToNineteen4")
    public Long maleFifteenToNineteen4;

    @Expose
    @Column(name = "femaleFifteenToNineteen4")
    public Long femaleFifteenToNineteen4;

    @Expose
    @Column(name = "maleTwentyToTwentyFour4")
    public Long maleTwentyToTwentyFour4;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour4")
    public Long femaleTwentyToTwentyFour4;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine4;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine4;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour4;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour4;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine4;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine4;

    @Column
    @Expose
    public Long maleFortyToFortyFour4;

    @Column
    @Expose
    public Long femaleFortyToFortyFour4;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine4;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine4;

    @Expose
    @Column(name = "maleFiftyPlus4")
    public Long maleFiftyPlus4;

    @Expose
    @Column(name = "femaleFiftyPlus4")
    public Long femaleFiftyPlus4;

    @Expose
    @Column(name = "maleLessThanOne5")
    public Long maleLessThanOne5;

    @Expose
    @Column(name = "femaleLessThanOne5")
    public Long femaleLessThanOne5;

    @Expose
    @Column(name = "maleOneToFour5")
    public Long maleOneToFour5;

    @Expose
    @Column(name = "femaleOneToFour5")
    public Long femaleOneToFour5;

    @Expose
    @Column(name = "maleFiveToNine5")
    public Long maleFiveToNine5;

    @Expose
    @Column(name = "femaleFiveToNine5")
    public Long femaleFiveToNine5;

    @Expose
    @Column(name = "maleTenToFourteen5")
    public Long maleTenToFourteen5;

    @Expose
    @Column(name = "femaleTenToFourteen5")
    public Long femaleTenToFourteen5;

    @Expose
    @Column(name = "maleFifteenToNineteen5")
    public Long maleFifteenToNineteen5;

    @Expose
    @Column(name = "femaleFifteenToNineteen5")
    public Long femaleFifteenToNineteen5;

    @Expose
    @Column(name = "maleTwentyToTwentyFour5")
    public Long maleTwentyToTwentyFour5;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour5")
    public Long femaleTwentyToTwentyFour5;

    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine5;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine5;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour5;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour5;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine5;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine5;

    @Column
    @Expose
    public Long maleFortyToFortyFour5;

    @Column
    @Expose
    public Long femaleFortyToFortyFour5;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine5;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine5;

    @Expose
    @Column(name = "maleFiftyPlus5")
    public Long maleFiftyPlus5;

    @Expose
    @Column(name = "femaleFiftyPlus5")
    public Long femaleFiftyPlus5;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public HTCTST(){

    }

    public static HTCTST get(Long id) {
        return new Select().from(HTCTST.class).where("Id = ?", id).executeSingle();
    }

    public static HTCTST getHTCTST(Long id) {
        return new Select().from(HTCTST.class).where("serverId = ?", id).executeSingle();
    }

    public static List<HTCTST> getAll() {
        return new Select()
                .from(HTCTST.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(HTCTST.class)
                .count();
    }

    public static List<HTCTST> getFilesToUpload() {
        return new Select()
                .from(HTCTST.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(HTCTST.class).execute();
    }

    public static HTCTST fromJson(JSONObject jsonObject) {
        HTCTST i = new HTCTST();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<HTCTST> fromJson(JSONArray jsonArray) {
        ArrayList<HTCTST> list = new ArrayList<HTCTST>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            HTCTST business = HTCTST.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<HTCTST>> LIST =
            new TypeToken<List<HTCTST>>() {
            };


    @Override
    public String toString() {
        return name;
    }

    public Long pitcMalePositive() {
        return AppUtil.getLong(maleLessThanOne) + AppUtil.getLong(maleOneToFour) +
                AppUtil.getLong(maleFiveToNine) + AppUtil.getLong(maleTenToFourteen) +
                AppUtil.getLong(maleFifteenToNineteen) + AppUtil.getLong(maleTwentyToTwentyFour) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine) + AppUtil.getLong(maleThirtyToThirtyFour) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine) + AppUtil.getLong(maleFortyToFortyFour) +
                AppUtil.getLong(maleFortyFiveToFortyNine) + AppUtil.getLong(maleFiftyPlus);
    }

    public Long pitcFemalePositive() {
        return AppUtil.getLong(femaleLessThanOne) + AppUtil.getLong(femaleOneToFour) +
                AppUtil.getLong(femaleFiveToNine) + AppUtil.getLong(femaleTenToFourteen) +
                AppUtil.getLong(femaleFifteenToNineteen) + AppUtil.getLong(femaleTwentyToTwentyFour) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine) + AppUtil.getLong(femaleThirtyToThirtyFour) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine) + AppUtil.getLong(femaleFortyToFortyFour) +
                AppUtil.getLong(femaleFortyFiveToFortyNine) + AppUtil.getLong(femaleFiftyPlus);
    }

    public Long pitcMaleNegative() {
        return AppUtil.getLong(maleLessThanOne1) + AppUtil.getLong(maleOneToFour1) +
                AppUtil.getLong(maleFiveToNine1) + AppUtil.getLong(maleTenToFourteen1) +
                AppUtil.getLong(maleFifteenToNineteen1) + AppUtil.getLong(maleTwentyToTwentyFour1) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine1) + AppUtil.getLong(maleThirtyToThirtyFour1) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine1) + AppUtil.getLong(maleFortyToFortyFour1) +
                AppUtil.getLong(maleFortyFiveToFortyNine1) + AppUtil.getLong(maleFiftyPlus1);
    }

    public Long pitcFemaleNegative() {
        return AppUtil.getLong(femaleLessThanOne1) + AppUtil.getLong(femaleOneToFour1) +
                AppUtil.getLong(femaleFiveToNine1) + AppUtil.getLong(femaleTenToFourteen1) +
                AppUtil.getLong(femaleFifteenToNineteen1) + AppUtil.getLong(femaleTwentyToTwentyFour1) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine1) + AppUtil.getLong(femaleThirtyToThirtyFour1) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine1) + AppUtil.getLong(femaleFortyToFortyFour1) +
                AppUtil.getLong(femaleFortyFiveToFortyNine1) + AppUtil.getLong(femaleFiftyPlus1);
    }

    public Long tbClinicsMaleNegative() {
        return AppUtil.getLong(maleLessThanOne2) + AppUtil.getLong(maleOneToFour2) +
                AppUtil.getLong(maleFiveToNine2) + AppUtil.getLong(maleTenToFourteen2) +
                AppUtil.getLong(maleFifteenToNineteen2) + AppUtil.getLong(maleTwentyToTwentyFour2) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine2) + AppUtil.getLong(maleThirtyToThirtyFour2) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine2) + AppUtil.getLong(maleFortyToFortyFour2) +
                AppUtil.getLong(maleFortyFiveToFortyNine2) + AppUtil.getLong(maleFiftyPlus2);
    }

    public Long tbClinicsFemaleTestedNegative() {
        return AppUtil.getLong(femaleLessThanOne2) + AppUtil.getLong(femaleOneToFour2) +
                AppUtil.getLong(femaleFiveToNine2) + AppUtil.getLong(femaleTenToFourteen2) +
                AppUtil.getLong(femaleFifteenToNineteen2) + AppUtil.getLong(femaleTwentyToTwentyFour2) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine2) + AppUtil.getLong(femaleThirtyToThirtyFour2) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine2) + AppUtil.getLong(femaleFortyToFortyFour2) +
                AppUtil.getLong(femaleFortyFiveToFortyNine2) + AppUtil.getLong(femaleFiftyPlus2);
    }

    public Long tbClinicsMalePositive() {
        return AppUtil.getLong(maleLessThanOne3) + AppUtil.getLong(maleOneToFour3) +
                AppUtil.getLong(maleFiveToNine3) + AppUtil.getLong(maleTenToFourteen3) +
                AppUtil.getLong(maleFifteenToNineteen3) + AppUtil.getLong(maleTwentyToTwentyFour3) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine3) + AppUtil.getLong(maleThirtyToThirtyFour3) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine3) + AppUtil.getLong(maleFortyToFortyFour3) +
                AppUtil.getLong(maleFortyFiveToFortyNine3) + AppUtil.getLong(maleFiftyPlus3);
    }

    public Long tbClinicsFemaleTestedPositive() {
        return AppUtil.getLong(femaleLessThanOne3) + AppUtil.getLong(femaleOneToFour3) +
                AppUtil.getLong(femaleFiveToNine3) + AppUtil.getLong(femaleTenToFourteen3) +
                AppUtil.getLong(femaleFifteenToNineteen3) + AppUtil.getLong(femaleTwentyToTwentyFour3) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine3) + AppUtil.getLong(femaleThirtyToThirtyFour3) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine3) + AppUtil.getLong(femaleFortyToFortyFour3) +
                AppUtil.getLong(femaleFortyFiveToFortyNine3) + AppUtil.getLong(femaleFiftyPlus3);
    }

    public Long ancMalePositive() {
        return AppUtil.getLong(maleLessThanOne4) + AppUtil.getLong(maleOneToFour4) +
                AppUtil.getLong(maleFiveToNine4) + AppUtil.getLong(maleTenToFourteen4) +
                AppUtil.getLong(maleFifteenToNineteen4) + AppUtil.getLong(maleTwentyToTwentyFour4) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine4) + AppUtil.getLong(maleThirtyToThirtyFour4) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine4) + AppUtil.getLong(maleFortyToFortyFour4) +
                AppUtil.getLong(maleFortyFiveToFortyNine4) + AppUtil.getLong(maleFiftyPlus4);
    }

    public Long ancFemalePositive() {
        return AppUtil.getLong(femaleLessThanOne4) + AppUtil.getLong(femaleOneToFour4) +
                AppUtil.getLong(femaleFiveToNine4) + AppUtil.getLong(femaleTenToFourteen4) +
                AppUtil.getLong(femaleFifteenToNineteen4) + AppUtil.getLong(femaleTwentyToTwentyFour4) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine4) + AppUtil.getLong(femaleThirtyToThirtyFour4) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine4) + AppUtil.getLong(femaleFortyToFortyFour4) +
                AppUtil.getLong(femaleFortyFiveToFortyNine4) + AppUtil.getLong(femaleFiftyPlus4);
    }

    public Long ancMaleNegative() {
        return AppUtil.getLong(maleLessThanOne5) + AppUtil.getLong(maleOneToFour5) +
                AppUtil.getLong(maleFiveToNine5) + AppUtil.getLong(maleTenToFourteen5) +
                AppUtil.getLong(maleFifteenToNineteen5) + AppUtil.getLong(maleTwentyToTwentyFour5) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine5) + AppUtil.getLong(maleThirtyToThirtyFour5) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine5) + AppUtil.getLong(maleFortyToFortyFour5) +
                AppUtil.getLong(maleFortyFiveToFortyNine5) + AppUtil.getLong(maleFiftyPlus5);
    }

    public Long ancFemaleNegative() {
        return AppUtil.getLong(femaleLessThanOne5) + AppUtil.getLong(femaleOneToFour5) +
                AppUtil.getLong(femaleFiveToNine5) + AppUtil.getLong(femaleTenToFourteen5) +
                AppUtil.getLong(femaleFifteenToNineteen5) + AppUtil.getLong(femaleTwentyToTwentyFour5) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine5) + AppUtil.getLong(femaleThirtyToThirtyFour5) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine5) + AppUtil.getLong(femaleFortyToFortyFour5) +
                AppUtil.getLong(femaleFortyFiveToFortyNine5) + AppUtil.getLong(femaleFiftyPlus5);
    }
}
