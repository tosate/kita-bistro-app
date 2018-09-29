package de.kitaggmbhtrier.bistro.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class NurseryTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    
    @ManyToMany
    private List<KindergartenGroup> kindergartenGroups;

    protected NurseryTeacher() {
    }

    public NurseryTeacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("NurseryTeacher=[id=%d, firstName='%s', lastName='%s', groupsCount=%d]", id, firstName, lastName, this.getKindergartenGroups().size());
    }

	public List<KindergartenGroup> getKindergartenGroups() {
		if(this.kindergartenGroups == null) {
			this.kindergartenGroups = new ArrayList<>();
		}
		return kindergartenGroups;
	}
    
	public void addKindergartenGroup(KindergartenGroup group) {
		this.getKindergartenGroups().add(group);
	}
}
