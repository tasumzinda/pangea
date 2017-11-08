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
import zw.co.ncmp.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 3/18/2017.
 */
@Table(name = "pmtct_eid")
public class PMTCTEIDForm extends Model {

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
    @Column(name = "positiveZeroToTwo")
    public Long positiveZeroToTwo;

    @Expose
    @Column(name = "positiveTwoToTwelve")
    public Long positiveTwoToTwelve;

    @Expose
    @Column(name = "negativeZeroToTwo")
    public Long negativeZeroToTwo;

    @Expose
    @Column(name = "negativeTwoToTwelve")
    public Long negativeTwoToTwelve;

    @Expose
    @Column(name = "collectedZeroToTwo")
    public Long collectedZeroToTwo;

    @Expose
    @Column(name = "collectedTwoToTwelve")
    public Long collectedTwoToTwelve;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public PMTCTEIDForm() {
        super();
    }

    public static PMTCTEIDForm get(Long id) {
        return new Select().from(PMTCTEIDForm.class).where("Id = ?", id).executeSingle();
    }

    public static PMTCTEIDForm getPMTCTEIDForm(Long id) {
        return new Select().from(PMTCTEIDForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<PMTCTEIDForm> getAll() {
        return new Select()
                .from(PMTCTEIDForm.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(PMTCTEIDForm.class)
                .count();
    }

    public static List<PMTCTEIDForm> getFilesToUpload() {
        return new Select()
                .from(PMTCTEIDForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(PMTCTEIDForm.class).execute();
    }

    public static PMTCTEIDForm fromJson(JSONObject jsonObject) {
        PMTCTEIDForm i = new PMTCTEIDForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<PMTCTEIDForm> fromJson(JSONArray jsonArray) {
        ArrayList<PMTCTEIDForm> list = new ArrayList<PMTCTEIDForm>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PMTCTEIDForm business = PMTCTEIDForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<PMTCTEIDForm>> LIST =
            new TypeToken<List<PMTCTEIDForm>>() {
            };


    @Override
    public String toString() {
        return name;
    }

    public Long getNumerator(){
        return AppUtil.getLong(positiveTwoToTwelve) + AppUtil.getLong(positiveZeroToTwo) + AppUtil.getLong(negativeTwoToTwelve)
                + AppUtil.getLong(negativeZeroToTwo) + AppUtil.getLong(collectedTwoToTwelve) + AppUtil.getLong(collectedZeroToTwo);
    }

    public Long getZeroToTwo(){
        return AppUtil.getLong(positiveZeroToTwo) + AppUtil.getLong(negativeZeroToTwo) + AppUtil.getLong(collectedZeroToTwo);
    }

    public Long getTwoToTwelve(){
        return AppUtil.getLong(positiveTwoToTwelve) + AppUtil.getLong(negativeTwoToTwelve) + AppUtil.getLong(collectedTwoToTwelve) ;
    }

}
