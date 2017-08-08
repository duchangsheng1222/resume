package com.resume.service;

import java.util.List;

import com.resume.dto.ResumeFile;

public interface ResumeFileService {
	
	/**
	 * 通过id获取简历信息
	 * @param id
	 * @return
	 */
	ResumeFile getResumeFileByResumeIdAndType(long resumeId,int type);
	
	/**
	 * 获取多个简历文件
	 * @param resumeIds
	 * @param type
	 * @return
	 */
	List<ResumeFile> getResumeFileByResumeIdsAndType(List<Long> resumeIds, int type);
	
	/**
	 * 保存简历文件
	 * @param resumeFile
	 * @return 返回简历文件id
	 */
	long saveResumeFile(ResumeFile resumeFile);
	
	
	/**
	 * 删除文件信息
	 * @param id
	 */
	void deleteFile(long id);


}
