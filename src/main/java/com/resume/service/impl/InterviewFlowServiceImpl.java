package com.resume.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume.dao.InterviewFlowDao;
import com.resume.dto.InterviewFlow;
import com.resume.dto.ResumeInfo;
import com.resume.po.InterviewFlowPo;
import com.resume.service.InterviewFlowService;
import com.resume.util.BeanUtil;

@Service
public class InterviewFlowServiceImpl implements InterviewFlowService{
	
	@Autowired
	private InterviewFlowDao interviewFlowDao;
	
	@Override
	public Long insertInterviewFlow(InterviewFlow interviewFlow){
		InterviewFlowPo interviewFlowPo = BeanUtil.createCopy(interviewFlow, InterviewFlowPo.class);
		interviewFlowDao.insertInterviewFlow(interviewFlowPo);
		return interviewFlowPo.getId();
	}
	
	@Override
	public void updateFlowStatus(InterviewFlow interviewFlow){
		InterviewFlowPo interviewFlowPo = BeanUtil.createCopy(interviewFlow, InterviewFlowPo.class);
		interviewFlowDao.updateFlowStatus(interviewFlowPo);
	}
	
	@Override
	public InterviewFlow findById(long id){
		InterviewFlowPo interviewFlowPo = interviewFlowDao.queryById(id);
		return BeanUtil.createCopy(interviewFlowPo, InterviewFlow.class);
	}
	
	@Override
	public InterviewFlow findByResumeId(long resumeId){
		InterviewFlowPo interviewFlowPo = interviewFlowDao.queryByResumeId(resumeId);
		return BeanUtil.createCopy(interviewFlowPo, InterviewFlow.class);
	}

	@Override
	public List<InterviewFlow> list(Long step, String col, String order,Integer page,Integer size) {
		Integer beginIndex = null;
		if(page != null){
			beginIndex = (page -1 ) * size;
		}
		List<InterviewFlowPo> listPos = interviewFlowDao.listPos(step, col, order, beginIndex, size);
		List<InterviewFlow> dtos = new ArrayList<InterviewFlow>();
		for (InterviewFlowPo interviewFlowPo : listPos) {
			ResumeInfo resumeInfo = BeanUtil.createCopy(interviewFlowPo.getResumeInfoPo(), ResumeInfo.class);
			InterviewFlow flow = BeanUtil.createCopy(interviewFlowPo, InterviewFlow.class);
			flow.setResumeInfo(resumeInfo);
			dtos.add(flow);
		}
		
		return dtos;
	}
}
