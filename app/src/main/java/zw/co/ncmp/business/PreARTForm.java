package zw.co.ncmp.business;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import zw.co.ncmp.business.util.ClientServices;
import zw.co.ncmp.business.util.Gender;
import zw.co.ncmp.business.util.ReasonForHIVTest;
import zw.co.ncmp.business.util.YesNo;

import java.util.Date;
import java.util.List;

/**
 * Created by tasu
 */
@Table(name = "pre_art")
public class PreARTForm extends Model{

    @Expose
    @Column(name = "serverId", unique = true)
    @SerializedName("id")
    public Long serverId;

    @Expose
    @Column
    public String firstName;

    @Expose
    @Column
    public String lastName;

    @Expose
    @Column
    public Integer cardNumber;

    @Expose
    @Column
    public Date date;

    @Expose
    @Column
    public Facility facility;

    @Expose
    @Column
    public String time;

    @Expose
    @Column
    public Gender gender;

    @Expose
    @Column
    public Integer age;

    @Expose
    @Column
    public ReasonForHIVTest reasonForHIVTest;

    @Column
    @Expose
    public String test;

    @Expose
    @Column
    public String finalResult;

    @Expose
    @Column
    public String entryStream;

    public ClientServices clientServices;

    @Column
    @Expose
    public YesNo inPreArt;

    @Column
    @Expose
    public Date registeredInPreArt;

    @Column
    @Expose
    public YesNo initiatedOnArt;

    @Column
    @Expose
    public Date dateOfInitiation;

    @Column
    @Expose
    public Integer oiArtNumber;

    public PreARTForm(){
        super();
    }

    public static PreARTForm findByServerId(Long serverId){
        return new Select()
                .from(PreARTForm.class)
                .where("serverId = ?", serverId)
                .executeSingle();
    }

    public static List<PreARTForm> getAll(){
        return new Select()
                .from(PreARTForm.class)
                .execute();
    }
}
