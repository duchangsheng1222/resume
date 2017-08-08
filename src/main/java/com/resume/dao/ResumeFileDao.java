package com.resume.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.resume.po.ResumeFilePo;

public interface ResumeFileDao {
	
	/**
	 * 通过简历id和文件类型查询文件信息
	 * @param resumeId
	 * @param type
	 * @return
	 */
	ResumeFilePo queryByResumeIdAndType(@Param("resumeId")long resumeId,@Param("type")int type);
	
	List<ResumeFilePo> queryByResumeIdsAndType(@Param("resumeIds")List<Long> resumeIds,@Param("type")int type);
	
	/**
	 * 保存简历文件
	 * @param resumeFilePo
	 */
	Long insertResumeFile(ResumeFilePo resumeFilePo);
	
	/**
	 * 删除文件
	 * @param id
	 */
	void deleteFile(@Param("id")long id);
}
