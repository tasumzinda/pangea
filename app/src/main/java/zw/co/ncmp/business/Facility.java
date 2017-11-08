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
@Table(name = "facility")
public class Facility extends Model {

    public Long id;
    @Expose
    @Column(name = "name", unique = true)
    public String name;
    @Column(name = "facility_code", notNull = false)
    public String facilityCode;
    @Column(name = "contact_name", notNull = false)
    public String contactName;
    @Column(name = "contact_mobile_number", notNull = false)
    public String contactMobileNumber;
    @Column(name = "contact_email",  notNull = false)
    public String contactEmail;
    @Column(name = "mentor_id", notNull = false)
    public Mentor mentor;
    @Expose
    @SerializedName("id")
    @Column(name = "serverId", notNull = false, unique = true)
    public Long serverId;

    public Facility() {
        super();
    }

    public Facility(String name) {
        super();
        this.name = name;
    }

    public static Facility get(Long id) {
        return new Select().from(Facility.class).where("Id = ?", id).executeSingle();
    }

    public static Facility getFacility(Long id) {
        return new Select().from(Facility.class).where("serverId = ?", id).executeSingle();
    }

    public static List<Facility> getAll() {
        return new Select()
                .from(Facility.class)
                .orderBy("name ASC")
                .execute();
    }

    public static List<Facility> getAll(Long id) {
        return new Select()
                .from(Facility.class)
                .where("mentor_id = ?", id)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(Facility.class)
                .count();
    }

    public static void deleteAll() {
        new Delete().from(Facility.class).execute();
    }

    public static Facility fromJson(JSONObject jsonObject) {
        Facility i = new Facility();
        try {
            i.serverId = jsonObject.getLong("id");
            i.name =  jsonObject.getString("name");
            i.facilityCode =jsonObject.getString("facilityCode");
            i.contactName = jsonObject.getString("contactName");
            i.contactMobileNumber =jsonObject.getString("contactMobileNumber");
            i.contactEmail = jsonObject.getString("contactEmail");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<Facility> fromJson(JSONArray jsonArray) {
        ArrayList<Facility> list = new ArrayList<Facility>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Error", "Errror");
                continue;
            }

            Facility business = Facility.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<Facility>> LIST =
            new TypeToken<List<Facility>>() {
            };

    @Override
    public String toString() {
        return name;
    }
}
