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
import zw.co.ncmp.business.util.YesNo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tdhla on 10/25/2016.
 */
@Table(name = "supplementary_indicator_form")
public class SupplementaryIndicatorForm extends Model {

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
    @Column
    public Long estimatedCatchmentPopulation;

    @Expose
    @Column
    public Long numberOfPreARTPatients;

    @Expose
    @Column
    public Long numberOfCLFsDeployed;

    @Expose
    @Column
    public Date dateCLFsDeployed;

    @Expose
    @Column
    public Long numberOfActiveCARGs;

    @Expose
    @Column
    public Long numberOfActiveCARGMembers;

    @Expose
    @Column
    public Long numberOfCARGsFormedThisMonth;

    @Expose
    @Column
    public Long numberOfCARGsFormedToDate;

    @Expose
    @Column
    public Long numberOfCATSSupportersDeployed;

    @Expose
    @Column
    public Date dateCATSDeployed;

    @Expose
    @Column
    public Long numberOfActiveAdolescentSupportGroups;

    @Expose
    @Column
    public Long numberOfActiveAdolescentSupportGroupMembers;

    @Expose
    @Column
    public YesNo areYouImplementingDefaulterTracking;

    @Expose
    @Column
    public Date dateDefaulterTrackingImplemented;

    @Expose
    @Column
    public YesNo areYouImplementingIndexTesting;

    @Expose
    @Column
    public Date dateIndexTestingImplemented;

    @Expose
    @Column
    public YesNo areYouImplementingRetestPriorToARTInitiation;

    @Expose
    @Column
    public Date dateRetestPriorToARTInitiationImplemented;

    @Expose
    @Column
    public YesNo doesFacilityHaveStaticHTSHRH;

    @Expose
    @Column
    public Date dateStaticHTSHRSDeployed;

    @Expose
    @Column
    public YesNo doesFacilityHaveStaticTXNEWHRH;

    @Expose
    @Column
    public Date dateStaticTXNEWHRHDeployed;

    @Expose
    @Column
    public YesNo doesFacilityProvideMultiMonthDrugDispensing;

    @Expose
    @Column
    public Date dateMultiMonthDrugDispensingStarted;

    @Expose
    @Column
    public YesNo doesFacilityHaveFunctionalHealthCentreCommittee;

    @Expose
    @Column
    public YesNo doesFacilityHaveFunctionalQualityImprovementCommittee;

    @Expose
    @Column
    public YesNo doesFacilityHaveQualityImprovementProject;

    @Expose
    @Column
    public Long numberOfOPDPatientsSeenInLastMonth;

    @Expose
    @Column
    public Long numberOfOPDPatientsWithKnownHIVStatusOnEntry;

    @Expose
    @Column
    public Long numberOfOPDPatientsTestedForHIVInLastMonth;

    @Expose
    @Column
    public Long numberOfOPDPatientsTestedHIVPositiveInLastMonth;

    @Expose
    @Column
    public Long numberOfSTIPatientsSeenInLastMonth;

    @Expose
    @Column
    public Long numberOfSTIPatientsWithKnownHIVStatusOnEntry;

    @Expose
    @Column
    public Long numberOfSTIPatientsTestedForHIVInLastMonth;

    @Expose
    @Column
    public Long numberOfSTIPatientsTestedHIVPositiveInLastMonth;

    @Expose
    @Column
    public Long numberOfInpatientPatientsSeenInLastMonth;

    @Expose
    @Column
    public Long numberOfInpatientPatientsWithKnownHIVStatusOnEntry;

    @Expose
    @Column
    public Long numberOfInpatientPatientsTestedForHIVInLastMonth;

    @Expose
    @Column
    public Long numberOfInpatientPatientsTestedHIVPositiveInLastMonth;

    @Expose
    @Column
    public Long numberOfClientsWithDocumentedCompletedReferralCycle;

    @Expose
    @Column
    public Long numberOfClientsWithDocumentedCompletedReferralCycleFromFacilityToCommunity;

    @Expose
    @Column
    public Long numberOfClientsWithDocumentedCompletedReferralCycleFromCommunityToFacility;

    @Expose
    @Column
    public Long numberOfClientsWhoDefaultedART;

    @Expose
    @Column
    public Long numberOfClientsWhoWereFollowedAndHaveADocumentedOutcome;

    @Expose
    @Column
    public Long numberOfEIDResultsReceivedAndSuccessfullyIssuedToCaregivers;

    @Expose
    @Column
    public Long numberOfHIVInfectedInfantsTrackedAndLinkedBackToCare;

    @Expose
    @Column
    public Long numberOfVillageHealthWorkersWorkingWithITECH;

    @Column(name = "date_submitted", notNull = false)
    public Date dateSubmitted;

    public SupplementaryIndicatorForm() {
        super();
    }

    public static SupplementaryIndicatorForm get(Long id) {
        return new Select().from(SupplementaryIndicatorForm.class).where("Id = ?", id).executeSingle();
    }

    public static SupplementaryIndicatorForm getSupplementaryIndicatorForm(Long id) {
        return new Select().from(SupplementaryIndicatorForm.class).where("serverId = ?", id).executeSingle();
    }

    public static List<SupplementaryIndicatorForm> getAll() {
        return new Select()
                .from(SupplementaryIndicatorForm.class)
                .orderBy("name ASC")
                .execute();
    }

    public static int getCount() {
        return new Select()
                .distinct()
                .from(SupplementaryIndicatorForm.class)
                .count();
    }

    public static List<SupplementaryIndicatorForm> getFilesToUpload() {
        return new Select()
                .from(SupplementaryIndicatorForm.class)
                .where("serverId is null")
                .where("date_submitted is not null")
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(SupplementaryIndicatorForm.class).execute();
    }

    public static SupplementaryIndicatorForm fromJson(JSONObject jsonObject) {
        SupplementaryIndicatorForm i = new SupplementaryIndicatorForm();
        try {
            i.serverId = jsonObject.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return i;
    }

    public static ArrayList<SupplementaryIndicatorForm> fromJson(JSONArray jsonArray) {
        ArrayList<SupplementaryIndicatorForm> list = new ArrayList<SupplementaryIndicatorForm>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = null;
            try {
                item = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            SupplementaryIndicatorForm business = SupplementaryIndicatorForm.fromJson(item);
            if (business != null) {
                list.add(business);
            }
        }

        return list;
    }

    public static final TypeToken<List<SupplementaryIndicatorForm>> LIST =
            new TypeToken<List<SupplementaryIndicatorForm>>() {
            };


    @Override
    public String toString() {
        return name;
    }
}
