package com.atc.response;

import java.util.List;


/**
 * 
 * @author Naveen.Reddy
 *
 */

public class Response {
	
	private String health;
	
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	private List<Server> hostList;

	public List<Server> getHostList() {
		return hostList;
	}

	public void setHostList(List<Server> hostList) {
		this.hostList = hostList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		result = prime * result + ((hostList == null) ? 0 : hostList.hashCode());
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
		Response other = (Response) obj;
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		if (hostList == null) {
			if (other.hostList != null)
				return false;
		} else if (!hostList.equals(other.hostList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{health:");
		builder.append(health);
		builder.append(", hostList:");
		builder.append(hostList);
		builder.append("}");
		return builder.toString();
	}

	
	
	

}
