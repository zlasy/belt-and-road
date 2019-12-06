package com.example.mybatisdemo.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class QuestionClassifyTrain {
    private Long id;

    private String name;

    private Integer companyId;

    private Long qcId;

    private String keywords;

    private String factors;

    private Long roomId;

    private Long sessionId;

    private String content;

    private Integer status;

    private String creationUser;

    private Date creationDate;

    private String lastChangedUser;

    private Date lastChangedDate;

    private BigDecimal similarity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Long getQcId() {
        return qcId;
    }

    public void setQcId(Long qcId) {
        this.qcId = qcId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors == null ? null : factors.trim();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser == null ? null : creationUser.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastChangedUser() {
        return lastChangedUser;
    }

    public void setLastChangedUser(String lastChangedUser) {
        this.lastChangedUser = lastChangedUser == null ? null : lastChangedUser.trim();
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public BigDecimal getSimilarity() {
        return similarity;
    }

    public void setSimilarity(BigDecimal similarity) {
        this.similarity = similarity;
    }


    @Override
    public String toString() {
        return "QuestionClassifyTrain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", qcId=" + qcId +
                ", keywords='" + keywords + '\'' +
                ", factors='" + factors + '\'' +
                ", roomId=" + roomId +
                ", sessionId=" + sessionId +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", creationUser='" + creationUser + '\'' +
                ", creationDate=" + creationDate +
                ", lastChangedUser='" + lastChangedUser + '\'' +
                ", lastChangedDate=" + lastChangedDate +
                ", similarity=" + similarity +
                '}';
    }

}