package com.vg.eventmanagement.requests;

public class OrganizerResponse {

	private Long id;
	private String organizerName;

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
