package com.example.trabahopeapp.ViewAll;

public class Jobs {

    private String jobTitle;
    private String companyName;
    private String jobType;
    private String salary;
    private String qualifications;
    private String jobDresponsibilities;
    private String niceToHave;
    private String perksBenefits;
    private String barangay;
    String jobid, companyid, userid ,jobPostCreated, jobCategory;



    public Jobs(String jobTitle, String companyName, String jobType, String salary, String qualifications, String jobDresponsibilities, String niceToHave, String perksBenefits, String barangay, String jobid, String companyid, String userid, String jobPostCreated, String jobCategory) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.jobType = jobType;
        this.salary = salary;
        this.qualifications = qualifications;
        this.jobDresponsibilities = jobDresponsibilities;
        this.niceToHave = niceToHave;
        this.perksBenefits = perksBenefits;
        this.barangay = barangay;
        this.jobid = jobid;
        this.companyid = companyid;
        this.userid = userid;
        this.jobPostCreated = jobPostCreated;
        this.jobCategory = jobCategory;

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getjobType() {
        return jobType;
    }


    public void setjobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setRequirements(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getJobDresponsibilities() {
        return jobDresponsibilities;
    }

    public void setJobDresponsibilities(String jobDresponsibilities) {
        this.jobDresponsibilities = jobDresponsibilities;
    }

    public String getNiceToHave() {
        return niceToHave;
    }

    public void setNiceToHave(String niceToHave) {
        this.niceToHave = niceToHave;
    }

    public String getPerksBenefits() {
        return perksBenefits;
    }

    public void setPerksBenefits(String perksBenefits) {
        this.perksBenefits = perksBenefits;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJobPostCreated() {
        return jobPostCreated;
    }

    public void setJobPostCreated(String jobPostCreated) {
        this.jobPostCreated = jobPostCreated;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }


}
