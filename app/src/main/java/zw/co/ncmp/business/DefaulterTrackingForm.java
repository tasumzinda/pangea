package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by tasu
 */
@Table(name = "defaulter_tracking")
public class DefaulterTrackingForm extends Model{

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
    public String physicalAddress;

    @Column
    @Expose
    public String contactDetails;

    @Column
    @Expose
    public Integer oIARTNumber;

    @Column
    @Expose
    public Date dateArtInitiation;

    @Column
    @Expose
    public Date reviewDate;

    @Column
    @Expose
    public Date appointmentDeemedDefaulter;

    @Column
    @Expose
    public String reasonForTracking;

    @Column
    @Expose
    public Date dateOfCall1;

    @Column
    @Expose
    public String call1Outcome;

    @Column
    @Expose
    public Date appointmentDateIfLinkedToCare;

    @Column
    @Expose
    public Date dateOfCall2;

    @Column
    @Expose
    public String call2Outcome;

    @Column
    @Expose
    public Date dateOfCall3;

    @Column
    @Expose
    public String call3Outcome;

    @Column
    @Expose
    public Date dateOfVisit;

    @Column
    @Expose
    public String visitOutcome;

    @Column
    @Expose
    public Date appointmentDateIfLinkedToCare1;

    @Column
    @Expose
    public Date dateVisitDone;

    @Column
    @Expose
    public String visitDoneOutcome;

    @Column
    @Expose
    public Date appointmentDateIfLinkedBackToCare;

    @Column
    @Expose
    public Date dateClientVisitedFacilityIfLinkedToCare;

    public DefaulterTrackingForm(){
        super();
    }

    public static DefaulterTrackingForm findById(Long id){
        return new Select()
                .from(DefaulterTrackingForm.class)
                .where("serverId = ?", id)
                .executeSingle();
    }

    public static List<DefaulterTrackingForm> getAll(){
        return new Select()
                .from(DefaulterTrackingForm.class)
                .execute();
    }
}
