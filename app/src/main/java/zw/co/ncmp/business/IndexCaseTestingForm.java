package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import zw.co.ncmp.business.util.Gender;
import zw.co.ncmp.business.util.HIVResult;
import zw.co.ncmp.business.util.YesNo;

import java.util.Date;
import java.util.List;

/**
 * Created by tasu
 */
@Table(name = "index_case_testing")
public class IndexCaseTestingForm extends Model{

    @SerializedName("id")
    @Column
    @Expose
    public Long serverId;

    @Column
    @Expose
    public String firstNameOfIndex;

    @Column
    @Expose
    public String lastNameOfIndex;

    @Column
    @Expose
    public Integer indexOIARTNumber;

    @Column
    @Expose
    public YesNo initiatedOnART;

    @Column
    @Expose
    public String reasonForNotBeingInitiated;

    @Column
    @Expose
    public YesNo consentForListedContacts;

    @Column
    @Expose
    public String preferredPlaceForContactsToBeTested;

    @Column
    @Expose
    public Date appointmentDateForContact;

    @Column
    @Expose
    public String nameOfContact;

    @Column
    @Expose
    public String relationShipToIndex;

    @Column
    @Expose
    public Integer age;

    @Column
    @Expose
    public Gender gender;

    @Column
    @Expose
    public String contactAddress;

    @Column
    @Expose
    public Long indexContactNumber;

    @Column
    @Expose
    public Date dateCalled;

    @Column
    @Expose
    public String callOutcome;

    @Column
    @Expose
    public Date dateVisited;

    @Column
    @Expose
    public String visitOutcome;

    @Column
    @Expose
    public Date contactTestedDate;

    @Column
    @Expose
    public String locationOfTest;

    @Column
    @Expose
    public HIVResult hivResult;

    @Column
    @Expose
    public YesNo enrolledIntoCare;

    public IndexCaseTestingForm(){
        super();
    }

    public static IndexCaseTestingForm findById(Long id){
        return new Select()
                .from(IndexCaseTestingForm.class)
                .where("serverId = ?", id)
                .executeSingle();
    }

    public static List<IndexCaseTestingForm> getAll(){
        return new Select()
                .from(IndexCaseTestingForm.class)
                .execute();
    }
}
