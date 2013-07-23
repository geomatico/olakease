package co.geomati.olakease.persistence;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Assignment extends AbstractIdentifiableEntity {

	private Date start;

	private int workingHours;

	private float hoursDayDedication;

	@ManyToOne(optional = false)
	private Project project;

	@OneToOne(optional = false)
	private Developer developer;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public int getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(int workingHours) {
		this.workingHours = workingHours;
	}

	public float getHoursDayDedication() {
		return hoursDayDedication;
	}

	public void setHoursDayDedication(float hoursDayDedication) {
		this.hoursDayDedication = hoursDayDedication;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

}
