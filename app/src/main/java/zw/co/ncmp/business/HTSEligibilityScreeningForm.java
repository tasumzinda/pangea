package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import zw.co.ncmp.business.util.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Tasu Muzinda
 */
@Table(name = "hts_eligibility_screening")
public class HTSEligibilityScreeningForm extends Model{

    @Expose
    @Column(name = "serverId", unique = true)
    @SerializedName("id")
    public Long serverId;

    @Expose
    @Column
    public String clientName;

    @Expose
    @Column
    public Integer cardNumber;

    @Column
    public Date date;

    @Expose
    public String dateC;

    @Expose
    @Column
    public Facility facility;

    @Expose
    @Column
    public String mTime;

    @Expose
    @Column
    public Gender gender;

    @Expose
    @Column
    public Integer age;

    @Expose
    @Column
    public YesNo eligibleForHIVTest;

    @Expose
    @Column
    public YesNo willingToBeTestedToday;

    @Expose
    @Column
    public String reasonForUnwillingnessToBeTested;

    @Expose
    @Column
    public String reasonForIneligibilityForTesting;

    @Expose
    @Column
    public String servicesBeingSought;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public HTSEligibilityScreeningForm(){
        super();
    }

    public static HTSEligibilityScreeningForm findByServerId(Long serverId){
        return new Select()
                .from(HTSEligibilityScreeningForm.class)
                .where("serverId = ?", serverId)
                .executeSingle();
    }

    public static HTSEligibilityScreeningForm findById(Long Id){
        return new Select()
                .from(HTSEligibilityScreeningForm.class)
                .where("Id = ?", Id)
                .executeSingle();
    }

    public static List<HTSEligibilityScreeningForm> getAll(){
        return new Select()
                .from(HTSEligibilityScreeningForm.class)
                .execute();
    }

    public static List<HTSEligibilityScreeningForm> getFilesToUpload() {
        return new Select()
                .from(HTSEligibilityScreeningForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

}
