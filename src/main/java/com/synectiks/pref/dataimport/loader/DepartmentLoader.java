package com.synectiks.pref.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.pref.dataimport.AllRepositories;
import com.synectiks.pref.dataimport.DataLoader;
import com.synectiks.pref.domain.AcademicYear;
import com.synectiks.pref.domain.Branch;
import com.synectiks.pref.domain.Department;
import com.synectiks.pref.exceptions.MandatoryFieldMissingException;
import com.synectiks.pref.service.util.CommonUtil;


public class DepartmentLoader extends DataLoader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public DepartmentLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException {
        StringBuilder sb = new StringBuilder();

        Department obj = CommonUtil.createCopyProperties(cls.newInstance(), Department.class);

        String name = row.getCellAsString(0).orElse(null);
        if(CommonUtil.isNullOrEmpty(name)) {
            sb.append("name, ");
            logger.warn("Mandatory field missing. Field name - name");
        }else {
            obj.setName(name);
        }

        String description = row.getCellAsString(1).orElse(null);
        if(CommonUtil.isNullOrEmpty(description)) {
            sb.append("description, ");
            logger.warn("Mandatory field missing. Field name - description");
        }else {
            obj.setDescription(description);
        }

        String deptHead = row.getCellAsString(2).orElse(null);
        if(CommonUtil.isNullOrEmpty(deptHead)) {
            sb.append("deptHead, ");
            logger.warn("Mandatory field missing. Field name - dept_head");
        }else {
            obj.setDeptHead(deptHead);
        }

        String branchName = row.getCellAsString(3).orElse(null);
        if(CommonUtil.isNullOrEmpty(branchName)) {
            sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Field name - branch_id");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(b.isPresent()) {
                obj.setBranch(b.get());
            }else {
                sb.append("branch_id, ");
                logger.warn("Branch not found. Given branch name : "+branchName);
            }
        }

        String academicYear = row.getCellAsString(4).orElse(null);
        if(CommonUtil.isNullOrEmpty(academicYear)) {
            sb.append("academic_year_id, ");
            logger.warn("Mandatory field missing. Field name - academic_year_id");
        }else {
            AcademicYear academicYear1 = new AcademicYear();
            academicYear1.setDescription(academicYear);
            Optional<AcademicYear> ay = this.allRepositories.findRepository("academic_year").findOne(Example.of(academicYear1));
            if(ay.isPresent()) {
                obj.setAcademicYear(ay.get());
            }else {
                sb.append("academic_year_id, ");
                logger.warn("AcademicYear not found. Given academicYear name : "+academicYear);
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }
        return (T)obj;

    }
}
