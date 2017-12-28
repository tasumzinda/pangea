package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static zw.co.ncmp.util.AppUtil.getLong;

/**
 * @uthor Tasu Muzinda
 */
@Table(name = "htstst_b7")
public class HTSTSTB7 extends Model{

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

    @SerializedName("start")
    @Expose
    public String startDateC;

    @SerializedName("end")
    @Expose
    public String endDateC;

    @Column(name = "date_created")
    public Date dateCreated;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    @SerializedName("datec")
    @Expose
    public String serverCreatedDate;

    @Expose
    @Column(name = "name")
    public String name;

    @Expose
    @Column(name = "maleLessThanTwoMonths")
    public Long maleLessThanTwoMonths;

    @Expose
    @Column(name = "femaleLessThanTwoMonths")
    public Long femaleLessThanTwoMonths;

    @Expose
    @Column(name = "maleTwoToTwelveMonths")
    public Long maleTwoToTwelveMonths;

    @Expose
    @Column(name = "femaleTwoToTwelveMonths")
    public Long femaleTwoToTwelveMonths;

    @Expose
    @Column(name = "maleThirteenToTwentyFourMonths")
    public Long maleThirteenToTwentyFourMonths;

    @Expose
    @Column(name = "femaleThirteenToTwentyFourMonths")
    public Long femaleThirteenToTwentyFourMonths;

    @Expose
    @Column(name = "maleTwentyFiveToFiftyNineMonths")
    public Long maleTwentyFiveToFiftyNineMonths;

    @Expose
    @Column(name = "femaleTwentyFiveToFiftyNineMonths")
    public Long femaleTwentyFiveToFiftyNineMonths;

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
    public Long maleThirtyToFortyNine1;

    @Column
    @Expose
    public Long femaleThirtyToFortyNine1;

    @Expose
    @Column(name = "maleFiftyPlus1")
    public Long maleFiftyPlus1;

    @Expose
    @Column(name = "femaleFiftyPlus1")
    public Long femaleFiftyPlus1;

    @Expose
    @Column(name = "maleLessThanFifteen")
    public Long maleLessThanFifteen;

    @Expose
    @Column(name = "femaleLessThanFifteen")
    public Long femaleLessThanFifteen;

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
    public Long maleThirtyToFortyNine2;

    @Column
    @Expose
    public Long femaleThirtyToFortyNine2;

    @Expose
    @Column(name = "maleFiftyPlus2")
    public Long maleFiftyPlus2;

    @Expose
    @Column(name = "femaleFiftyPlus2")
    public Long femaleFiftyPlus2;

    public HTSTSTB7(){
        super();
    }

    public static HTSTSTB7 get(Long id) {
        return new Select().from(HTSTSTB7.class).where("Id = ?", id).executeSingle();
    }

    public static List<HTSTSTB7> getFilesToUpload() {
        return new Select()
                .from(HTSTSTB7.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static List<HTSTSTB7> getAll() {
        return new Select()
                .from(HTSTSTB7.class)
                .orderBy("date_created ASC")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(HTSTSTB7.class).execute();
    }

    @Override
    public String toString() {
        return name;
    }

    public static HTSTSTB7 fromJson(JSONObject jsonObject) {
        HTSTSTB7 i = new HTSTSTB7();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<HTSTSTB7> fromJson(JSONArray jsonArray) {
        ArrayList<HTSTSTB7> list = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            HTSTSTB7 business = HTSTSTB7.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public Long getFemaleIndividuals(){
        return getLong(femaleLessThanTwoMonths) + getLong(femaleTwoToTwelveMonths) +
                getLong(femaleThirteenToTwentyFourMonths) + getLong(femaleTwentyFiveToFiftyNineMonths) +
                getLong(femaleFiveToNine1) + getLong(femaleTenToFourteen1) +
                getLong(femaleFifteenToNineteen1) +
                getLong(femaleTwentyToTwentyFour1) + getLong(femaleTwentyFiveToTwentyNine1) +
                getLong(femaleThirtyToFortyNine1) + getLong(femaleFiftyPlus1);
    }

    public Long getMaleIndividuals(){
        return getLong(maleLessThanTwoMonths) + getLong(maleTwoToTwelveMonths) +
                getLong(maleThirteenToTwentyFourMonths) + getLong(maleTwentyFiveToFiftyNineMonths) +
                getLong(maleFiveToNine1) + getLong(maleTenToFourteen1) +
                getLong(maleFifteenToNineteen1) +
                getLong(maleTwentyToTwentyFour1) + getLong(maleTwentyFiveToTwentyNine1) +
                getLong(maleThirtyToFortyNine1) + getLong(maleFiftyPlus1);
    }

    public Long getFemaleCouples(){
        return AppUtil.getLong(femaleLessThanFifteen) + AppUtil.getLong(femaleFifteenToNineteen2) +
                AppUtil.getLong(femaleTwentyToTwentyFour2) + AppUtil.getLong(femaleTwentyFiveToTwentyNine2) +
                AppUtil.getLong(femaleThirtyToFortyNine2) + AppUtil.getLong(femaleFiftyPlus2);
    }

    public Long getMaleCouples(){
        return AppUtil.getLong(maleLessThanFifteen) + AppUtil.getLong(maleFifteenToNineteen2) +
                AppUtil.getLong(maleTwentyToTwentyFour2) + AppUtil.getLong(maleTwentyFiveToTwentyNine2) +
                AppUtil.getLong(maleThirtyToFortyNine2) + AppUtil.getLong(maleFiftyPlus2);
    }
}
