package com.resume.dao;

import com.resume.po.ResumeInfoPo;

public interface ResumeInfoDao {
	
	
	/**
	 * 根据id查询resume
	 * @param id
	 * @return
	 */
	ResumeInfoPo queryById(long id);
	
	/**
	 * 保存个人信息
	 * @param resumeInfoPo
	 */
	Long insertResumeInfo(ResumeInfoPo resumeInfoPo);
	
	/**
	 * 更新个人信息
	 * @param resumeInfoPo
	 */
	void updateResumeInfo(ResumeInfoPo resumeInfoPo);

}
