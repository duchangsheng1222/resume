package com.resume.service;

import com.resume.dto.InterviewFlow;

public interface InterviewFlowService {
	
	public Long insertInterviewFlow(InterviewFlow interviewFlow);
	
	public void updateFlowStatus(InterviewFlow interviewFlow);
	
	public InterviewFlow findById(long id);
	
	public InterviewFlow findByResumeId(long resumeId);

}
