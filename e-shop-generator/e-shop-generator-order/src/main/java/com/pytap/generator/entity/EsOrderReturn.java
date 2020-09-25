package com.pytap.generator.entity;

import java.io.Serializable;
import java.util.Date;

public class EsOrderReturn implements Serializable {
    private Long id;

    private Long memberId;

    private Long orderId;

    private Long handlerId;

    private Long memberAddressId;

    private Long shopAddressId;

    private Integer status;

    private Date handleTime;

    private String reason;

    private String description;

    private String certificateImage;

    private String handleNote;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Long getMemberAddressId() {
        return memberAddressId;
    }

    public void setMemberAddressId(Long memberAddressId) {
        this.memberAddressId = memberAddressId;
    }

    public Long getShopAddressId() {
        return shopAddressId;
    }

    public void setShopAddressId(Long shopAddressId) {
        this.shopAddressId = shopAddressId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(String certificateImage) {
        this.certificateImage = certificateImage;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", orderId=").append(orderId);
        sb.append(", handlerId=").append(handlerId);
        sb.append(", memberAddressId=").append(memberAddressId);
        sb.append(", shopAddressId=").append(shopAddressId);
        sb.append(", status=").append(status);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", reason=").append(reason);
        sb.append(", description=").append(description);
        sb.append(", certificateImage=").append(certificateImage);
        sb.append(", handleNote=").append(handleNote);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}