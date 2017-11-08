package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tdhlakama on 2/6/2016.
 */
@Table(name = "challenge_status")
public class ChallengeStatus extends Model {

    @Expose
    @Column(name = "name", unique = true)
    public String name;

    @SerializedName("id")
    @Expose
    @Column(name = "serverId", unique = true)
    public Long serverId;

    public static ChallengeStatus get(Long id) {
        return new Select().from(ChallengeStatus.class).where("Id = ?", id).executeSingle();
    }

    public static ChallengeStatus getPendingChallengeStatus() {
        return new Select().from(ChallengeStatus.class).where("name = ?", "Pending").executeSingle();
    }

    public static ChallengeStatus getChallengeStatus(Long id) {
        return new Select().from(ChallengeStatus.class).where("serverId = ?", id).executeSingle();
    }

    public static List<ChallengeStatus> getAll() {
        return new Select()
                .from(ChallengeStatus.class)
                .orderBy("name ASC")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(ChallengeStatus.class).execute();
    }

    @Override
    public String toString() {
        return name;
    }

}
