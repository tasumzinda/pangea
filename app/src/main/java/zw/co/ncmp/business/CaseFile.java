package zw.co.ncmp.business;

import android.util.Log;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 1/22/2016.
 */
@Table(name = "case_file")
public class CaseFile extends Model {

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    @Column(name = "date_created")
    public Date dateCreated;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    @Column(name = "check_out_date", notNull = false)
    public Date checkOutDate;

    @SerializedName("dateCreated")
    @Expose
    public String serverCreatedDate;

    @SerializedName("dateSubmitted")
    @Expose
    public String serverSubmittedDate;

    @Expose
    @Column(name = "latitude_coordinate_created", notNull = false)
    public Double latitudeCreated;

    @Expose
    @Column(name = "longitude_coordinate_created", notNull = false)
    public Double longitudeCreated;

    @Expose
    @Column(name = "latitude_coordinate_submitted", notNull = false)
    public Double latitudeSubmitted;

    @Expose
    @Column(name = "longitude_coordinate_submitted", notNull = false)
    public Double longitudeSubmitted;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", notNull = false)
    public Long serverId;

    @Expose
    public List<Mentee> mentees;

    @Expose
    public List<Mentor> mentors;

    public CaseFile() {
        super();
    }

    public static CaseFile get(Long id) {
        return new Select().from(CaseFile.class).where("Id = ?", id).executeSingle();
    }

    public static CaseFile getCaseFile(Long id) {
        return new Select().from(CaseFile.class).where("serverId = ?", id).executeSingle();
    }

    public static List<CaseFile> getAll() {
        return new Select()
                .from(CaseFile.class)
                .orderBy("date_created ASC")
                .execute();
    }

    public static List<CaseFile> getCaseFiles(Long id) {
        return new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .orderBy("date_created ASC")
                .execute();
    }

    public static List<CaseFile> getFilesToUpload() {
        return new Select()
                .from(CaseFile.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .orderBy("date_created ASC")
                .execute();
    }

    public static List<CaseFile> getOpenCaseFiles(Long id) {
        return new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .where("date_submitted is null")
                .execute();
    }

    public static boolean caseFileOpen(Long id) {
        int count = new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .where("date_submitted is null")
                .count();
        if (count != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static List<CaseFile> getFilesToUpload(Long id) {
        return new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .where("date_submitted is not null")
                .where("serverId is null")
                .execute();
    }

    public static Integer getNumberOfFilesToUpload(Long id) {
        return new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .where("date_submitted is not null")
                .where("serverId is null")
                .count();
    }

    public static Integer getNumberOfFilesToUpload() {
        return new Select()
                .from(CaseFile.class)
                .where("date_submitted is not null")
                .where("serverId is null")
                .count();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(CaseFile.class)
                .count();
    }

    public static Integer getTotalCaseFilesForFacility(Long id) {
        return new Select()
                .from(CaseFile.class)
                .where("facility_id = ?", id)
                .count();
    }

    public static void deleteCaseFile(Long id) {
        new Delete()
                .from(CaseFile.class)
                .where("id = ?", id)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(CaseFile.class).execute();
    }


    @Override
    public String toString() {
        return facility.name + " - " + AppUtil.getStringDate(dateCreated);
    }

    public static CaseFile fromJson(JSONObject jsonObject) {
        CaseFile i = new CaseFile();
        try {
            i.serverId = Long.valueOf(jsonObject.getLong("id"));
            i.latitudeCreated = jsonObject.getDouble("latitudeCreated");
            i.longitudeCreated = jsonObject.getDouble("longitudeCreated");
            i.latitudeSubmitted = jsonObject.getDouble("latitudeSubmitted");
            i.longitudeSubmitted = jsonObject.getDouble("longitudeSubmitted");
            i.dateCreated = AppUtil.getSQLDate(jsonObject.getString("dateCreated"));
            if (i.dateCreated == null) {
                i.dateCreated = AppUtil.getOIDSQLDate(jsonObject.getString("dateCreated"));
            }
            i.dateSubmitted = AppUtil.getSQLDate(jsonObject.getString("dateSubmitted"));
            if (i.dateSubmitted == null) {
                i.dateSubmitted = AppUtil.getOIDSQLDate(jsonObject.getString("dateSubmitted"));
            }
            JSONObject item = jsonObject.getJSONObject("facility");
            i.facility = Facility.getFacility(Long.valueOf(item.getString("id")));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<CaseFile> fromJson(JSONArray jsonArray) {
        ArrayList<CaseFile> list = new ArrayList<CaseFile>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            CaseFile business = CaseFile.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }
}
