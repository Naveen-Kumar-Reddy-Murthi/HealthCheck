package com.atc.response;

/**
 * 
 * @author Naveen.Reddy
 *
 */
//@JsonIgnoreProperties({"host"})
//@JsonAutoDetect
public class Server {
	
//	private String host;
	private String health;
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getHealth() {
		return health;
	}
	
	public void setHealth(String status) {
		this.health = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Server other = (Server) obj;
		
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ health:");
		builder.append(health);
		builder.append(", type:");
		builder.append(type);
		builder.append("}");
		return builder.toString();
	}
	
	

}
