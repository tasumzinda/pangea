package zw.co.ncmp.business;

import android.util.Log;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasu
 */
@Table(name = "district")
public class District extends Model {

    @SerializedName("id")
    @Expose
    @Column
    public Long serverId;

    @Expose
    @Column
    public String name;

    @Expose
    @Column
    public Province province;

    public District(){
        super();
    }

    public static District findById(Long id){
        return new Select()
                .from(District.class)
                .where("serverId = ?", id)
                .executeSingle();
    }

    public static List<District> getAll(){
        return new Select()
                .from(District.class)
                .execute();
    }

    public static List<District> findByProvince(Province province){
        return new Select()
                .from(District.class)
                .where("province = ?", province.getId())
                .execute();
    }

    public static District fromJson(JSONObject jsonObject) {
        District i = new District();
        try {
            i.serverId = jsonObject.getLong("id");
            i.name =  jsonObject.getString("name");
            if( ! jsonObject.isNull("province")){
                JSONObject province = jsonObject.getJSONObject("province");
                i.province = Province.findById(province.getLong("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<District> fromJson(JSONArray jsonArray) {
        ArrayList<District> list = new ArrayList<>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Error", "Errror");
                continue;
            }

            District business = District.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }
}
