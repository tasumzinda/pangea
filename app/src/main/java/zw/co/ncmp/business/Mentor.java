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

/**
 * Created by tdhlakama on 10/16/2015.
 */
@Table(name = "mentor")
public class Mentor extends Model {

    @Expose
    @Column(name = "first_name")
    public String firstName;

    @Expose
    @Column(name = "last_name")
    public String lastName;

    @Expose
    @Column(name = "middle_name")
    public String middleName;

    @Expose
    @Column(name = "national_id")
    public String nationalId;

    @Expose
    @Column(name = "email")
    public String email;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    @Expose
    @Column(name = "mobile_number")
    public String mobileNumber;

    @Expose
    @Column(name = "mentor_role")
    public String mentorRole;

    public Mentor() {
        super();
    }

    public Mentor(String email) {
        super();
        this.email = email;
    }

    public static Mentor get(Long id) {
        return new Select().from(Mentor.class).where("Id = ?", id).executeSingle();
    }

    public static Mentor getMentor(Long id) {
        return new Select().from(Mentor.class).where("serverId = ?", id).executeSingle();
    }

    public static List<Mentor> getAll() {
        return new Select()
                .from(Mentor.class)
                .orderBy("last_name ASC")
                .orderBy("first_name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(Mentor.class)
                .count();
    }

    public static void deleteAll() {
        new Delete().from(Mentor.class).execute();
    }

    public static Mentor fromJson(JSONObject jsonObject) {
        Mentor i = new Mentor();
        try {
            i.serverId = jsonObject.getLong("id");
            i.firstName = jsonObject.getString("firstName");
            i.lastName = jsonObject.getString("lastName");
            i.email = jsonObject.getString("email");
            i.nationalId = jsonObject.getString("nationalId");
            i.mobileNumber = jsonObject.getString("mobileNumber");
            i.mentorRole = jsonObject.getString("mentorRole");
         } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<Mentor> fromJson(JSONArray jsonArray) {
        ArrayList<Mentor> list = new ArrayList<Mentor>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Mentor business = Mentor.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<Mentor>> LIST =
            new TypeToken<List<Mentor>>() {
            };

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
