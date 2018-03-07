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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zw.co.ncmp.business.*;
import zw.co.ncmp.util.AppUtil;

/**
 * Created by tdhlakama on 2/5/2016.
 */
public class PushPullService extends IntentService {

    public static final String NOTIFICATION = "zw.co.ncmp";
    private Context context = this;
    public static final String RESULT = "result";
    private int result = Activity.RESULT_CANCELED;
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public PushPullService() {
        super("PushPullService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int result = Activity.RESULT_OK;

        for (HttpUrl httpUrl : getHttpUrls()) {
            try {
                if (httpUrl.equals(AppUtil.getChallengeStatusUrl(context))) {
                    loadChallengeStatus(AppUtil.run(httpUrl, context));
                }

                if (httpUrl.equals(AppUtil.getActionTakenCategoryUrl(context))) {
                    loadActionTakenCategory(AppUtil.run(httpUrl, context));
                }

                if (httpUrl.equals(AppUtil.getDesignationUrl(context))) {
                    loadDesginations(AppUtil.run(httpUrl, context));
                }
                if (httpUrl.equals(AppUtil.getChallengeUrl(context))) {
                    loadChallenges(AppUtil.run(httpUrl, context));
                }
                if (httpUrl.equals(AppUtil.getFocusAreaUrl(context))) {
                    loadFocusArea(AppUtil.run(httpUrl, context));
                }
                if (httpUrl.equals(AppUtil.getQualificationsUrl(context))) {
                    loadQualifications(AppUtil.run(httpUrl, context));
                }

                if (httpUrl.equals(AppUtil.getPeriodUrl(context))) {
                    loadPeriods(AppUtil.run(httpUrl, context));
                }

                if (httpUrl.equals(AppUtil.getMentorFacilitiesUrl(context))) {
                    loadFactilities(AppUtil.run(httpUrl, context));
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = Activity.RESULT_CANCELED;
            }
        }
        final List<Facility> facilities = Facility.getAll();

        try {
            for (Facility facility : facilities) {
                loadFacilityMentees(AppUtil.run(AppUtil.getFacilityMenteesUrl(context, facility.serverId), context), facility.serverId);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = Activity.RESULT_CANCELED;
        }

        try {
            if (!facilities.isEmpty()) {
                Facility facility = Facility.getAll().get(0);
                loadFacilityMentors(AppUtil.run(AppUtil.getMentorsUrl(context, facility.serverId), context));
            }
        } catch (IOException e) {
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

    public List<HttpUrl> getHttpUrls() {
        List<HttpUrl> static_lists = new ArrayList<>();
        static_lists.add(AppUtil.getMentorFacilitiesUrl(context));
        static_lists.add(AppUtil.getChallengeStatusUrl(context));
        static_lists.add(AppUtil.getChallengeUrl(context));
        static_lists.add(AppUtil.getDesignationUrl(context));
        static_lists.add(AppUtil.getFocusAreaUrl(context));
        static_lists.add(AppUtil.getQualificationsUrl(context));
        static_lists.add(AppUtil.getPeriodUrl(context));
        static_lists.add(AppUtil.getActionTakenCategoryUrl(context));
        return static_lists;
    }

    private String loadQualifications(String data) {
        int i = 0;
        String msg = "Qualifications Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                Qualification checkDuplicate = Qualification.getQualification(staticData.serverId);
                if (checkDuplicate == null) {
                    Qualification item = new Qualification();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Qualification Sync Failed";
        }
        return msg;
    }

    private String loadProvince(String data) {
        int i = 0;
        String msg = "Province Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                Province checkDuplicate = Province.findById(staticData.serverId);
                if (checkDuplicate == null) {
                    Province item = new Province();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Province Sync Failed";
        }
        return msg;
    }

    private String loadPeriods(String data) {
        int i = 0;
        String msg = "Periods Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                Period checkDuplicate = Period.getPeriod(staticData.serverId);
                if (checkDuplicate == null) {
                    Period item = new Period();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Period Sync Failed";
        }
        return msg;
    }

    private String loadChallenges(String data) {
        int i = 0;
        String msg = "Challenges Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                Challenge checkDuplicate = Challenge.getChallenge(staticData.serverId);
                if (checkDuplicate == null) {
                    Challenge item = new Challenge();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Actions Taked Sync Failed";
        }
        return msg;
    }

    private String loadChallengeStatus(String data) {
        int i = 0;
        String msg = "Challenge Status";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                ChallengeStatus checkDuplicate = ChallengeStatus.getChallengeStatus(staticData.serverId);
                if (checkDuplicate == null) {
                    ChallengeStatus item = new ChallengeStatus();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Challenge Status Sync Failed";
        }
        return msg;
    }

    private String loadActionTakenCategory(String data) {
        int i = 0;
        String msg = "Action Taken Category";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                ActionCategory checkDuplicate = ActionCategory.getActionTakenCategory(staticData.serverId);
                if (checkDuplicate == null) {
                    ActionCategory item = new ActionCategory();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Action Taken Category Status Sync Failed";
        }
        return msg;
    }

    private String loadDesginations(String data) {
        int i = 0;
        String msg = "Designations Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                Designation checkDuplicate = Designation.getDesignation(staticData.serverId);
                if (checkDuplicate == null) {
                    Designation item = new Designation();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Designation Sync Failed";
        }
        return msg;
    }

    private String loadFocusArea(String data) {
        int i = 0;
        String msg = "Focus Areas Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<StaticData> list = StaticData.fromJson(jsonArray);
            for (StaticData staticData : list) {
                MentorShipFocusArea checkDuplicate = MentorShipFocusArea.getFocusArea(staticData.serverId);
                if (checkDuplicate == null) {
                    MentorShipFocusArea item = new MentorShipFocusArea();
                    item.serverId = staticData.serverId;
                    item.name = staticData.name;
                    item.save();
                } else {
                    checkDuplicate.name = staticData.name;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Focus Area Sync Failed";
        }
        return msg;
    }

    public String loadFactilities(String data) {
        int i = 0;
        String msg = "Facilities Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<Facility> facilityList = Facility.fromJson(jsonArray);
            for (Facility facility : facilityList) {
                Facility checkDuplicate = Facility.getFacility(facility.serverId);
                if (checkDuplicate == null) {
                    facility.mentor = Mentor.getMentor(AppUtil.getWebUserId(context));
                    facility.save();
                } else {
                    checkDuplicate.name = facility.name;
                    checkDuplicate.contactName = facility.contactName;
                    checkDuplicate.contactEmail = facility.contactEmail;
                    checkDuplicate.contactMobileNumber = facility.contactMobileNumber;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Sync Failed";
        }
        return msg;
    }

    public String loadDistricts(String data) {
        int i = 0;
        String msg = "Districts Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<District> districtList = District.fromJson(jsonArray);
            for (District facility : districtList) {
                District checkDuplicate = District.findById(facility.serverId);
                if (checkDuplicate == null) {
                    facility.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Sync Failed";
        }
        return msg;
    }

    public String loadFacilityMentors(String data) {
        int i = 0;
        String msg = "Mentors Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<Mentor> list = Mentor.fromJson(jsonArray);
            for (Mentor item : list) {
                Mentor checkDuplicate = Mentor.getMentor(item.serverId);
                if (checkDuplicate == null) {
                    item.save();
                } else {
                    checkDuplicate.firstName = item.firstName;
                    checkDuplicate.lastName = item.lastName;
                    checkDuplicate.middleName = item.middleName;
                    checkDuplicate.nationalId = item.nationalId;
                    checkDuplicate.mobileNumber = item.mobileNumber;
                    checkDuplicate.mentorRole = item.mentorRole;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Sync Failed";
        }
        return msg;
    }

    public String loadFacilityMentees(String data, long facility_id) {
        int i = 0;
        String msg = "Mentees Synced";
        try {
            JSONArray jsonArray = new JSONArray(data);
            List<Mentee> list = Mentee.fromJson(jsonArray);
            for (Mentee item : list) {
                Mentee checkDuplicate = Mentee.getMentee(item.serverId);
                if (checkDuplicate == null) {
                    item.facility = Facility.getFacility(facility_id);
                    item.save();
                } else {
                    checkDuplicate.firstName = item.firstName;
                    checkDuplicate.lastName = item.lastName;
                    checkDuplicate.dateOfBirth = item.dateOfBirth;
                    checkDuplicate.middleName = item.middleName;
                    checkDuplicate.nationalId = item.nationalId;
                    checkDuplicate.mobileNumber = item.mobileNumber;
                    checkDuplicate.qualification = item.qualification;
                    checkDuplicate.designation = item.designation;
                    checkDuplicate.save();
                }
                i++;
            }
            msg = msg.concat(" - " + i);

        } catch (Exception e) {
            msg = "Sync Failed";
        }
        return msg;
    }
}

