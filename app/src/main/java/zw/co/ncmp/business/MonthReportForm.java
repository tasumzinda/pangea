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

import zw.co.ncmp.R;
import zw.co.ncmp.util.AppUtil;

import static zw.co.ncmp.util.AppUtil.getLong;

/**
 * Created by tdhlakama on 2/6/2016.
 */
@Table(name = "monthly_report_form")
public class MonthReportForm extends Model {

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    public Province province;

    public District district;

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    @Expose
    @Column(name = "period_id")
    public Period period;

    @Column(name = "date_created")
    public Date dateCreated;

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
    @Column(name = "maleLessThanTwoMonths2")
    public Long maleLessThanTwoMonths2;

    @Expose
    @Column(name = "femaleLessThanTwoMonths2")
    public Long femaleLessThanTwoMonths2;

    @Expose
    @Column(name = "maleTwoToTwelveMonths2")
    public Long maleTwoToTwelveMonths2;

    @Expose
    @Column(name = "femaleTwoToTwelveMonths2")
    public Long femaleTwoToTwelveMonths2;

    @Expose
    @Column(name = "maleThirteenToTwentyFourMonths2")
    public Long maleThirteenToTwentyFourMonths2;

    @Expose
    @Column(name = "femaleThirteenToTwentyFourMonths2")
    public Long femaleThirteenToTwentyFourMonths2;

    @Expose
    @Column(name = "maleTwentyFiveToFiftyNineMonths2")
    public Long maleTwentyFiveToFiftyNineMonths2;

    @Expose
    @Column(name = "femaleTwentyFiveToFiftyNineMonths2")
    public Long femaleTwentyFiveToFiftyNineMonths2;

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

    @Expose
    @Column(name = "maleLessThanFifteen")
    public Long maleLessThanFifteen;

    @Expose
    @Column(name = "femaleLessThanFifteen")
    public Long femaleLessThanFifteen;

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
    public Long maleThirtyToFortyNine3;

    @Column
    @Expose
    public Long femaleThirtyToFortyNine3;

    @Expose
    @Column(name = "maleFiftyPlus3")
    public Long maleFiftyPlus3;

    @Expose
    @Column(name = "femaleFiftyPlus3")
    public Long femaleFiftyPlus3;

    @Expose
    @Column(name = "maleLessThanFifteen1")
    public Long maleLessThanFifteen1;

    @Expose
    @Column(name = "femaleLessThanFifteen1")
    public Long femaleLessThanFifteen1;

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
    public Long maleThirtyToFortyNine4;

    @Column
    @Expose
    public Long femaleThirtyToFortyNine4;

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
    @Column
    public Long maleTwentyFiveToTwentyNine6;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine6;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour6;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour6;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine6;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine6;

    @Column
    @Expose
    public Long maleFortyToFortyFour6;

    @Column
    @Expose
    public Long femaleFortyToFortyFour6;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine6;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine6;

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
    @Column
    public Long maleTwentyFiveToTwentyNine7;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine7;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour7;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour7;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine7;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine7;

    @Column
    @Expose
    public Long maleFortyToFortyFour7;

    @Column
    @Expose
    public Long femaleFortyToFortyFour7;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine7;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine7;

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
    @Column
    public Long maleTwentyFiveToTwentyNine8;

    @Column
    @Expose
    public Long femaleTwentyFiveToTwentyNine8;

    @Column
    @Expose
    public Long maleThirtyToThirtyFour8;

    @Column
    @Expose
    public Long femaleThirtyToThirtyFour8;

    @Column
    @Expose
    public Long maleThirtyFiveToThirtyNine8;

    @Column
    @Expose
    public Long femaleThirtyFiveToThirtyNine8;

    @Column
    @Expose
    public Long maleFortyToFortyFour8;

    @Column
    @Expose
    public Long femaleFortyToFortyFour8;

    @Column
    @Expose
    public Long maleFortyFiveToFortyNine8;

    @Column
    @Expose
    public Long femaleFortyFiveToFortyNine8;

    @Expose
    @Column(name = "maleFiftyPlus8")
    public Long maleFiftyPlus8;

    @Expose
    @Column(name = "femaleFiftyPlus8")
    public Long femaleFiftyPlus8;

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

    @Column(name = "testedMaternity")
    public Long testedMaternity;

    @Column(name = "positiveTestedMaternity")
    public Long positiveTestedMaternity;

    @SerializedName("testedPNC")
    @Expose
    @Column(name = "testedANC")
    public Long testedANC;

    @SerializedName("positiveTestedPNC")
    @Expose
    @Column(name = "positiveTestedANC")
    public Long positiveTestedANC;

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
    @Column(name = "testedPMTCT")
    public Long testedPMTCT;

    @Expose
    @Column(name = "positiveTestedPMTCT")
    public Long positiveTestedPMTCT;

    @Expose
    @Column(name = "testedSTI")
    public Long testedSTI;

    @Expose
    @Column(name = "positiveTestedSTI")
    public Long positiveTestedSTI;

    @SerializedName("testedVIACCentre")
    @Expose
    @Column(name = "testedVIAC")
    public Long testedVIAC;

    @Expose
    @Column(name = "testedVMMC")
    public Long testedVMMC;

    @Expose
    @Column(name = "positiveTestedVMMC")
    public Long positiveTestedVMMC;

    @SerializedName("positiveTestedVIACCentre")
    @Expose
    @Column(name = "positiveTestedVIAC")
    public Long positiveTestedVIAC;

    @Expose
    @Column(name = "testedTB")
    public Long testedTB;

    @Expose
    @Column(name = "positiveTestedTB")
    public Long positiveTestedTB;

    @Expose
    @Column(name = "pmtctEIDP30")
    public Long pmtctEIDP30;

    @Expose
    @Column(name = "pmtctEIDP31")
    public Long pmtctEIDP31;

    @Expose
    @Column(name = "pmtctEIDP4")
    public Long pmtctEIDP4;

    @Expose
    @Column(name = "pmtctEIDP5")
    public Long pmtctEIDP5;

    @Expose
    @Column(name = "pmtctEIDP14")
    public Long pmtctEIDP14;

    @Expose
    @Column(name = "pmtctEIDP17")
    public Long pmtctEIDP17;

    @Expose
    @Column(name = "pmtctSTATP2")
    public Long pmtctSTATP2;

    @Expose
    @Column(name = "pmtctSTATP5")
    public Long pmtctSTATP5;

    @Expose
    @Column(name = "pmtctSTATP13")
    public Long pmtctSTATP13;

    @Expose
    @Column(name = "pmtctSTATP1")
    public Long pmtctSTATP1;

    @Expose
    @Column(name = "pmtctSTATP12")
    public Long pmtctSTATP12;

    @Expose
    @Column(name = "pmtctSTATP12DisaggregationP4")
    public Long pmtctSTATP12DisaggregationP4;

    @Expose
    @Column(name = "pmtctSTATP12DisaggregationP5")
    public Long pmtctSTATP12DisaggregationP5;

    @Expose
    @Column(name = "pmtctSTATP12DisaggregationP14")
    public Long pmtctSTATP12DisaggregationP14;

    @Expose
    @Column(name = "pmtctSTATP12DisaggregationP17")
    public Long pmtctSTATP12DisaggregationP17;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    @Column
    @Expose
    public Long lessThanTwo;

    @Column
    @Expose
    public Long threeToTwelve;

    @Expose
    @Column
    public Long thirteenToTwentyFour;

    @Column
    @Expose
    public Long lessThanTwo1;

    @Column
    @Expose
    public Long threeToTwelve1;

    @Expose
    @Column
    public Long thirteenToTwentyFour1;

    @Column
    @Expose
    public Long lessThanTwo2;

    @Column
    @Expose
    public Long threeToTwelve2;

    @Expose
    @Column
    public Long thirteenToTwentyFour2;

    public static MonthReportForm get(Long id) {
        return new Select().from(MonthReportForm.class).where("Id = ?", id).executeSingle();
    }

    public static MonthReportForm getMonthReportForm(Long id) {
        return new Select().from(MonthReportForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<MonthReportForm> getFilesToUpload() {
        return new Select()
                .from(MonthReportForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static List<MonthReportForm> getAll() {
        return new Select()
                .from(MonthReportForm.class)
                .orderBy("date_created ASC")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(MonthReportForm.class).execute();
    }

    @Override
    public String toString() {
        return "Register Form";
    }

    public static MonthReportForm fromJson(JSONObject jsonObject) {
        MonthReportForm i = new MonthReportForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<MonthReportForm> fromJson(JSONArray jsonArray) {
        ArrayList<MonthReportForm> list = new ArrayList<MonthReportForm>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            MonthReportForm business = MonthReportForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<MonthReportForm>> LIST =
            new TypeToken<List<MonthReportForm>>() {
            };

    public Long maleQuestion1() {
        return getLong(maleLessThanTwoMonths) + getLong(maleTwoToTwelveMonths) +
                getLong(maleThirteenToTwentyFourMonths) + getLong(maleTwentyFiveToFiftyNineMonths) +
                getLong(maleFiveToNine1) + getLong(maleTenToFourteen1) +
                getLong(maleFifteenToNineteen1) +
                getLong(maleTwentyToTwentyFour1) + getLong(maleTwentyFiveToTwentyNine1) +
                getLong(maleThirtyToFortyNine1) + getLong(maleFiftyPlus1);}

    public Long femaleQuestion1() {
        return getLong(femaleLessThanTwoMonths) + getLong(femaleTwoToTwelveMonths) +
                getLong(femaleThirteenToTwentyFourMonths) + getLong(femaleTwentyFiveToFiftyNineMonths) +
                getLong(femaleFiveToNine1) + getLong(femaleTenToFourteen1) +
                getLong(femaleFifteenToNineteen1) +
                getLong(femaleTwentyToTwentyFour1) + getLong(femaleTwentyFiveToTwentyNine1) +
                getLong(femaleThirtyToFortyNine1) + getLong(femaleFiftyPlus1);
    }

    public Long maleQuestion2() {
        return getLong(maleLessThanTwoMonths2) + getLong(maleTwoToTwelveMonths2) +
                getLong(maleThirteenToTwentyFourMonths2) + getLong(maleTwentyFiveToFiftyNineMonths2) +
                getLong(maleFiveToNine2) + getLong(maleTenToFourteen2) +
                getLong(maleFifteenToNineteen2) +
                getLong(maleTwentyToTwentyFour2) + getLong(maleTwentyFiveToTwentyNine2) +
                getLong(maleThirtyToFortyNine2) + getLong(maleFiftyPlus2);}

    public Long femaleQuestion2() {
        return getLong(femaleLessThanTwoMonths2) + getLong(femaleTwoToTwelveMonths2) +
                getLong(femaleThirteenToTwentyFourMonths2) + getLong(femaleTwentyFiveToFiftyNineMonths2) +
                getLong(femaleFiveToNine2) + getLong(femaleTenToFourteen2) +
                getLong(femaleFifteenToNineteen2) +
                getLong(femaleTwentyToTwentyFour2) + getLong(femaleTwentyFiveToTwentyNine2) +
                getLong(femaleThirtyToFortyNine2) + getLong(femaleFiftyPlus2);
    }

    public Long maleQuestion3() {
        return getLong(maleLessThanFifteen) + getLong(maleFifteenToNineteen3) +
                getLong(maleTwentyToTwentyFour3) + getLong(maleTwentyFiveToTwentyNine3) +
                getLong(maleThirtyToFortyNine3) + getLong(maleFiftyPlus3);}

    public Long femaleQuestion3() {
        return getLong(femaleLessThanFifteen) + getLong(femaleFifteenToNineteen3) +
                getLong(femaleTwentyToTwentyFour3) + getLong(femaleTwentyFiveToTwentyNine3) +
                getLong(femaleThirtyToFortyNine3) + getLong(femaleFiftyPlus3);
    }

    public Long maleQuestion4() {
        return getLong(maleLessThanFifteen1) + getLong(maleFifteenToNineteen4) +
                getLong(maleTwentyToTwentyFour4) + getLong(maleTwentyFiveToTwentyNine4) +
                getLong(maleThirtyToFortyNine4) + getLong(maleFiftyPlus4);}

    public Long femaleQuestion4() {
        return getLong(femaleLessThanFifteen1) + getLong(femaleFifteenToNineteen3) +
                getLong(femaleTwentyToTwentyFour4) + getLong(femaleTwentyFiveToTwentyNine4) +
                getLong(femaleThirtyToFortyNine4) + getLong(femaleFiftyPlus4);
    }

    public Long maleQuestion5() {
        return AppUtil.getLong(maleLessThanOne5) + AppUtil.getLong(maleOneToFour5) +
                AppUtil.getLong(maleFiveToNine5) + AppUtil.getLong(maleTenToFourteen5) +
                AppUtil.getLong(maleFifteenToNineteen5) + AppUtil.getLong(maleTwentyToTwentyFour5) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine5) + AppUtil.getLong(maleThirtyToThirtyFour5) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine5) + AppUtil.getLong(maleFortyToFortyFour5) +
                AppUtil.getLong(maleFortyFiveToFortyNine5) + AppUtil.getLong(maleFiftyPlus5);
    }

    public Long femaleQuestion5() {
        return AppUtil.getLong(femaleLessThanOne5) + AppUtil.getLong(femaleOneToFour5) +
                AppUtil.getLong(femaleFiveToNine5) + AppUtil.getLong(femaleTenToFourteen5) +
                AppUtil.getLong(femaleFifteenToNineteen5) + AppUtil.getLong(femaleTwentyToTwentyFour5) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine5) + AppUtil.getLong(femaleThirtyToThirtyFour5) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine5) + AppUtil.getLong(femaleFortyToFortyFour5) +
                AppUtil.getLong(femaleFortyFiveToFortyNine5) + AppUtil.getLong(femaleFiftyPlus5);
    }

    public Long maleQuestion6() {
        return AppUtil.getLong(maleLessThanOne6) + AppUtil.getLong(maleOneToFour6) +
                AppUtil.getLong(maleFiveToNine6) + AppUtil.getLong(maleTenToFourteen6) +
                AppUtil.getLong(maleFifteenToNineteen6) + AppUtil.getLong(maleTwentyToTwentyFour6) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine6) + AppUtil.getLong(maleThirtyToThirtyFour6) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine6) + AppUtil.getLong(maleFortyToFortyFour6) +
                AppUtil.getLong(maleFortyFiveToFortyNine6) + AppUtil.getLong(maleFiftyPlus6);
    }

    public Long femaleQuestion6() {
        return AppUtil.getLong(femaleLessThanOne6) + AppUtil.getLong(femaleOneToFour6) +
                AppUtil.getLong(femaleFiveToNine6) + AppUtil.getLong(femaleTenToFourteen6) +
                AppUtil.getLong(femaleFifteenToNineteen6) + AppUtil.getLong(femaleTwentyToTwentyFour6) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine6) + AppUtil.getLong(femaleThirtyToThirtyFour6) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine6) + AppUtil.getLong(femaleFortyToFortyFour6) +
                AppUtil.getLong(femaleFortyFiveToFortyNine6) + AppUtil.getLong(femaleFiftyPlus6);
    }

    public Long maleQuestion7() {
        return AppUtil.getLong(maleLessThanOne7) + AppUtil.getLong(maleOneToFour7) +
                AppUtil.getLong(maleFiveToNine7) + AppUtil.getLong(maleTenToFourteen7) +
                AppUtil.getLong(maleFifteenToNineteen7) + AppUtil.getLong(maleTwentyToTwentyFour7) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine7) + AppUtil.getLong(maleThirtyToThirtyFour7) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine7) + AppUtil.getLong(maleFortyToFortyFour7) +
                AppUtil.getLong(maleFortyFiveToFortyNine7) + AppUtil.getLong(maleFiftyPlus7);
    }

    public Long femaleQuestion7() {
        return AppUtil.getLong(femaleLessThanOne7) + AppUtil.getLong(femaleOneToFour7) +
                AppUtil.getLong(femaleFiveToNine7) + AppUtil.getLong(femaleTenToFourteen7) +
                AppUtil.getLong(femaleFifteenToNineteen7) + AppUtil.getLong(femaleTwentyToTwentyFour7) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine7) + AppUtil.getLong(femaleThirtyToThirtyFour7) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine7) + AppUtil.getLong(femaleFortyToFortyFour7) +
                AppUtil.getLong(femaleFortyFiveToFortyNine7) + AppUtil.getLong(femaleFiftyPlus7);
    }

    public Long maleQuestion8() {
        return AppUtil.getLong(maleLessThanOne8) + AppUtil.getLong(maleOneToFour8) +
                AppUtil.getLong(maleFiveToNine8) + AppUtil.getLong(maleTenToFourteen8) +
                AppUtil.getLong(maleFifteenToNineteen8) + AppUtil.getLong(maleTwentyToTwentyFour8) +
                AppUtil.getLong(maleTwentyFiveToTwentyNine8) + AppUtil.getLong(maleThirtyToThirtyFour8) +
                AppUtil.getLong(maleThirtyFiveToThirtyNine8) + AppUtil.getLong(maleFortyToFortyFour8) +
                AppUtil.getLong(maleFortyFiveToFortyNine8) + AppUtil.getLong(maleFiftyPlus8);
    }

    public Long femaleQuestion8() {
        return AppUtil.getLong(femaleLessThanOne8) + AppUtil.getLong(femaleOneToFour8) +
                AppUtil.getLong(femaleFiveToNine8) + AppUtil.getLong(femaleTenToFourteen8) +
                AppUtil.getLong(femaleFifteenToNineteen8) + AppUtil.getLong(femaleTwentyToTwentyFour8) +
                AppUtil.getLong(femaleTwentyFiveToTwentyNine8) + AppUtil.getLong(femaleThirtyToThirtyFour8) +
                AppUtil.getLong(femaleThirtyFiveToThirtyNine8) + AppUtil.getLong(femaleFortyToFortyFour8) +
                AppUtil.getLong(femaleFortyFiveToFortyNine8) + AppUtil.getLong(femaleFiftyPlus8);
    }

    public Long getP36Total(){
        return AppUtil.getLong(lessThanTwo) + AppUtil.getLong(threeToTwelve) + AppUtil.getLong(thirteenToTwentyFour);
    }

    public Long getP37Total(){
        return AppUtil.getLong(lessThanTwo1) + AppUtil.getLong(threeToTwelve1) + AppUtil.getLong(thirteenToTwentyFour1);
    }

    public Long getP38Total(){
        return AppUtil.getLong(lessThanTwo2) + AppUtil.getLong(threeToTwelve2) + AppUtil.getLong(thirteenToTwentyFour2);
    }
}
