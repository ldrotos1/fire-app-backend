package org.fireapp.model.incident;

/**
 * Represents a fuel spill incident
 * 
 * @author Louis Drotos
 *
 */
public class FuelSpillIncident extends Incident {

	private String spillSize;
	private Boolean ignited;
	
	public FuelSpillIncident() {
		
		super();
	}

	public String getSpillSize() {
		return spillSize;
	}

	public void setSpillSize(String spillSize) {
		this.spillSize = spillSize;
	}

	public Boolean getIgnited() {
		return ignited;
	}

	public void setIgnited(Boolean ignited) {
		this.ignited = ignited;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ignited == null) ? 0 : ignited.hashCode());
		result = prime * result + ((spillSize == null) ? 0 : spillSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuelSpillIncident other = (FuelSpillIncident) obj;
		if (ignited == null) {
			if (other.ignited != null)
				return false;
		} else if (!ignited.equals(other.ignited))
			return false;
		if (spillSize == null) {
			if (other.spillSize != null)
				return false;
		} else if (!spillSize.equals(other.spillSize))
			return false;
		return true;
	}
}
