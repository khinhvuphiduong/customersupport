package khanhnh.demo;

import java.io.Serializable;
import java.net.InetAddress;

public class PageVisit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long enteredTimestamp;
	private Long leftTimestamp;
	private String request;
	private InetAddress ipaddress;
	
	public long getEnteredTimestamp() {
		return enteredTimestamp;
	}
	public void setEnteredTimestamp(long enteredTimestamp) {
		this.enteredTimestamp = enteredTimestamp;
	}
	public Long getLeftTimestamp() {
		return leftTimestamp;
	}
	public void setLeftTimestamp(Long leftTimestamp) {
		this.leftTimestamp = leftTimestamp;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public InetAddress getIpAddress() {
		return ipaddress;
	}
	public void setIpAddress(InetAddress ipaddress) {
		this.ipaddress = ipaddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
