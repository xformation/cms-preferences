package com.synectiks.pref.domain.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.synectiks.pref.domain.AttendanceMaster;

/**
 * A Vo for the Lecture entity.
 */


public class CmsLectureVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private LocalDate lecDate;
    private LocalDate lastUpdatedOn;
    private String lastUpdatedBy;
    private String startTime;
    private String endTime;
    private AttendanceMaster attendancemaster;
    private String strLecDate;
    private String strLastUpdatedOn;
	private String weekDay;
    private String subjectId;
    private String teacherId;
    
	private Long attendanceMasterId;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLecDate() {
        return lecDate;
    }

    public void setLecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

	public AttendanceMaster getAttendancemaster() {
		return attendancemaster;
	}

	public void setAttendancemaster(AttendanceMaster attendancemaster) {
		this.attendancemaster = attendancemaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendancemaster == null) ? 0 : attendancemaster.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
		result = prime * result + ((lecDate == null) ? 0 : lecDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CmsLectureVo other = (CmsLectureVo) obj;
		if (attendancemaster == null) {
			if (other.attendancemaster != null)
				return false;
		} else if (!attendancemaster.equals(other.attendancemaster))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
			return false;
		if (lecDate == null) {
			if (other.lecDate != null)
				return false;
		} else if (!lecDate.equals(other.lecDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	public String getStrLecDate() {
		return strLecDate;
	}

	public void setStrLecDate(String strLecDate) {
		this.strLecDate = strLecDate;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Long getAttendanceMasterId() {
		return attendanceMasterId;
	}

	public void setAttendanceMasterId(Long attendanceMasterId) {
		this.attendanceMasterId = attendanceMasterId;
	}

	public String getStrLastUpdatedOn() {
		return strLastUpdatedOn;
	}

	public void setStrLastUpdatedOn(String strLastUpdatedOn) {
		this.strLastUpdatedOn = strLastUpdatedOn;
	}

	@Override
	public String toString() {
		return "CmsLectureVo [id=" + id + ", lecDate=" + lecDate + ", lastUpdatedOn=" + lastUpdatedOn
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", attendancemaster=" + attendancemaster + ", strLecDate=" + strLecDate + ", strLastUpdatedOn="
				+ strLastUpdatedOn + ", weekDay=" + weekDay + ", subjectId=" + subjectId + ", teacherId=" + teacherId
				+ ", attendanceMasterId=" + attendanceMasterId + "]";
	}

    
}
