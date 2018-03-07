package zw.co.ncmp.network;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import org.json.JSONArray;
import zw.co.ncmp.business.*;
import zw.co.ncmp.util.AppUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @uthor Tasu Muzinda
 */
public class PushService extends IntentService{

    public static final String NOTIFICATION = "zw.co.ncmp";
    private Context context = this;
    public static final String RESULT = "result";
    private int result = Activity.RESULT_CANCELED;
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public PushService() {
        super("PushService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int result = Activity.RESULT_OK;
        final List<Facility> facilities = Facility.getAll();

        final List<Mentee> newMentees = Mentee.getFilesToUpload();
        try {
            if (!newMentees.isEmpty()) {
                for (Mentee mentee : newMentees) {
                    save(run(AppUtil.getMenteesPushUrl(context, mentee.facility.serverId), mentee), mentee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (CaseFile caseFile : CaseFile.getFilesToUpload()) {
                save(run(AppUtil.getPushCaseFileUrl(context), caseFile), caseFile);
            }
        } catch (Exception e) {

            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (FacilityChallenge f : FacilityChallenge.getFilesToUpload()) {
                if (f.caseFile.serverId != null) {
                    delete(run(AppUtil.getPushFacilityChallengeUrl(context, f.caseFile.serverId), f), f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (MentorVisitReport m : MentorVisitReport.getFilesToUpload()) {
                if (m.caseFile.serverId != null) {
                    save(run(AppUtil.getPushMentorVisitReportUrl(context, m.caseFile.serverId), m), m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            FacilityChallenge.deleteServerFiles();
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (Facility facility : facilities) {
                saveServerlFacilityChallenge(runFacilityChallenges(AppUtil.getPullFacilityChallengeUrl(context, facility.serverId), facility));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (ARTForm m : ARTForm.getFilesToUpload()) {
                save(run(AppUtil.getPushARTFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (ScreenForm m : ScreenForm.getFilesToUpload()) {
                save(run(AppUtil.getPushScreenFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();

            result = Activity.RESULT_CANCELED;
        }

        try {
            for (StatForm m : StatForm.getFilesToUpload()) {
                save(run(AppUtil.getPushStatFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (DSDIndividual m : DSDIndividual.getFilesToUpload()) {
                save(run(AppUtil.getPushDSDIndividualUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }


        try {
            for (MonthReportForm m : MonthReportForm.getFilesToUpload()) {
                save(run(AppUtil.getPushMonthFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }


        try {
            for (RegisterForm m : RegisterForm.getFilesToUpload()) {
                save(run(AppUtil.getPushRegisterFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (TXTNew m : TXTNew.getFilesToUpload()) {
                save(run(AppUtil.getPushTXTNewUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }


        try {
            for (PMTCTARTForm m : PMTCTARTForm.getFilesToUpload()) {
                save(run(AppUtil.getPushPMTCTARTFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (PMTCTFOForm m : PMTCTFOForm.getFilesToUpload()) {
                save(run(AppUtil.getPushPMTCTFOFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (SupplementaryIndicatorForm m : SupplementaryIndicatorForm.getFilesToUpload()) {
                save(run(AppUtil.getPushSupplementaryIndicatorFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (TXCURRForm m : TXCURRForm.getFilesToUpload()) {
                save(run(AppUtil.getPushTXCURRFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (TXPVLSForm m : TXPVLSForm.getFilesToUpload()) {
                save(run(AppUtil.getPushTXPVLSFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (TXRETForm m : TXRETForm.getFilesToUpload()) {
                save(run(AppUtil.getPushTXRETFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (HTCTST m : HTCTST.getFilesToUpload()) {
                save(run(AppUtil.getPushHTCTSTReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (PMTCTStat m : PMTCTStat.getFilesToUpload()) {
                save(run(AppUtil.getPushPMTCTStatReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (PMTCTEIDForm m : PMTCTEIDForm.getFilesToUpload()) {
                save(run(AppUtil.getPushPMTCTEIDFormReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (TXNew m : TXNew.getFilesToUpload()) {
                save(run(AppUtil.getPushTXNewReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (CommunityBasedDSD m : CommunityBasedDSD.getFilesToUpload()) {
                save(run(AppUtil.getPushCommBasedDSDReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (DSDIndividualCOP17 m : DSDIndividualCOP17.getFilesToUpload()) {
                save(run(AppUtil.getPushDSDCOP17ReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (COP17TXNew m : COP17TXNew.getFilesToUpload()) {
                save(run(AppUtil.getPushCOP17TXTNewUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            for (HTSEligibilityScreeningForm m : HTSEligibilityScreeningForm.getFilesToUpload()) {
                save(run(AppUtil.getPushHTSEligibilityScreenReportUrl(context, m.facility.serverId), m), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        publishResults(result);
    }

    private void publishResults(int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }

    private String runFacilityChallenges(HttpUrl httpUrl, Facility facility) {
        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        return AppUtil.getResponeBody(client, httpUrl);
    }


    private String run(HttpUrl httpUrl, CaseFile caseFile) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        caseFile.serverCreatedDate = AppUtil.getStringTime(caseFile.dateCreated);
        caseFile.serverSubmittedDate = AppUtil.getStringTime(caseFile.dateSubmitted);
        caseFile.mentees = CaseFileMentee.getMentees(caseFile.getId());
        caseFile.mentors = CaseFileMentor.getMentors(caseFile.getId());
        String json = gson.toJson(caseFile);
        return AppUtil.getResponeBody(client, httpUrl, json);
    }

    private String run(HttpUrl httpUrl, FacilityChallenge facilityChallenge) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        facilityChallenge.serverFollowUpDate = AppUtil.getStringDate(facilityChallenge.follow_up_date);
        facilityChallenge.expectedCompletionDate = AppUtil.getStringDate(facilityChallenge.expected_completion_date);
        if (facilityChallenge.previousChallenge != null && facilityChallenge.previousChallenge.serverId != null) {
            facilityChallenge.parentId = facilityChallenge.previousChallenge.serverId;
        }
        String json = gson.toJson(facilityChallenge);

        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, MentorVisitReport mentorVisitReport) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        mentorVisitReport.focusAreas = VisitReportFocusArea.getMentorShipFocusAreas(mentorVisitReport.getId());
        String json = gson.toJson(mentorVisitReport);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, Mentee mentee) throws IOException {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        mentee.serverDateOfBirth = AppUtil.getStringDate(mentee.dateOfBirth);
        String json = gson.toJson(mentee);

        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, ARTForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, DSDIndividual form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.startDateC = AppUtil.getStringDate(form.startDate);
        form.endDateC = AppUtil.getStringDate(form.endDate);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, COP17TXNew form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.startDateC = AppUtil.getStringDate(form.startDate);
        form.endDateC = AppUtil.getStringDate(form.endDate);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, DSDIndividualCOP17 form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.startDateC = AppUtil.getStringDate(form.startDate);
        form.endDateC = AppUtil.getStringDate(form.endDate);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, MonthReportForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }


    private String run(HttpUrl httpUrl, RegisterForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, TXTNew form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.startDateC = AppUtil.getStringDate(form.startDate);
        form.endDateC = AppUtil.getStringDate(form.endDate);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, ScreenForm form) {
        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, StatForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, PMTCTARTForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, PMTCTFOForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, SupplementaryIndicatorForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.dateCATS = AppUtil.getStringDate(form.dateCATSDeployed);
        form.dateCLFS = AppUtil.getStringDate(form.dateCLFsDeployed);
        form.dateDefaulter = AppUtil.getStringDate(form.dateDefaulterTrackingImplemented);
        form.dateHTS = AppUtil.getStringDate(form.dateStaticHTSHRSDeployed);
        form.dateIndex = AppUtil.getStringDate(form.dateIndexTestingImplemented);
        form.dateMulti = AppUtil.getStringDate(form.dateMultiMonthDrugDispensingStarted);
        form.dateRetest = AppUtil.getStringDate(form.dateRetestPriorToARTInitiationImplemented);
        form.dateTXNEW = AppUtil.getStringDate(form.dateStaticTXNEWHRHDeployed);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, TXCURRForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, TXPVLSForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, TXRETForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, HTCTST form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, PMTCTStat form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, PMTCTEIDForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, TXNew form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, CommunityBasedDSD form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.serverCreatedDate = AppUtil.getStringDate(form.dateCreated);
        form.startDateC = AppUtil.getStringDate(form.startDate);
        form.endDateC = AppUtil.getStringDate(form.endDate);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, Esta3TxNew form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.datec = AppUtil.getStringDate(form.mDate);
        form.dateReg = AppUtil.getStringDate(form.registeredInPreArt);
        form.dateInit = AppUtil.getStringDate(form.dateOfInitiation);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    private String run(HttpUrl httpUrl, HTSEligibilityScreeningForm form) {

        OkHttpClient client = new OkHttpClient();
        client = AppUtil.connectionSettings(client);
        client = AppUtil.getUnsafeOkHttpClient(client);
        client = AppUtil.createAuthenticationData(client, context);
        form.dateC = AppUtil.getStringDate(form.date);
        String json = gson.toJson(form);
        return AppUtil.getResponeBody(client, httpUrl, json);

    }

    public CaseFile save(String data, CaseFile item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String data, FacilityChallenge item) {
        try {
            Long id = Long.valueOf(data);
            item.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MentorVisitReport save(String data, MentorVisitReport item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Mentee save(String data, Mentee item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String saveServerlFacilityChallenge(String data) {
        int i = 0;

        String msg = "Facility Challenges Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<FacilityChallenge> list = FacilityChallenge.fromJson(jsonArray);

            for (FacilityChallenge item : list) {
                FacilityChallenge checkDuplicate = FacilityChallenge.getFacilityChallenge(item.serverId);
                if (checkDuplicate == null) {
                    CaseFile caseFile = CaseFile.getCaseFile(item.caseFile.serverId);
                    if (caseFile != null) {
                        item.caseFile = caseFile;
                    } else {
                        item.caseFile.save();
                    }
                    item.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Sync Failed";
        }
        return msg;
    }

    public ARTForm save(String data, ARTForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HTSEligibilityScreeningForm save(String data, HTSEligibilityScreeningForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ScreenForm save(String data, ScreenForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StatForm save(String data, StatForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HTCTST save(String data, HTCTST item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PMTCTStat save(String data, PMTCTStat item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CommunityBasedDSD save(String data, CommunityBasedDSD item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public COP17TXNew save(String data, COP17TXNew item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PMTCTEIDForm save(String data, PMTCTEIDForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TXNew save(String data, TXNew item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public DSDIndividual save(String data, DSDIndividual item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DSDIndividualCOP17 save(String data, DSDIndividualCOP17 item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MonthReportForm save(String data, MonthReportForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RegisterForm save(String data, RegisterForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TXTNew save(String data, TXTNew item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PMTCTARTForm save(String data, PMTCTARTForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PMTCTFOForm save(String data, PMTCTFOForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public SupplementaryIndicatorForm save(String data, SupplementaryIndicatorForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TXCURRForm save(String data, TXCURRForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public TXPVLSForm save(String data, TXPVLSForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TXRETForm save(String data, TXRETForm item) {
        try {
            Long id = Long.valueOf(data);
            item.serverId = id;
            item.save();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}