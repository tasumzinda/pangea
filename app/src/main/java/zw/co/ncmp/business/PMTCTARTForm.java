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
 * Created by tdhla on 10/25/2016.
 */
@Table(name = "pmtctart_form")
public class PMTCTARTForm extends Model {

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
    @Column(name = "artnew")
    public Long artnew;

    @Expose
    @Column(name = "artTreatment")
    public Long artTreatment;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public PMTCTARTForm() {
        super();
    }

    public static PMTCTARTForm get(Long id) {
        return new Select().from(PMTCTARTForm.class).where("Id = ?", id).executeSingle();
    }

    public static PMTCTARTForm getPMTCTARTForm(Long id) {
        return new Select().from(PMTCTARTForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<PMTCTARTForm> getAll() {
        return new Select()
                .from(PMTCTARTForm.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(PMTCTARTForm.class)
                .count();
    }

    public static List<PMTCTARTForm> getFilesToUpload() {
        return new Select()
                .from(PMTCTARTForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(PMTCTARTForm.class).execute();
    }

    public static PMTCTARTForm fromJson(JSONObject jsonObject) {
        PMTCTARTForm i = new PMTCTARTForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<PMTCTARTForm> fromJson(JSONArray jsonArray) {
        ArrayList<PMTCTARTForm> list = new ArrayList<PMTCTARTForm>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PMTCTARTForm business = PMTCTARTForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<PMTCTARTForm>> LIST =
            new TypeToken<List<PMTCTARTForm>>() {
            };


    @Override
    public String toString() {
        return name;
    }


}
