package de.kitaggmbhtrier.bistro.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private Date kitaStart;
    private Date kitaEnd;
    private boolean breakfast;
    private boolean lunch;
    @ManyToOne
    private KindergartenGroup group;
    @ManyToMany
    private List<ChildAttribute> attributes;

    protected KindergartenChild() {
    }

    public KindergartenChild(String firstName, String lastName, Date kitaStart, Date kitaEnd, boolean breakfast,
            boolean lunch) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kitaStart = kitaStart;
        this.kitaEnd = kitaEnd;
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
	
	public boolean getBreakfast() {
		return breakfast;
	}
	
	public boolean getLunch() {
		return lunch;
	}

	public KindergartenGroup getGroup() {
		return group;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<ChildAttribute> getAttributes() {
		return attributes;
	}

	public Date getKitaStart() {
		return kitaStart;
	}

	public void setKitaStart(Date kitaStart) {
		this.kitaStart = kitaStart;
	}

	public Date getKitaEnd() {
		return kitaEnd;
	}

	public void setKitaEnd(Date kitaEnd) {
		this.kitaEnd = kitaEnd;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}

	public Long getId() {
		return id;
	}

	public void setAttributes(List<ChildAttribute> attributes) {
		this.attributes = attributes;
	}
	
}
