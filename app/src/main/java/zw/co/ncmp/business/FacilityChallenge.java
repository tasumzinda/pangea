package zw.co.ncmp.business;

import android.util.Log;

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
 * Created by tdhlakama on 10/16/2015.
 */
@Table(name = "facility_challenge")
public class FacilityChallenge extends Model {

    @Column(name = "case_file_id")
    public CaseFile caseFile;

    @Expose
    @Column(name = "detail")
    public String detail;

    @Expose
    @Column(name = "challenge_id")
    public Challenge challenge;

    @Expose
    @Column(name = "expected_outcome_id")
    public String expectedOutcome;

    @Expose
    @Column(name = "action_taken_id")
    public String actionTaken;

    @Expose
    @Column(name = "challenge_status_id")
    public ChallengeStatus challengeStatus;

    @SerializedName("followUpDate")
    @Expose
    public String serverFollowUpDate;

    @Column(name = "follow_up_date")
    public Date follow_up_date;

    @Column(name = "expected_completion_date")
    public Date expected_completion_date;

    @SerializedName("expectedCompletionDate")
    @Expose
    public String expectedCompletionDate;

    @Expose
    @Column(name = "realistic")
    public String realistic;

    @SerializedName("specify")
    @Expose
    @Column(name = "specific")
    public String specific;

    @Expose
    @Column(name = "achievable")
    public String achievable;

    @Expose
    @Column(name = "measurement_method")
    public String measurementMethod;

    @Expose
    @Column(name = "specificDetail")
    public String specificDetail;

    @Expose
    @Column(name = "serverId", notNull = false)
    public Long serverId;

    @Expose
    @SerializedName("parent")
    public Long parentId;

    @Expose
    @Column(name = "others")
    public String others;

    @Expose
    @Column(name = "action_category_id")
    public ActionCategory actionCategory;

    @Column(name = "facility_challenge_id", notNull = false)
    public FacilityChallenge previousChallenge;

    public FacilityChallenge() {
        super();
    }

    public static FacilityChallenge get(Long id) {
        return new Select().from(FacilityChallenge.class).where("Id = ?", id).executeSingle();
    }

    public static FacilityChallenge getFacilityChallenge(Long id) {
        return new Select().from(FacilityChallenge.class).where("serverId = ?", id).executeSingle();
    }

    public static List<FacilityChallenge> getFilesToUpload() {
        return new Select()
                .from(FacilityChallenge.class)
                .where("serverId is null")
                .execute();
    }

    public static List<FacilityChallenge> getAll() {
        return new Select()
                .from(FacilityChallenge.class)
                .execute();
    }

    public static List<FacilityChallenge> getChallenges(Long id) {
        return new Select()
                .from(FacilityChallenge.class)
                .innerJoin(CaseFile.class).on("facility_challenge.case_file_id = case_file.id")
                .where("case_file.facility_id = ?", id)
                .execute();
    }

    public static List<FacilityChallenge> getChallenges(long id, Long challenge_status_id) {
        return new Select()
                .from(FacilityChallenge.class)
                .innerJoin(CaseFile.class).on("facility_challenge.case_file_id = case_file.id")
                .where("case_file.facility_id = ?", id)
                .where("challenge_status_id = ?", challenge_status_id)
                .execute();
    }

    public static List<FacilityChallenge> getChallengesByCaseFile(Long id) {
        return new Select()
                .from(FacilityChallenge.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static int getCount(Long id) {
        return new Select()
                .distinct()
                .from(FacilityChallenge.class)
                .innerJoin(CaseFile.class).on("facility_challenge.case_file_id = case_file.id")
                .where("case_file.facility_id = ?", id)
                .count();
    }

    public static int getCount(Long id, Long challenge_status_id) {
        return new Select()
                .distinct()
                .from(FacilityChallenge.class)
                .innerJoin(CaseFile.class).on("facility_challenge.case_file_id = case_file.id")
                .where("case_file.facility_id = ?", id)
                .where("challenge_status_id = ?", challenge_status_id)
                .count();
    }

    public static int getCountChallengesByCaseFile(Long id) {
        return new Select()
                .from(FacilityChallenge.class)
                .where("case_file_id = ?", id)
                .count();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(FacilityChallenge.class)
                .count();
    }

    public static void deleteAll() {
        new Delete().from(FacilityChallenge.class).execute();
    }

    public static void deleteAll(Long id) {
        new Delete()
                .from(FacilityChallenge.class)
                .innerJoin(CaseFile.class).on("facility_challenge.case_file_id = case_file.id")
                .where("case_file.facility_id = ?", id)
                .where("serverId is not null")
                .execute();
    }

    public static void deleteServerFiles() {
        new Delete()
                .from(FacilityChallenge.class)
                .where("serverId is not null")
                .execute();
    }

    public static final TypeToken<List<FacilityChallenge>> LIST =
            new TypeToken<List<FacilityChallenge>>() {
            };

    @Override
    public String toString() {
        return " " + caseFile.dateCreated;
    }

    public static FacilityChallenge fromJson(JSONObject jsonObject) {
        FacilityChallenge i = new FacilityChallenge();
        try {
            i.serverId = jsonObject.getLong("id");
            i.actionTaken = jsonObject.getString("actionTaken");
            i.expectedOutcome = jsonObject.getString("expectedOutcome");
            i.detail = jsonObject.getString("detail");
            String followUpDate = jsonObject.getString("followUpDate");
            if (followUpDate.equals(null) || !followUpDate.isEmpty()) {
                i.follow_up_date = AppUtil.getSQLDate(followUpDate);
            }
            String dateCompleted = jsonObject.getString("expectedCompletionDate");
            if (dateCompleted.equals(null) || !dateCompleted.isEmpty()) {
                i.expected_completion_date = AppUtil.getSQLDate(dateCompleted);
            }
            i.others = jsonObject.getString("others");
            i.measurementMethod = jsonObject.getString("measurementMethod");
            i.achievable = jsonObject.getString("achievable");
            i.realistic = jsonObject.getString("realistic");
            i.specific = jsonObject.getString("specify");
            i.specificDetail = jsonObject.getString("specificDetail");
            //action taken category
            JSONObject item = null;
            try {
                item = jsonObject.getJSONObject("actionCategory");
                i.actionCategory = ActionCategory.getActionTakenCategory(Long.valueOf(item.getString("id")));
            } catch (Exception e) {

            }
            //challenge
            item = jsonObject.getJSONObject("challenge");
            i.challenge = Challenge.getChallenge(Long.valueOf(item.getString("id")));
            //challengeStatus
            item = jsonObject.getJSONObject("challengeStatus");
            i.challengeStatus = ChallengeStatus.getChallengeStatus(Long.valueOf(item.getString("id")));
            item = jsonObject.getJSONObject("caseFile");
            i.caseFile = CaseFile.fromJson(item);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<FacilityChallenge> fromJson(JSONArray jsonArray) {
        ArrayList<FacilityChallenge> list = new ArrayList<FacilityChallenge>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            FacilityChallenge business = FacilityChallenge.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }
}
