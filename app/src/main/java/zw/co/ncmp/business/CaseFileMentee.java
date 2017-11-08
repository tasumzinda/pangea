package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdhlakama on 1/22/2016.
 */
@Table(name = "case_file_mentee")
public class CaseFileMentee extends Model {

    @Column(name = "mentee_id")
    public Mentee mentee;

    @Column(name = "case_file_id")
    public CaseFile caseFile;

    @Column(name = "serverId")
    public Long serverId;

    public CaseFileMentee() {
        super();
    }

    public static CaseFileMentee get(Long id) {
        return new Select().from(CaseFileMentee.class).where("Id = ?", id).executeSingle();
    }

    public static CaseFileMentee get(Long case_file_id, Long mentee_id) {
        return new Select().from(CaseFileMentee.class)
                .where("case_file_id = ?", case_file_id)
                .where("mentee_id = ?", mentee_id)
                .executeSingle();
    }

    public static List<CaseFileMentee> getAll() {
        return new Select()
                .from(CaseFileMentee.class)
                .execute();
    }

    public static List<CaseFileMentee> getCaseFileMentees(Long id) {
        return new Select()
                .from(CaseFileMentee.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static List<Mentee> getMentees(Long id) {
        List<Mentee> list = new ArrayList<>();
        for (CaseFileMentee caseFile : getCaseFileMentees(id)) {
            list.add(caseFile.mentee);
        }
        return list;
    }

    public static int getCount(Long id) {
        return new Select()
                .distinct()
                .from(CaseFileMentee.class)
                .where("case_file_id = ?", id)
                .count();
    }

    public static void deleteAll(Long id) {
        new Delete().from(CaseFileMentee.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(CaseFileMentee.class)
                .execute();
    }

    @Override
    public String toString() {
        return mentee.toString();
    }
}
