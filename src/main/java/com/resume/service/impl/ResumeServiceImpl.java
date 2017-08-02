package com.resume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.dao.ResumeInfoDao;
import com.resume.dto.ResumeInfo;
import com.resume.po.ResumeInfoPo;
import com.resume.service.ResumeService;
import com.resume.util.BeanUtil;

@Service
public class ResumeServiceImpl implements ResumeService {
	
	@Autowired
	private ResumeInfoDao resumeInfoDao;

	@Override
	public ResumeInfo getResumeById(long id) {
		ResumeInfoPo resumeInfoPo = resumeInfoDao.queryById(id);
		return BeanUtil.createCopy(resumeInfoPo, ResumeInfo.class);
	}

	@Override
	public long saveResumeInfo(ResumeInfo resumeInfo) {
		
		ResumeInfoPo resumeInfoPo = BeanUtil.createCopy(resumeInfo, ResumeInfoPo.class);
		resumeInfoDao.insertResumeInfo(resumeInfoPo);
		
		return resumeInfoPo.getId();
	}

	@Override
	public void updateResumeInfo(ResumeInfo resumeInfo) {
		if(null != resumeInfo){
			resumeInfoDao.updateResumeInfo(BeanUtil.createCopy(resumeInfo, ResumeInfoPo.class));
		}

	}

	@Override
	public void deleteResumeInfo(long id) {
		// TODO Auto-generated method stub

	}

}
