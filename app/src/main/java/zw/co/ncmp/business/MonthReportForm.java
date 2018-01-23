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

    /*@Expose
    @Column(name = "pmtctSTATP13")
    public Long pmtctSTATP13;*/

    @Expose
    @Column(name = "pmtctSTATP6")
    public Long pmtctSTATP6;

    @Expose
    @Column(name = "pmtctSTATP12")
    public Long pmtctSTATP12;

    @Expose
    @Column(name = "pmtctSTATP4")
    public Long pmtctSTATP4;

    /*@Expose
    @Column(name = "pmtctSTATP12DisaggregationP5")
    public Long pmtctSTATP12DisaggregationP5;*/

    /*@Expose
    @Column(name = "pmtctSTATP12DisaggregationP14")
    public Long pmtctSTATP12DisaggregationP14;*/

    @Expose
    @Column(name = "pmtctSTATP17")
    public Long pmtctSTATP17;


    @Expose
    @Column
    public Long pmtctSTATP1;

    @Expose
    @Column
    public Long pmtctSTATP3;

    @Expose
    @Column
    public Long pmtctSTATP10;

    @Expose
    @Column
    public Long pmtctSTATP11;

    @Expose
    @Column
    public Long pmtctSTATP18;

    @Expose
    @Column
    public Long pmtctSTATP19;

    @Expose
    @Column
    public Long pmtctSTATP20;

    @Expose
    @Column
    public Long pmtctSTATP21;

    @Expose
    @Column
    public Long pmtctSTATP22;

    @Expose
    @Column
    public Long pmtctSTATP23;

    @Expose
    @Column
    public Long pmtctSTATTenToFourteen;

    @Expose
    @Column
    public Long pmtctSTATFifteenToNineteen;

    @Expose
    @Column
    public Long pmtctSTATTwentyToTwentyFour;

    @Expose
    @Column
    public Long pmtctSTATTwentyFiveToTwentyNine;

    @Expose
    @Column
    public Long pmtctSTATThirtyToFortyNine;

    @Expose
    @Column
    public Long pmtctSTATFiftyPlus;

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

    @Expose
    @Column
    public Long maleLessThanTwoMonths9;
    @Expose
    @Column
    public Long femaleLessThanTwoMonths9;
    @Expose
    @Column
    public Long maleTwoToTwelveMonths9;
    @Expose
    @Column
    public Long femaleTwoToTwelveMonths9;
    @Expose
    @Column
    public Long maleThirteenToTwentyFourMonths9;
    @Expose
    @Column
    public Long femaleThirteenToTwentyFourMonths9;
    @Expose
    @Column
    public Long maleTwentyFiveToFiftyNineMonths9;
    @Expose
    @Column
    public Long femaleTwentyFiveToFiftyNineMonths9;
    @Expose
    @Column
    public Long maleFiveToNine9;
    @Expose
    @Column
    public Long femaleFiveToNine9;
    @Expose
    @Column
    public Long maleTenToFourteen9;
    @Expose
    @Column
    public Long femaleTenToFourteen9;
    @Expose
    @Column
    public Long maleFifteenToNineteen9;
    @Expose
    @Column
    public Long femaleFifteenToNineteen9;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour9;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour9;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine9;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine9;
    @Expose
    @Column
    public Long maleThirtyToFortyNine9;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine9;
    @Expose
    @Column
    public Long maleFiftyPlus9;
    @Expose
    @Column
    public Long femaleFiftyPlus9;

    @Expose
    @Column
    public Long maleLessThanTwoMonths10;
    @Expose
    @Column
    public Long femaleLessThanTwoMonths10;
    @Expose
    @Column
    public Long maleTwoToTwelveMonths10;
    @Expose
    @Column
    public Long femaleTwoToTwelveMonths10;
    @Expose
    @Column
    public Long maleThirteenToTwentyFourMonths10;
    @Expose
    @Column
    public Long femaleThirteenToTwentyFourMonths10;
    @Expose
    @Column
    public Long maleTwentyFiveToFiftyNineMonths10;
    @Expose
    @Column
    public Long femaleTwentyFiveToFiftyNineMonths10;
    @Expose
    @Column
    public Long maleFiveToNine10;
    @Expose
    @Column
    public Long femaleFiveToNine10;
    @Expose
    @Column
    public Long maleTenToFourteen10;
    @Expose
    @Column
    public Long femaleTenToFourteen10;
    @Expose
    @Column
    public Long maleFifteenToNineteen10;
    @Expose
    @Column
    public Long femaleFifteenToNineteen10;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour10;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour10;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine10;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine10;
    @Expose
    @Column
    public Long maleThirtyToFortyNine10;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine10;
    @Expose
    @Column
    public Long maleFiftyPlus10;
    @Expose
    @Column
    public Long femaleFiftyPlus10;

    @Expose
    @Column
    public Long maleTenToFourteen11;
    @Expose
    @Column
    public Long femaleTenToFourteen11;
    @Expose
    @Column
    public Long maleFifteenToNineteen11;
    @Expose
    @Column
    public Long femaleFifteenToNineteen11;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour11;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour11;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine11;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine11;
    @Expose
    @Column
    public Long maleThirtyToFortyNine11;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine11;
    @Expose
    @Column
    public Long maleFiftyPlus11;
    @Expose
    @Column
    public Long femaleFiftyPlus11;

    @Expose
    @Column
    public Long maleTenToFourteen12;
    @Expose
    @Column
    public Long femaleTenToFourteen12;
    @Expose
    @Column
    public Long maleFifteenToNineteen12;
    @Expose
    @Column
    public Long femaleFifteenToNineteen12;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour12;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour12;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine12;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine12;
    @Expose
    @Column
    public Long maleThirtyToFortyNine12;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine12;
    @Expose
    @Column
    public Long maleFiftyPlus12;
    @Expose
    @Column
    public Long femaleFiftyPlus12;

    @Expose
    @Column
    public Long maleTenToFourteen13;
    @Expose
    @Column
    public Long femaleTenToFourteen13;
    @Expose
    @Column
    public Long maleFifteenToNineteen13;
    @Expose
    @Column
    public Long femaleFifteenToNineteen13;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour13;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour13;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine13;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine13;
    @Expose
    @Column
    public Long maleThirtyToFortyNine13;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine13;
    @Expose
    @Column
    public Long maleFiftyPlus13;
    @Expose
    @Column
    public Long femaleFiftyPlus13;

    @Expose
    @Column
    public Long maleTenToFourteen14;
    @Expose
    @Column
    public Long femaleTenToFourteen14;
    @Expose
    @Column
    public Long maleFifteenToNineteen14;
    @Expose
    @Column
    public Long femaleFifteenToNineteen14;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour14;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour14;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine14;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine14;
    @Expose
    @Column
    public Long maleThirtyToFortyNine14;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine14;
    @Expose
    @Column
    public Long maleFiftyPlus14;
    @Expose
    @Column
    public Long femaleFiftyPlus14;

    @Expose
    @Column
    public Long maleTenToFourteen15;
    @Expose
    @Column
    public Long femaleTenToFourteen15;
    @Expose
    @Column
    public Long maleFifteenToNineteen15;
    @Expose
    @Column
    public Long femaleFifteenToNineteen15;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour15;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour15;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine15;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine15;
    @Expose
    @Column
    public Long maleThirtyToFortyNine15;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine15;
    @Expose
    @Column
    public Long maleFiftyPlus15;
    @Expose
    @Column
    public Long femaleFiftyPlus15;

    @Expose
    @Column
    public Long maleTenToFourteen16;
    @Expose
    @Column
    public Long femaleTenToFourteen16;
    @Expose
    @Column
    public Long maleFifteenToNineteen16;
    @Expose
    @Column
    public Long femaleFifteenToNineteen16;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour16;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour16;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine16;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine16;
    @Expose
    @Column
    public Long maleThirtyToFortyNine16;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine16;
    @Expose
    @Column
    public Long maleFiftyPlus16;
    @Expose
    @Column
    public Long femaleFiftyPlus16;

    @Expose
    @Column
    public Long maleTenToFourteen17;
    @Expose
    @Column
    public Long femaleTenToFourteen17;
    @Expose
    @Column
    public Long maleFifteenToNineteen17;
    @Expose
    @Column
    public Long femaleFifteenToNineteen17;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour17;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour17;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine17;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine17;
    @Expose
    @Column
    public Long maleThirtyToFortyNine17;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine17;
    @Expose
    @Column
    public Long maleFiftyPlus17;
    @Expose
    @Column
    public Long femaleFiftyPlus17;

    @Expose
    @Column
    public Long maleTenToFourteen18;
    @Expose
    @Column
    public Long femaleTenToFourteen18;
    @Expose
    @Column
    public Long maleFifteenToNineteen18;
    @Expose
    @Column
    public Long femaleFifteenToNineteen18;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour18;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour18;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine18;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine18;
    @Expose
    @Column
    public Long maleThirtyToFortyNine18;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine18;
    @Expose
    @Column
    public Long maleFiftyPlus18;
    @Expose
    @Column
    public Long femaleFiftyPlus18;

    @Expose
    @Column
    public Long maleTenToFourteen19;
    @Expose
    @Column
    public Long femaleTenToFourteen19;
    @Expose
    @Column
    public Long maleFifteenToNineteen19;
    @Expose
    @Column
    public Long femaleFifteenToNineteen19;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour19;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour19;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine19;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine19;
    @Expose
    @Column
    public Long maleThirtyToFortyNine19;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine19;
    @Expose
    @Column
    public Long maleFiftyPlus19;
    @Expose
    @Column
    public Long femaleFiftyPlus19;

    @Expose
    @Column
    public Long maleTenToFourteen20;
    @Expose
    @Column
    public Long femaleTenToFourteen20;
    @Expose
    @Column
    public Long maleFifteenToNineteen20;
    @Expose
    @Column
    public Long femaleFifteenToNineteen20;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour20;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour20;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine20;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine20;
    @Expose
    @Column
    public Long maleThirtyToFortyNine20;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine20;
    @Expose
    @Column
    public Long maleFiftyPlus20;
    @Expose
    @Column
    public Long femaleFiftyPlus20;

    @Expose
    @Column
    public Long maleTenToFourteen21;
    @Expose
    @Column
    public Long femaleTenToFourteen21;
    @Expose
    @Column
    public Long maleFifteenToNineteen21;
    @Expose
    @Column
    public Long femaleFifteenToNineteen21;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour21;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour21;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine21;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine21;
    @Expose
    @Column
    public Long maleThirtyToFortyNine21;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine21;
    @Expose
    @Column
    public Long maleFiftyPlus21;
    @Expose
    @Column
    public Long femaleFiftyPlus21;

    @Expose
    @Column
    public Long maleTenToFourteen22;
    @Expose
    @Column
    public Long femaleTenToFourteen22;
    @Expose
    @Column
    public Long maleFifteenToNineteen22;
    @Expose
    @Column
    public Long femaleFifteenToNineteen22;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour22;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour22;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine22;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine22;
    @Expose
    @Column
    public Long maleThirtyToFortyNine22;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine22;
    @Expose
    @Column
    public Long maleFiftyPlus22;
    @Expose
    @Column
    public Long femaleFiftyPlus22;

    @Expose
    @Column
    public Long maleTenToFourteen23;
    @Expose
    @Column
    public Long femaleTenToFourteen23;
    @Expose
    @Column
    public Long maleFifteenToNineteen23;
    @Expose
    @Column
    public Long femaleFifteenToNineteen23;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour23;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour23;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine23;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine23;
    @Expose
    @Column
    public Long maleThirtyToFortyNine23;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine23;
    @Expose
    @Column
    public Long maleFiftyPlus23;
    @Expose
    @Column
    public Long femaleFiftyPlus23;

    @Expose
    @Column
    public Long maleTenToFourteen24;
    @Expose
    @Column
    public Long femaleTenToFourteen24;
    @Expose
    @Column
    public Long maleFifteenToNineteen24;
    @Expose
    @Column
    public Long femaleFifteenToNineteen24;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour24;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour24;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine24;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine24;
    @Expose
    @Column
    public Long maleThirtyToFortyNine24;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine24;
    @Expose
    @Column
    public Long maleFiftyPlus24;
    @Expose
    @Column
    public Long femaleFiftyPlus24;

    @Expose
    @Column
    public Long maleTenToFourteen25;
    @Expose
    @Column
    public Long femaleTenToFourteen25;
    @Expose
    @Column
    public Long maleFifteenToNineteen25;
    @Expose
    @Column
    public Long femaleFifteenToNineteen25;
    @Expose
    @Column
    public Long maleTwentyToTwentyFour25;
    @Expose
    @Column
    public Long femaleTwentyToTwentyFour25;
    @Expose
    @Column
    public Long maleTwentyFiveToTwentyNine25;
    @Expose
    @Column
    public Long femaleTwentyFiveToTwentyNine25;
    @Expose
    @Column
    public Long maleThirtyToFortyNine25;
    @Expose
    @Column
    public Long femaleThirtyToFortyNine25;
    @Expose
    @Column
    public Long maleFiftyPlus25;
    @Expose
    @Column
    public Long femaleFiftyPlus25;

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

    public Long maleQuestion9() {
        return getLong(maleLessThanTwoMonths9) + getLong(maleTwoToTwelveMonths9) +
                getLong(maleThirteenToTwentyFourMonths9) + getLong(maleTwentyFiveToFiftyNineMonths9) +
                getLong(maleFiveToNine9) + getLong(maleTenToFourteen9) +
                getLong(maleFifteenToNineteen9) +
                getLong(maleTwentyToTwentyFour9) + getLong(maleTwentyFiveToTwentyNine9) +
                getLong(maleThirtyToFortyNine9) + getLong(maleFiftyPlus9);}

    public Long femaleQuestion9() {
        return getLong(femaleLessThanTwoMonths9) + getLong(femaleTwoToTwelveMonths10) +
                getLong(femaleThirteenToTwentyFourMonths9) + getLong(femaleTwentyFiveToFiftyNineMonths9) +
                getLong(femaleFiveToNine9) + getLong(femaleTenToFourteen9) +
                getLong(femaleFifteenToNineteen9) +
                getLong(femaleTwentyToTwentyFour9) + getLong(femaleTwentyFiveToTwentyNine9) +
                getLong(femaleThirtyToFortyNine9) + getLong(femaleFiftyPlus9);
    }

    public Long maleQuestion10() {
        return getLong(maleLessThanTwoMonths10) + getLong(maleTwoToTwelveMonths10) +
                getLong(maleThirteenToTwentyFourMonths10) + getLong(maleTwentyFiveToFiftyNineMonths10) +
                getLong(maleFiveToNine10) + getLong(maleTenToFourteen10) +
                getLong(maleFifteenToNineteen10) +
                getLong(maleTwentyToTwentyFour10) + getLong(maleTwentyFiveToTwentyNine10) +
                getLong(maleThirtyToFortyNine10) + getLong(maleFiftyPlus10);}

    public Long femaleQuestion10() {
        return getLong(femaleLessThanTwoMonths10) + getLong(femaleTwoToTwelveMonths10) +
                getLong(femaleThirteenToTwentyFourMonths10) + getLong(femaleTwentyFiveToFiftyNineMonths10) +
                getLong(femaleFiveToNine10) + getLong(femaleTenToFourteen10) +
                getLong(femaleFifteenToNineteen10) +
                getLong(femaleTwentyToTwentyFour10) + getLong(femaleTwentyFiveToTwentyNine10) +
                getLong(femaleThirtyToFortyNine10) + getLong(femaleFiftyPlus10);
    }

    public Long maleQuestion11() {
        return getLong(maleTenToFourteen11) + getLong(maleFifteenToNineteen11) +
                getLong(maleTwentyToTwentyFour11) + getLong(maleTwentyFiveToTwentyNine11) +
                getLong(maleThirtyToFortyNine11) + getLong(maleFiftyPlus11);}

    public Long femaleQuestion11() {
        return getLong(femaleTenToFourteen11) + getLong(femaleFifteenToNineteen11) +
                getLong(femaleTwentyToTwentyFour11) + getLong(femaleTwentyFiveToTwentyNine11) +
                getLong(femaleThirtyToFortyNine11) + getLong(femaleFiftyPlus11);
    }

    public Long maleQuestion12() {
        return getLong(maleTenToFourteen12) + getLong(maleFifteenToNineteen12) +
                getLong(maleTwentyToTwentyFour12) + getLong(maleTwentyFiveToTwentyNine12) +
                getLong(maleThirtyToFortyNine12) + getLong(maleFiftyPlus12);}

    public Long femaleQuestion12() {
        return getLong(femaleTenToFourteen12) + getLong(femaleFifteenToNineteen12) +
                getLong(femaleTwentyToTwentyFour12) + getLong(femaleTwentyFiveToTwentyNine12) +
                getLong(femaleThirtyToFortyNine12) + getLong(femaleFiftyPlus12);
    }

    public Long maleQuestion13() {
        return getLong(maleTenToFourteen13) + getLong(maleFifteenToNineteen13) +
                getLong(maleTwentyToTwentyFour13) + getLong(maleTwentyFiveToTwentyNine13) +
                getLong(maleThirtyToFortyNine13) + getLong(maleFiftyPlus13);}

    public Long femaleQuestion13() {
        return getLong(femaleTenToFourteen13) + getLong(femaleFifteenToNineteen13) +
                getLong(femaleTwentyToTwentyFour13) + getLong(femaleTwentyFiveToTwentyNine13) +
                getLong(femaleThirtyToFortyNine13) + getLong(femaleFiftyPlus13);
    }

    public Long maleQuestion14() {
        return getLong(maleTenToFourteen14) + getLong(maleFifteenToNineteen14) +
                getLong(maleTwentyToTwentyFour14) + getLong(maleTwentyFiveToTwentyNine14) +
                getLong(maleThirtyToFortyNine14) + getLong(maleFiftyPlus14);}

    public Long femaleQuestion14() {
        return getLong(femaleTenToFourteen14) + getLong(femaleFifteenToNineteen14) +
                getLong(femaleTwentyToTwentyFour14) + getLong(femaleTwentyFiveToTwentyNine14) +
                getLong(femaleThirtyToFortyNine14) + getLong(femaleFiftyPlus14);
    }

    public Long maleQuestion15() {
        return getLong(maleTenToFourteen15) + getLong(maleFifteenToNineteen15) +
                getLong(maleTwentyToTwentyFour15) + getLong(maleTwentyFiveToTwentyNine15) +
                getLong(maleThirtyToFortyNine15) + getLong(maleFiftyPlus15);}

    public Long femaleQuestion15() {
        return getLong(femaleTenToFourteen15) + getLong(femaleFifteenToNineteen15) +
                getLong(femaleTwentyToTwentyFour15) + getLong(femaleTwentyFiveToTwentyNine15) +
                getLong(femaleThirtyToFortyNine15) + getLong(femaleFiftyPlus15);
    }

    public Long maleQuestion16() {
        return getLong(maleTenToFourteen16) + getLong(maleFifteenToNineteen16) +
                getLong(maleTwentyToTwentyFour16) + getLong(maleTwentyFiveToTwentyNine16) +
                getLong(maleThirtyToFortyNine16) + getLong(maleFiftyPlus16);}

    public Long femaleQuestion16() {
        return getLong(femaleTenToFourteen16) + getLong(femaleFifteenToNineteen16) +
                getLong(femaleTwentyToTwentyFour16) + getLong(femaleTwentyFiveToTwentyNine16) +
                getLong(femaleThirtyToFortyNine16) + getLong(femaleFiftyPlus16);
    }

    public Long maleQuestion17() {
        return getLong(maleTenToFourteen17) + getLong(maleFifteenToNineteen17) +
                getLong(maleTwentyToTwentyFour17) + getLong(maleTwentyFiveToTwentyNine17) +
                getLong(maleThirtyToFortyNine17) + getLong(maleFiftyPlus17);}

    public Long femaleQuestion17() {
        return getLong(femaleTenToFourteen17) + getLong(femaleFifteenToNineteen17) +
                getLong(femaleTwentyToTwentyFour17) + getLong(femaleTwentyFiveToTwentyNine17) +
                getLong(femaleThirtyToFortyNine17) + getLong(femaleFiftyPlus17);
    }

    public Long maleQuestion18() {
        return getLong(maleTenToFourteen18) + getLong(maleFifteenToNineteen18) +
                getLong(maleTwentyToTwentyFour18) + getLong(maleTwentyFiveToTwentyNine18) +
                getLong(maleThirtyToFortyNine18) + getLong(maleFiftyPlus18);}

    public Long femaleQuestion18() {
        return getLong(femaleTenToFourteen18) + getLong(femaleFifteenToNineteen18) +
                getLong(femaleTwentyToTwentyFour18) + getLong(femaleTwentyFiveToTwentyNine18) +
                getLong(femaleThirtyToFortyNine18) + getLong(femaleFiftyPlus18);
    }

    public Long maleQuestion19() {
        return getLong(maleTenToFourteen19) + getLong(maleFifteenToNineteen19) +
                getLong(maleTwentyToTwentyFour19) + getLong(maleTwentyFiveToTwentyNine19) +
                getLong(maleThirtyToFortyNine19) + getLong(maleFiftyPlus19);}

    public Long femaleQuestion19() {
        return getLong(femaleTenToFourteen19) + getLong(femaleFifteenToNineteen19) +
                getLong(femaleTwentyToTwentyFour19) + getLong(femaleTwentyFiveToTwentyNine19) +
                getLong(femaleThirtyToFortyNine19) + getLong(femaleFiftyPlus19);
    }

    public Long maleQuestion20() {
        return getLong(maleTenToFourteen20) + getLong(maleFifteenToNineteen20) +
                getLong(maleTwentyToTwentyFour20) + getLong(maleTwentyFiveToTwentyNine20) +
                getLong(maleThirtyToFortyNine20) + getLong(maleFiftyPlus20);
    }

    public Long femaleQuestion20() {
        return getLong(femaleTenToFourteen20) + getLong(femaleFifteenToNineteen20) +
                getLong(femaleTwentyToTwentyFour20) + getLong(femaleTwentyFiveToTwentyNine20) +
                getLong(femaleThirtyToFortyNine20) + getLong(femaleFiftyPlus20);
    }

    public Long maleQuestion21() {
        return getLong(maleTenToFourteen21) + getLong(maleFifteenToNineteen21) +
                getLong(maleTwentyToTwentyFour21) + getLong(maleTwentyFiveToTwentyNine21) +
                getLong(maleThirtyToFortyNine21) + getLong(maleFiftyPlus21);
    }

    public Long femaleQuestion21() {
        return getLong(femaleTenToFourteen21) + getLong(femaleFifteenToNineteen21) +
                getLong(femaleTwentyToTwentyFour21) + getLong(femaleTwentyFiveToTwentyNine21) +
                getLong(femaleThirtyToFortyNine21) + getLong(femaleFiftyPlus21);
    }

    public Long maleQuestion22() {
        return getLong(maleTenToFourteen22) + getLong(maleFifteenToNineteen22) +
                getLong(maleTwentyToTwentyFour22) + getLong(maleTwentyFiveToTwentyNine22) +
                getLong(maleThirtyToFortyNine22) + getLong(maleFiftyPlus22);
    }

    public Long femaleQuestion22() {
        return getLong(femaleTenToFourteen22) + getLong(femaleFifteenToNineteen22) +
                getLong(femaleTwentyToTwentyFour22) + getLong(femaleTwentyFiveToTwentyNine22) +
                getLong(femaleThirtyToFortyNine22) + getLong(femaleFiftyPlus22);
    }

    public Long maleQuestion23() {
        return getLong(maleTenToFourteen23) + getLong(maleFifteenToNineteen23) +
                getLong(maleTwentyToTwentyFour23) + getLong(maleTwentyFiveToTwentyNine23) +
                getLong(maleThirtyToFortyNine23) + getLong(maleFiftyPlus23);
    }

    public Long femaleQuestion23() {
        return getLong(femaleTenToFourteen23) + getLong(femaleFifteenToNineteen23) +
                getLong(femaleTwentyToTwentyFour23) + getLong(femaleTwentyFiveToTwentyNine23) +
                getLong(femaleThirtyToFortyNine23) + getLong(femaleFiftyPlus23);
    }

    public Long maleQuestion24() {
        return getLong(maleTenToFourteen24) + getLong(maleFifteenToNineteen24) +
                getLong(maleTwentyToTwentyFour24) + getLong(maleTwentyFiveToTwentyNine24) +
                getLong(maleThirtyToFortyNine24) + getLong(maleFiftyPlus24);
    }

    public Long femaleQuestion24() {
        return getLong(femaleTenToFourteen24) + getLong(femaleFifteenToNineteen24) +
                getLong(femaleTwentyToTwentyFour24) + getLong(femaleTwentyFiveToTwentyNine24) +
                getLong(femaleThirtyToFortyNine24) + getLong(femaleFiftyPlus24);
    }

    public Long maleQuestion25() {
        return getLong(maleTenToFourteen25) + getLong(maleFifteenToNineteen25) +
                getLong(maleTwentyToTwentyFour25) + getLong(maleTwentyFiveToTwentyNine25) +
                getLong(maleThirtyToFortyNine25) + getLong(maleFiftyPlus25);
    }

    public Long femaleQuestion25() {
        return getLong(femaleTenToFourteen25) + getLong(femaleFifteenToNineteen25) +
                getLong(femaleTwentyToTwentyFour25) + getLong(femaleTwentyFiveToTwentyNine25) +
                getLong(femaleThirtyToFortyNine25) + getLong(femaleFiftyPlus25);
    }
}
