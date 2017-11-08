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
@Table(name = "case_file_mentor")
public class CaseFileMentor extends Model {

    @Column(name = "mentor_id")
    public Mentor mentor;

    @Column(name = "case_file_id")
    public CaseFile caseFile;

    @Column(name = "serverId")
    public Long serverId;

    public CaseFileMentor() {
        super();
    }

    public static CaseFileMentor get(Long id) {
        return new Select().from(CaseFileMentor.class).where("Id = ?", id).executeSingle();
    }

    public static CaseFileMentor get(Long case_file_id, Long mentor_id) {
        return new Select().from(CaseFileMentor.class)
                .where("case_file_id = ?", case_file_id)
                .where("mentor_id = ?", mentor_id)
                .executeSingle();
    }


    public static List<CaseFileMentor> getAll() {
        return new Select()
                .from(CaseFileMentor.class)
                .execute();
    }

    public static List<CaseFileMentor> getCaseFileMentors(Long id) {
        return new Select()
                .from(CaseFileMentor.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static List<Mentor> getMentors(Long id) {
        List<Mentor> list = new ArrayList<>();
        for (CaseFileMentor caseFile : getCaseFileMentors(id)) {
            list.add(caseFile.mentor);
        }
        return list;
    }

    public static int getCount(Long id) {
        return new Select()
                .distinct()
                .from(CaseFileMentor.class)
                .where("case_file_id = ?", id)
                .count();
    }

    public static void deleteAll(Long id) {
        new Delete().from(CaseFileMentor.class)
                .where("case_file_id = ?", id)
                .execute();
    }

    public static void deleteAll() {
        new Delete().from(CaseFileMentor.class).execute();
    }

    @Override
    public String toString() {
        return mentor.toString();
    }
}
