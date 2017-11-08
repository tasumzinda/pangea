package zw.co.ncmp.business;

/**
 * Created by tdhla on 10/25/2016.
 */

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

@Table(name = "pmtctfo_form")
public class PMTCTFOForm extends Model {

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    @Expose
    @Column(name = "facility_id")
    public Facility facility;

    @Column(name = "date_created")
    public Date dateCreated;

    @SerializedName("datec")
    @Expose
    public String serverCreatedDate;

    @Expose
    @Column(name = "name")
    public String name;

    @Expose
    @Column(name = "period_id")
    public Period period;

    @Expose
    @Column(name = "numerator")
    public Long numerator;

    @Expose
    @Column(name = "denominator")
    public Long denominator;

    @Expose
    @Column(name = "hivInfected")
    public Long hivInfected;

    @Expose
    @Column(name = "hivUnInfected")
    public Long hivUnInfected;

    @Expose
    @Column(name = "hivUnknown")
    public Long hivUnknown;

    @Expose
    @Column(name = "died")
    public Long died;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public PMTCTFOForm() {
        super();
    }

    public static PMTCTFOForm get(Long id) {
        return new Select().from(PMTCTFOForm.class).where("Id = ?", id).executeSingle();
    }

    public static PMTCTFOForm getPMTCTFOForm(Long id) {
        return new Select().from(PMTCTFOForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<PMTCTFOForm> getAll() {
        return new Select()
                .from(PMTCTFOForm.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(PMTCTFOForm.class)
                .count();
    }

    public static List<PMTCTFOForm> getFilesToUpload() {
        return new Select()
                .from(PMTCTFOForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(PMTCTFOForm.class).execute();
    }

    public static PMTCTFOForm fromJson(JSONObject jsonObject) {
        PMTCTFOForm i = new PMTCTFOForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<PMTCTFOForm> fromJson(JSONArray jsonArray) {
        ArrayList<PMTCTFOForm> list = new ArrayList<PMTCTFOForm>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PMTCTFOForm business = PMTCTFOForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<PMTCTFOForm>> LIST =
            new TypeToken<List<PMTCTFOForm>>() {
            };


    @Override
    public String toString() {
        return name;
    }


}
