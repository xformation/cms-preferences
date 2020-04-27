package com.synectiks.pref.domain.vo;

import java.io.Serializable;

public class CmsLectureBadgeVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subjectDes;
	private String subjectCode;
	private Long totalScheduledLectures;
	private Long totalConductedLectures;
	private Long totalRemainingLectures;
	private String nextLectureDate;
	private String nextLectureTime;
	private String departmentName;
	private String className;
	
	
	public String getSubjectDes() {
		return subjectDes;
	}
	public void setSubjectDes(String subjectDes) {
		this.subjectDes = subjectDes;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public Long getTotalScheduledLectures() {
		return totalScheduledLectures;
	}
	public void setTotalScheduledLectures(Long totalScheduledLectures) {
		this.totalScheduledLectures = totalScheduledLectures;
	}
	public Long getTotalConductedLectures() {
		return totalConductedLectures;
	}
	public void setTotalConductedLectures(Long totalConductedLectures) {
		this.totalConductedLectures = totalConductedLectures;
	}
	public Long getTotalRemainingLectures() {
		return totalRemainingLectures;
	}
	public void setTotalRemainingLectures(Long totalRemainingLectures) {
		this.totalRemainingLectures = totalRemainingLectures;
	}
	public String getNextLectureDate() {
		return nextLectureDate;
	}
	public void setNextLectureDate(String nextLectureDate) {
		this.nextLectureDate = nextLectureDate;
	}
	public String getNextLectureTime() {
		return nextLectureTime;
	}
	public void setNextLectureTime(String nextLectureTime) {
		this.nextLectureTime = nextLectureTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return "CmsLectureBadgeVo [subjectDes=" + subjectDes + ", subjectCode=" + subjectCode
				+ ", totalScheduledLectures=" + totalScheduledLectures + ", totalConductedLectures="
				+ totalConductedLectures + ", totalRemainingLectures=" + totalRemainingLectures + ", nextLectureDate="
				+ nextLectureDate + ", nextLectureTime=" + nextLectureTime + ", departmentName=" + departmentName
				+ ", className=" + className + "]";
	}
	
	
}
