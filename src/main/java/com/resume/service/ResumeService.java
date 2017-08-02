package com.resume.service;

import com.resume.dto.ResumeInfo;

public interface ResumeService {
	
	/**
	 * 通过id获取简历信息
	 * @param id
	 * @return
	 */
	ResumeInfo getResumeById(long id);
	
	/**
	 * 保存简历信息
	 * @param resumeInfo
	 * @return 返回简历id
	 */
	long saveResumeInfo(ResumeInfo resumeInfo);
	
	/**
	 * 更新简历信息
	 * @param resumeInfo
	 */
	void updateResumeInfo(ResumeInfo resumeInfo);
	
	/**
	 * 删除简历信息
	 * @param id
	 */
	void deleteResumeInfo(long id);


}
