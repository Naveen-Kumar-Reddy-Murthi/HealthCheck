package com.atc.pojo;

import java.util.Map;

public class Health {
	private String health;
	private Map<String,String> DB;
	private Map<String,String> SFTP;
	public String getHealth() {
		return health;
	}
	public Map<String, String> getDB() {
		return DB;
	}
	public Map<String, String> getSFTP() {
		return SFTP;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public void setDB(Map<String, String> dB) {
		DB = dB;
	}
	public void setSFTP(Map<String, String> sFTP) {
		SFTP = sFTP;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DB == null) ? 0 : DB.hashCode());
		result = prime * result + ((SFTP == null) ? 0 : SFTP.hashCode());
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
		Health other = (Health) obj;
		if (DB == null) {
			if (other.DB != null)
				return false;
		} else if (!DB.equals(other.DB))
			return false;
		if (SFTP == null) {
			if (other.SFTP != null)
				return false;
		} else if (!SFTP.equals(other.SFTP))
			return false;
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
		builder.append("{ health=");
		builder.append(health);
		if(DB!=null){
		builder.append(", DB=");
		builder.append(DB);
		}
		if(SFTP!=null){
		builder.append(", SFTP=");
		builder.append(SFTP);
		}
		builder.append("}");
		return builder.toString();
	}
	
	
	

}
