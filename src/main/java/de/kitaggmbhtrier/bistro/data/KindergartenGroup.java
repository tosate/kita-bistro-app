package de.kitaggmbhtrier.bistro.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KINDERGARTEN_GROUP")
public class KindergartenGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    protected KindergartenGroup() {
    }

    public KindergartenGroup(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("KindergartenGroup[id=%d, name='%s']", id, name);
    }
    
	public void addChild(KindergartenChild child) {
		child.setGroup(this);
	}

	public String getName() {
		return name;
	}
	
}
