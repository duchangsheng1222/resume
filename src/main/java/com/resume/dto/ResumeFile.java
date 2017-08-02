package com.resume.dto;

public class ResumeFile {
	
	private long id;
	
	private String fileAddress;
	
	private long resumeId;
	
	private long userId;
	
	private String fileType;
	
	private int type;
	
	private String deleted;
	
	private ResumeInfo resumeInfo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
	}

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ResumeInfo getResumeInfo() {
		return resumeInfo;
	}

	public void setResumeInfo(ResumeInfo resumeInfo) {
		this.resumeInfo = resumeInfo;
	}
	

}
