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
 * Created by tdhlakama on 2/6/2016.
 */
@Table(name = "dsd_individual")
public class DSDIndividual extends Model {

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    @Column(name = "start_date")
    public Date startDate;

    @Column(name = "end_date")
    public Date endDate;

    @Column(name = "date_created")
    public Date dateCreated;

    @SerializedName("datec")
    @Expose
    public String serverCreatedDate;

    @SerializedName("start")
    @Expose
    public String startDateC;

    @SerializedName("end")
    @Expose
    public String endDateC;

    @Expose
    @Column(name = "name")
    public String name;

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
    @Column(name = "maleTwentyFiveToFortyNine1")
    public Long maleTwentyFiveToFortyNine1;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine1")
    public Long femaleTwentyFiveToFortyNine1;

    @Expose
    @Column(name = "maleFiftyPlus1")
    public Long maleFiftyPlus1;

    @Expose
    @Column(name = "femaleFiftyPlus1")
    public Long femaleFiftyPlus1;

    @Expose
    @Column(name = "maleFifteenLess2")
    public Long maleFifteenLess2;

    @Expose
    @Column(name = "femaleFifteenLess2")
    public Long femaleFifteenLess2;

    @Expose
    @Column(name = "maleFifteenPlus2")
    public Long maleFifteenPlus2;

    @Expose
    @Column(name = "femaleFifteenPlus2")
    public Long femaleFifteenPlus2;

    @Expose
    @Column(name = "maleFifteenLess3")
    public Long maleFifteenLess3;

    @Expose
    @Column(name = "femaleFifteenLess3")
    public Long femaleFifteenLess3;

    @Expose
    @Column(name = "maleFifteenPlus3")
    public Long maleFifteenPlus3;

    @Expose
    @Column(name = "femaleFifteenPlus3")
    public Long femaleFifteenPlus3;

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
    @Column(name = "maleTwentyFiveToFortyNine4")
    public Long maleTwentyFiveToFortyNine4;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine4")
    public Long femaleTwentyFiveToFortyNine4;

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
    @Column(name = "maleTwentyFiveToFortyNine5")
    public Long maleTwentyFiveToFortyNine5;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine5")
    public Long femaleTwentyFiveToFortyNine5;

    @Expose
    @Column(name = "maleFiftyPlus5")
    public Long maleFiftyPlus5;

    @Expose
    @Column(name = "femaleFiftyPlus5")
    public Long femaleFiftyPlus5;

    @Expose
    @Column(name = "maleLessThanOne6")
    public Long maleLessThanOne6;

    @Expose
    @Column(name = "femaleLessThanOne6")
    public Long femaleLessThanOne6;

    @Expose
    @Column(name = "maleOneToFour6")
    public Long maleOneToFour6;

    @Expose
    @Column(name = "femaleOneToFour6")
    public Long femaleOneToFour6;

    @Expose
    @Column(name = "maleFiveToNine6")
    public Long maleFiveToNine6;

    @Expose
    @Column(name = "femaleFiveToNine6")
    public Long femaleFiveToNine6;

    @Expose
    @Column(name = "maleTenToFourteen6")
    public Long maleTenToFourteen6;

    @Expose
    @Column(name = "femaleTenToFourteen6")
    public Long femaleTenToFourteen6;

    @Expose
    @Column(name = "maleFifteenToNineteen6")
    public Long maleFifteenToNineteen6;

    @Expose
    @Column(name = "femaleFifteenToNineteen6")
    public Long femaleFifteenToNineteen6;

    @Expose
    @Column(name = "maleTwentyToTwentyFour6")
    public Long maleTwentyToTwentyFour6;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour6")
    public Long femaleTwentyToTwentyFour6;

    @Expose
    @Column(name = "maleTwentyFiveToFortyNine6")
    public Long maleTwentyFiveToFortyNine6;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine6")
    public Long femaleTwentyFiveToFortyNine6;

    @Expose
    @Column(name = "maleFiftyPlus6")
    public Long maleFiftyPlus6;

    @Expose
    @Column(name = "femaleFiftyPlus6")
    public Long femaleFiftyPlus6;

    @Expose
    @Column(name = "maleLessThanOne7")
    public Long maleLessThanOne7;

    @Expose
    @Column(name = "femaleLessThanOne7")
    public Long femaleLessThanOne7;

    @Expose
    @Column(name = "maleOneToFour7")
    public Long maleOneToFour7;

    @Expose
    @Column(name = "femaleOneToFour7")
    public Long femaleOneToFour7;

    @Expose
    @Column(name = "maleFiveToNine7")
    public Long maleFiveToNine7;

    @Expose
    @Column(name = "femaleFiveToNine7")
    public Long femaleFiveToNine7;

    @Expose
    @Column(name = "maleTenToFourteen7")
    public Long maleTenToFourteen7;

    @Expose
    @Column(name = "femaleTenToFourteen7")
    public Long femaleTenToFourteen7;

    @Expose
    @Column(name = "maleFifteenToNineteen7")
    public Long maleFifteenToNineteen7;

    @Expose
    @Column(name = "femaleFifteenToNineteen7")
    public Long femaleFifteenToNineteen7;

    @Expose
    @Column(name = "maleTwentyToTwentyFour7")
    public Long maleTwentyToTwentyFour7;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour7")
    public Long femaleTwentyToTwentyFour7;

    @Expose
    @Column(name = "maleTwentyFiveToFortyNine7")
    public Long maleTwentyFiveToFortyNine7;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine7")
    public Long femaleTwentyFiveToFortyNine7;

    @Expose
    @Column(name = "maleFiftyPlus7")
    public Long maleFiftyPlus7;

    @Expose
    @Column(name = "femaleFiftyPlus7")
    public Long femaleFiftyPlus7;

    @Expose
    @Column(name = "maleLessThanOne8")
    public Long maleLessThanOne8;

    @Expose
    @Column(name = "femaleLessThanOne8")
    public Long femaleLessThanOne8;

    @Expose
    @Column(name = "maleOneToFour8")
    public Long maleOneToFour8;

    @Expose
    @Column(name = "femaleOneToFour8")
    public Long femaleOneToFour8;

    @Expose
    @Column(name = "maleFiveToNine8")
    public Long maleFiveToNine8;

    @Expose
    @Column(name = "femaleFiveToNine8")
    public Long femaleFiveToNine8;

    @Expose
    @Column(name = "maleTenToFourteen8")
    public Long maleTenToFourteen8;

    @Expose
    @Column(name = "femaleTenToFourteen8")
    public Long femaleTenToFourteen8;

    @Expose
    @Column(name = "maleFifteenToNineteen8")
    public Long maleFifteenToNineteen8;

    @Expose
    @Column(name = "femaleFifteenToNineteen8")
    public Long femaleFifteenToNineteen8;

    @Expose
    @Column(name = "maleTwentyToTwentyFour8")
    public Long maleTwentyToTwentyFour8;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour8")
    public Long femaleTwentyToTwentyFour8;

    @Expose
    @Column(name = "maleTwentyFiveToFortyNine8")
    public Long maleTwentyFiveToFortyNine8;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine8")
    public Long femaleTwentyFiveToFortyNine8;

    @Expose
    @Column(name = "maleFiftyPlus8")
    public Long maleFiftyPlus8;

    @Expose
    @Column(name = "femaleFiftyPlus8")
    public Long femaleFiftyPlus8;


    @Expose
    @Column(name = "maleLessThanOne9")
    public Long maleLessThanOne9;

    @Expose
    @Column(name = "femaleLessThanOne9")
    public Long femaleLessThanOne9;

    @Expose
    @Column(name = "maleOneToFour9")
    public Long maleOneToFour9;

    @Expose
    @Column(name = "femaleOneToFour9")
    public Long femaleOneToFour9;

    @Expose
    @Column(name = "maleFiveToNine9")
    public Long maleFiveToNine9;

    @Expose
    @Column(name = "femaleFiveToNine9")
    public Long femaleFiveToNine9;

    @Expose
    @Column(name = "maleTenToFourteen9")
    public Long maleTenToFourteen9;

    @Expose
    @Column(name = "femaleTenToFourteen9")
    public Long femaleTenToFourteen9;

    @Expose
    @Column(name = "maleFifteenToNineteen9")
    public Long maleFifteenToNineteen9;

    @Expose
    @Column(name = "femaleFifteenToNineteen9")
    public Long femaleFifteenToNineteen9;

    @Expose
    @Column(name = "maleTwentyToTwentyFour9")
    public Long maleTwentyToTwentyFour9;

    @Expose
    @Column(name = "femaleTwentyToTwentyFour9")
    public Long femaleTwentyToTwentyFour9;

    @Expose
    @Column(name = "maleTwentyFiveToFortyNine9")
    public Long maleTwentyFiveToFortyNine9;

    @Expose
    @Column(name = "femaleTwentyFiveToFortyNine9")
    public Long femaleTwentyFiveToFortyNine9;

    @Expose
    @Column(name = "maleFiftyPlus9")
    public Long maleFiftyPlus9;

    @Expose
    @Column(name = "femaleFiftyPlus9")
    public Long femaleFiftyPlus9;

    @Expose
    @Column(name = "testedOPD")
    public Long testedOPD;

    @Expose
    @Column(name = "positiveTestedOPD")
    public Long positiveTestedOPD;

    @Expose
    @Column(name = "testedOutreach")
    public Long testedOutreach;

    @Expose
    @Column(name = "positiveTestedOutreach")
    public Long positiveTestedOutreach;

    @Expose
    @Column(name = "testedANC")
    public Long testedANC;

    @Expose
    @Column(name = "positiveTestedANC")
    public Long positiveTestedANC;

    @Expose
    @Column(name = "testedPNC")
    public Long testedPNC;

    @Expose
    @Column(name = "positiveTestedPNC")
    public Long positiveTestedPNC;

    @Expose
    @Column(name = "testedInpatient")
    public Long testedInpatient;

    @Expose
    @Column(name = "positiveTestedInpatient")
    public Long positiveTestedInpatient;

    @Expose
    @Column(name = "testedPaediatric")
    public Long testedPaediatric;

    @Expose
    @Column(name = "positiveTestedPaediatric")
    public Long positiveTestedPaediatric;

    @Expose
    @Column(name = "testedIndex")
    public Long testedIndex;

    @Expose
    @Column(name = "positiveTestedIndex")
    public Long positiveTestedIndex;

    @Expose
    @Column(name = "testedSTI")
    public Long testedSTI;

    @Expose
    @Column(name = "positiveTestedSTI")
    public Long positiveTestedSTI;

    @Expose
    @Column(name = "testedVIAC")
    public Long testedVIAC;

    @Expose
    @Column(name = "testedVMMC")
    public Long testedVMMC;

    @Expose
    @Column(name = "positiveTestedVMMC")
    public Long positiveTestedVMMC;

    @Expose
    @Column(name = "positiveTestedVIAC")
    public Long positiveTestedVIAC;

    @SerializedName("testedTBUnit")
    @Expose
    @Column(name = "testedTB")
    public Long testedTB;

    @SerializedName("positiveTestedTBUnit")
    @Expose
    @Column(name = "positiveTestedTB")
    public Long positiveTestedTB;

    @Expose
    @Column(name = "couplesTested")
    public Long couplesTested;

    @Expose
    @Column(name = "couplesPositiveTested")
    public Long couplesPositiveTested;

    @Expose
    @Column(name = "couplesDiscordantTested")
    public Long couplesDiscordantTested;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public static DSDIndividual get(Long id) {
        return new Select().from(DSDIndividual.class).where("Id = ?", id).executeSingle();
    }

    public static DSDIndividual getDSDIndividual(Long id) {
        return new Select().from(DSDIndividual.class).where("serverId = ?", id).executeSingle();
    }

    public static List<DSDIndividual> getAll() {
        return new Select()
                .from(DSDIndividual.class)
                .orderBy("date_created ASC")
                .execute();
    }

    public static List<DSDIndividual> getFilesToUpload() {
        return new Select()
                .from(DSDIndividual.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(DSDIndividual.class).execute();
    }

    @Override
    public String toString() {
        return "HTC_TST: DSD- Couples";
    }

    public static DSDIndividual fromJson(JSONObject jsonObject) {
        DSDIndividual i = new DSDIndividual();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<DSDIndividual> fromJson(JSONArray jsonArray) {
        ArrayList<DSDIndividual> list = new ArrayList<DSDIndividual>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            DSDIndividual business = DSDIndividual.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<DSDIndividual>> LIST =
            new TypeToken<List<DSDIndividual>>() {
            };

    public Long maleQuestion1() {
        return AppUtil.getLong(maleLessThanOne1) + AppUtil.getLong(maleOneToFour1) +
                AppUtil.getLong(maleFiveToNine1) + AppUtil.getLong(maleTenToFourteen1) +
                AppUtil.getLong(maleFifteenToNineteen1) + AppUtil.getLong(maleTwentyToTwentyFour1) +
                AppUtil.getLong(maleTwentyFiveToFortyNine1) + AppUtil.getLong(maleFiftyPlus1);
    }

    public Long femaleQuestion1() {
        return AppUtil.getLong(femaleLessThanOne1) + AppUtil.getLong(femaleOneToFour1) +
                AppUtil.getLong(femaleFiveToNine1) + AppUtil.getLong(femaleTenToFourteen1) +
                AppUtil.getLong(femaleFifteenToNineteen1) + AppUtil.getLong(femaleTwentyToTwentyFour1) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine1) + AppUtil.getLong(femaleFiftyPlus1);
    }

    public Long maleQuestion2() {
        return AppUtil.getLong(maleFifteenLess2) + AppUtil.getLong(maleFifteenPlus2);
    }

    public Long femaleQuestion2() {
        return AppUtil.getLong(femaleFifteenLess2) + AppUtil.getLong(femaleFifteenPlus2);

    }

    public Long maleQuestion3() {
        return AppUtil.getLong(maleFifteenLess3) + AppUtil.getLong(maleFifteenPlus3);
    }


    public Long femaleQuestion3() {
        return AppUtil.getLong(femaleFifteenLess3) + AppUtil.getLong(femaleFifteenPlus3);

    }

    public Long maleQuestion4() {
        return AppUtil.getLong(maleLessThanOne4) + AppUtil.getLong(maleOneToFour4) +
                AppUtil.getLong(maleFiveToNine4) + AppUtil.getLong(maleTenToFourteen4) +
                AppUtil.getLong(maleFifteenToNineteen4) + AppUtil.getLong(maleTwentyToTwentyFour4) +
                AppUtil.getLong(maleTwentyFiveToFortyNine4) + AppUtil.getLong(maleFiftyPlus4);
    }

    public Long femaleQuestion4() {
        return AppUtil.getLong(femaleLessThanOne4) + AppUtil.getLong(femaleOneToFour4) +
                AppUtil.getLong(femaleFiveToNine4) + AppUtil.getLong(femaleTenToFourteen4) +
                AppUtil.getLong(femaleFifteenToNineteen4) + AppUtil.getLong(femaleTwentyToTwentyFour4) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine4) + AppUtil.getLong(femaleFiftyPlus4);
    }

    public Long maleQuestion5() {
        return AppUtil.getLong(maleLessThanOne5) + AppUtil.getLong(maleOneToFour5) +
                AppUtil.getLong(maleFiveToNine5) + AppUtil.getLong(maleTenToFourteen5) +
                AppUtil.getLong(maleFifteenToNineteen5) + AppUtil.getLong(maleTwentyToTwentyFour5) +
                AppUtil.getLong(maleTwentyFiveToFortyNine5) + AppUtil.getLong(maleFiftyPlus5);
    }

    public Long femaleQuestion5() {
        return AppUtil.getLong(femaleLessThanOne5) + AppUtil.getLong(femaleOneToFour5) +
                AppUtil.getLong(femaleFiveToNine5) + AppUtil.getLong(femaleTenToFourteen5) +
                AppUtil.getLong(femaleFifteenToNineteen5) + AppUtil.getLong(femaleTwentyToTwentyFour5) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine5) + AppUtil.getLong(femaleFiftyPlus5);
    }

    public Long maleQuestion6() {
        return AppUtil.getLong(maleLessThanOne6) + AppUtil.getLong(maleOneToFour6) +
                AppUtil.getLong(maleFiveToNine6) + AppUtil.getLong(maleTenToFourteen6) +
                AppUtil.getLong(maleFifteenToNineteen6) + AppUtil.getLong(maleTwentyToTwentyFour6) +
                AppUtil.getLong(maleTwentyFiveToFortyNine6) + AppUtil.getLong(maleFiftyPlus6);
    }

    public Long femaleQuestion6() {
        return AppUtil.getLong(femaleLessThanOne6) + AppUtil.getLong(femaleOneToFour6) +
                AppUtil.getLong(femaleFiveToNine6) + AppUtil.getLong(femaleTenToFourteen6) +
                AppUtil.getLong(femaleFifteenToNineteen6) + AppUtil.getLong(femaleTwentyToTwentyFour6) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine6) + AppUtil.getLong(femaleFiftyPlus6);
    }

    public Long maleQuestion7() {
        return AppUtil.getLong(maleLessThanOne7) + AppUtil.getLong(maleOneToFour7) +
                AppUtil.getLong(maleFiveToNine7) + AppUtil.getLong(maleTenToFourteen7) +
                AppUtil.getLong(maleFifteenToNineteen7) + AppUtil.getLong(maleTwentyToTwentyFour7) +
                AppUtil.getLong(maleTwentyFiveToFortyNine7) + AppUtil.getLong(maleFiftyPlus7);
    }

    public Long femaleQuestion7() {
        return AppUtil.getLong(femaleLessThanOne7) + AppUtil.getLong(femaleOneToFour7) +
                AppUtil.getLong(femaleFiveToNine7) + AppUtil.getLong(femaleTenToFourteen7) +
                AppUtil.getLong(femaleFifteenToNineteen7) + AppUtil.getLong(femaleTwentyToTwentyFour7) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine7) + AppUtil.getLong(femaleFiftyPlus7);
    }

    public Long maleQuestion8() {
        return AppUtil.getLong(maleLessThanOne8) + AppUtil.getLong(maleOneToFour8) +
                AppUtil.getLong(maleFiveToNine8) + AppUtil.getLong(maleTenToFourteen8) +
                AppUtil.getLong(maleFifteenToNineteen8) + AppUtil.getLong(maleTwentyToTwentyFour8) +
                AppUtil.getLong(maleTwentyFiveToFortyNine8) + AppUtil.getLong(maleFiftyPlus8);
    }

    public Long femaleQuestion8() {
        return AppUtil.getLong(femaleLessThanOne8) + AppUtil.getLong(femaleOneToFour8) +
                AppUtil.getLong(femaleFiveToNine8) + AppUtil.getLong(femaleTenToFourteen8) +
                AppUtil.getLong(femaleFifteenToNineteen8) + AppUtil.getLong(femaleTwentyToTwentyFour8) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine8) + AppUtil.getLong(femaleFiftyPlus8);
    }

    public Long maleQuestion9() {
        return AppUtil.getLong(maleLessThanOne9) + AppUtil.getLong(maleOneToFour9) +
                AppUtil.getLong(maleFiveToNine9) + AppUtil.getLong(maleTenToFourteen9) +
                AppUtil.getLong(maleFifteenToNineteen9) + AppUtil.getLong(maleTwentyToTwentyFour9) +
                AppUtil.getLong(maleTwentyFiveToFortyNine9) + AppUtil.getLong(maleFiftyPlus9);
    }

    public Long femaleQuestion9() {
        return AppUtil.getLong(femaleLessThanOne9) + AppUtil.getLong(femaleOneToFour9) +
                AppUtil.getLong(femaleFiveToNine9) + AppUtil.getLong(femaleTenToFourteen9) +
                AppUtil.getLong(femaleFifteenToNineteen9) + AppUtil.getLong(femaleTwentyToTwentyFour9) +
                AppUtil.getLong(femaleTwentyFiveToFortyNine9) + AppUtil.getLong(femaleFiftyPlus9);
    }

    public Long getEntryPointTested(){
        return AppUtil.getLong(testedOPD) + AppUtil.getLong(testedOutreach) + AppUtil.getLong(testedPNC) + AppUtil.getLong(testedANC) + AppUtil.getLong(testedInpatient)
                + AppUtil.getLong(testedPaediatric)  + AppUtil.getLong(testedIndex) + AppUtil.getLong(testedSTI)
                + AppUtil.getLong(testedVMMC) +  + AppUtil.getLong(testedVIAC) +AppUtil.getLong(testedTB);
    }

    public Long getEntryPointTestedPositive(){
        return AppUtil.getLong(positiveTestedOPD)
                + AppUtil.getLong(positiveTestedOutreach) + AppUtil.getLong(positiveTestedPNC)
                + AppUtil.getLong(positiveTestedANC)
                + AppUtil.getLong(positiveTestedInpatient)+ AppUtil.getLong(positiveTestedPaediatric)
                + AppUtil.getLong(positiveTestedIndex)
                + AppUtil.getLong(positiveTestedSTI)+ AppUtil.getLong(positiveTestedVMMC)
                + AppUtil.getLong(positiveTestedVIAC)+ AppUtil.getLong(positiveTestedTB);
    }

}
