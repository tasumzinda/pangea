package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdhlakama on 8/12/2016.
 */
@Table(name = "visit_report_focus_area")
public class VisitReportFocusArea extends Model {

    @Column(name = "mentor_visit_report_id")
    public MentorVisitReport mentorVisitReport;

    @Column(name = "focus_area_id")
    public MentorShipFocusArea mentorShipFocusArea;

    public VisitReportFocusArea() {
        super();
    }

    public static VisitReportFocusArea get(Long id) {
        return new Select().from(VisitReportFocusArea.class).where("Id = ?", id).executeSingle();
    }

    public static VisitReportFocusArea get(Long mentor_visit_report_id, Long focus_area_id) {
        return new Select().from(VisitReportFocusArea.class)
                .where("mentor_visit_report_id = ?", mentor_visit_report_id)
                .where("focus_area_id = ?", focus_area_id)
                .executeSingle();
    }

    public static List<VisitReportFocusArea> getVisitReportFocusArea(Long id) {
        return new Select()
                .from(VisitReportFocusArea.class)
                .where("mentor_visit_report_id = ?", id)
                .execute();
    }

    public static List<MentorShipFocusArea> getMentorShipFocusAreas(Long id) {
        List<MentorShipFocusArea> list = new ArrayList<>();
        for (VisitReportFocusArea focusArea : getVisitReportFocusArea(id)) {
            list.add(focusArea.mentorShipFocusArea);
        }
        return list;
    }

    public static void deleteAll(Long id) {
        new Delete().from(VisitReportFocusArea.class)
                .where("mentor_visit_report_id = ?", id)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(VisitReportFocusArea.class)
                .execute();
    }

    @Override
    public String toString() {
        return mentorShipFocusArea.toString();
    }
}
