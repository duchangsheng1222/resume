package com.resume.dao;

import com.resume.po.ResumeInfoPo;

public interface ResumeInfoDao {
	
	/**
	 * 保存个人信息
	 * @param resumeInfoPo
	 */
	void insertResumeInfo(ResumeInfoPo resumeInfoPo);

}
