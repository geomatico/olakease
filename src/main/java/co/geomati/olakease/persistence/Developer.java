package co.geomati.olakease.persistence;

import javax.persistence.Entity;

@Entity
public class Developer extends AbstractIdentifiableEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
