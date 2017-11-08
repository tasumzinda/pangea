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
@Table(name = "mentee")
public class Mentee extends Model {

    @Expose
    @Column(name = "first_name")
    public String firstName;

    @Expose
    @Column(name = "last_name")
    public String lastName;

    @Column(name = "middle_name")
    public String middleName;

    @Expose
    @Column(name = "national_id")
    public String nationalId;

    @Column(name = "date_of_birth", notNull = false)
    public Date dateOfBirth;

    @Expose
    @SerializedName("dateofBirth")
    public String serverDateOfBirth;

    @Expose
    @Column(name = "qualification_id", notNull = false)
    public Qualification qualification;

    @Expose
    @Column(name = "designation_id", notNull = false)
    public Designation designation;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    @Expose
    @Column(name = "mobile_number")
    public String mobileNumber;

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    public Mentee() {
        super();
    }

    public static Mentee get(Long id) {
        return new Select().from(Mentee.class).where("Id = ?", id).executeSingle();
    }

    public static Mentee getMentee(Long id) {
        return new Select().from(Mentee.class).where("serverId = ?", id).executeSingle();
    }

    public static List<Mentee> getAll() {
        return new Select()
                .from(Mentee.class)
                .orderBy("last_name ASC")
                .orderBy("first_name ASC")
                .execute();
    }

    public static List<Mentee> getMentees(Long id) {
        return new Select()
                .from(Mentee.class)
                .where("facility_id = ?", id)
                .orderBy("last_name ASC")
                .orderBy("first_name ASC")
                .execute();
    }

    public static List<Mentee> getFilesToUpload() {
        return new Select()
                .from(Mentee.class)
                .where("serverId is null")
                .execute();
    }

    public static int getMenteeCount(Long id) {
        return new Select()
                .distinct()
                .from(Mentee.class)
                .where("facility_id = ?", id)
                .count();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(Mentee.class)
                .count();
    }

    public static void deleteAll() {
        new Delete().from(Mentee.class).execute();
    }

    public static final TypeToken<List<Mentee>> LIST =
            new TypeToken<List<Mentee>>() {
            };

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    public static Mentee fromJson(JSONObject jsonObject) {
        Mentee i = new Mentee();
        try {
            i.serverId = jsonObject.getLong("id");
            i.firstName = jsonObject.getString("firstName");
            i.lastName = jsonObject.getString("lastName");
            i.nationalId = jsonObject.getString("nationalId");
            i.mobileNumber = jsonObject.getString("mobileNumber");
            String dateofBirth = jsonObject.getString("dateofBirth");
            if (dateofBirth.equals(null) || !dateofBirth.isEmpty()) {
                i.dateOfBirth = AppUtil.getSQLDate(dateofBirth);
            }
            //qualiification
            JSONObject item = jsonObject.getJSONObject("qualification");
            i.qualification = Qualification.getQualification(Long.valueOf(item.getString("id")));
            //designation
            try {
                item = jsonObject.getJSONObject("designation");
                i.designation = Designation.getDesignation(Long.valueOf(item.getString("id")));
            } catch (Exception e) {
                Log.d("", "Designation Null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<Mentee> fromJson(JSONArray jsonArray) {
        ArrayList<Mentee> list = new ArrayList<Mentee>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Mentee business = Mentee.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }
}
