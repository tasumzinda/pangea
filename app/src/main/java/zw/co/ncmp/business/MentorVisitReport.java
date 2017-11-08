package zw.co.ncmp.business;

import android.util.Log;
import android.widget.EditText;

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
import java.util.List;

/**
 * Created by tdhlakama on 10/16/2015.
 */
@Table(name = "mentor_visit_report")
public class MentorVisitReport extends Model {

    @Column(name = "case_file_id")
    public CaseFile caseFile;

    @SerializedName("mentorShipFocusAreas")
    @Expose
    public List<MentorShipFocusArea> focusAreas = new ArrayList<>();

    @Expose
    @Column(name = "hours")
    public Integer hours;

    @Expose
    @Column(name = "minutes")
    public Integer minutes;

    @Expose
    @Column(name = "comments")
    public String comments;

    @Expose
    @Column(name = "others")
    public String others;

    @SerializedName("actionTaken")
    @Expose
    @Column(name = "action_taken")
    public String action_taken;

    @Expose
    @Column(name = "observation")
    public String observation;

    @Expose
    @Column(name = "recommendations")
    public String recommendations;

    @Expose
    @Column(name = "serverId")
    public Long serverId;

    public MentorVisitReport() {
        super();
    }

    public static MentorVisitReport get(Long id) {
        return new Select().from(MentorVisitReport.class).where("Id = ?", id).executeSingle();
    }

    public static List<MentorVisitReport> getAll() {
        return new Select()
                .from(MentorVisitReport.class)
                .execute();
    }

    public static List<MentorVisitReport> getVisitsByCaseFile(Long id) {
        return new Select()
                .from(MentorVisitReport.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static int getCount(Long id) {
        return new Select()
                .distinct()
                .from(MentorVisitReport.class)
                .where("case_file_id = ?", id)
                .count();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(MentorVisitReport.class)
                .count();
    }

    public static List<MentorVisitReport> getFilesToUpload() {
        return new Select()
                .from(MentorVisitReport.class)
                .where("serverId is null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(MentorVisitReport.class).execute();
    }

    public static final TypeToken<List<MentorVisitReport>> LIST =
            new TypeToken<List<MentorVisitReport>>() {
            };

    @Override
    public String toString() {
        return " " + caseFile.dateCreated;
    }

    public static MentorVisitReport fromJson(JSONObject jsonObject) {
        MentorVisitReport i = new MentorVisitReport();
        try {
            i.serverId = jsonObject.getLong("id");
            i.hours = jsonObject.getInt("hours");
            i.minutes = jsonObject.getInt("minutes");
            i.comments = jsonObject.getString("comments");
            i.observation = jsonObject.getString("observation");
            i.others = jsonObject.getString("others");
            i.recommendations = jsonObject.getString("recommendations");
            i.action_taken = jsonObject.getString("actionTaken");
            i.caseFile = CaseFile.getCaseFile(Long.valueOf(jsonObject.getString("case_file_id")));
            //qualiification
//            JSONObject item = jsonObject.getJSONObject("mentorShipFocusArea");
//            i.mentorShipFocusArea = MentorShipFocusArea.getFocusArea(Long.valueOf(item.getString("id")));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<MentorVisitReport> fromJson(JSONArray jsonArray) {
        ArrayList<MentorVisitReport> list = new ArrayList<MentorVisitReport>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            MentorVisitReport business = MentorVisitReport.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }


}
