package de.kitaggmbhtrier.bistro.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KINDERGARTEN_CHILD")
public class KindergartenChild {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Date kigaStart;
    private Date kigaEnd;
    private boolean breakfast;
    private boolean lunch;
    @ManyToOne
    private KindergartenGroup group;

    protected KindergartenChild() {
    }

    public KindergartenChild(String firstName, String lastName, Date kigaStart, Date kigaEnd, boolean breakfast,
            boolean lunch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kigaStart = kigaStart;
        this.kigaEnd = kigaEnd;
        this.breakfast = breakfast;
        this.lunch = lunch;
    }

    @Override
    public String toString() {
        return String.format("KindergartenChild[id=%d, firstName='%s', lastName='%s', group='%s']", id, firstName, lastName, group);
    }

	public void setGroup(KindergartenGroup group) {
		this.group = group;
	}
	
	public boolean getsBreakfast() {
		return breakfast;
	}
	
	public boolean getsLunch() {
		return lunch;
	}
}
