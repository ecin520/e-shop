package com.pytap.generator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EsProduct implements Serializable {
    private Long id;

    private Long productCategoryDetailId;

    private Long shopId;

    private String productCategoryDetailName;

    private String shopName;

    private String name;

    private String parameter;

    private String showImage;

    private String itemNo;

    private Integer deleteStatus;

    private Integer shelfStatus;

    private Integer verifyStatus;

    private BigDecimal price;

    private Integer growthValue;

    private Integer integral;

    private String subTitle;

    private String unit;

    private Integer preStatus;

    private Date saleTime;

    private String keywords;

    private Date updateTime;

    private Date createTime;

    private String description;

    private String detail;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCategoryDetailId() {
        return productCategoryDetailId;
    }

    public void setProductCategoryDetailId(Long productCategoryDetailId) {
        this.productCategoryDetailId = productCategoryDetailId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryDetailName() {
        return productCategoryDetailName;
    }

    public void setProductCategoryDetailName(String productCategoryDetailName) {
        this.productCategoryDetailName = productCategoryDetailName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getShelfStatus() {
        return shelfStatus;
    }

    public void setShelfStatus(Integer shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPreStatus() {
        return preStatus;
    }

    public void setPreStatus(Integer preStatus) {
        this.preStatus = preStatus;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productCategoryDetailId=").append(productCategoryDetailId);
        sb.append(", shopId=").append(shopId);
        sb.append(", productCategoryDetailName=").append(productCategoryDetailName);
        sb.append(", shopName=").append(shopName);
        sb.append(", name=").append(name);
        sb.append(", parameter=").append(parameter);
        sb.append(", showImage=").append(showImage);
        sb.append(", itemNo=").append(itemNo);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", shelfStatus=").append(shelfStatus);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", price=").append(price);
        sb.append(", growthValue=").append(growthValue);
        sb.append(", integral=").append(integral);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", unit=").append(unit);
        sb.append(", preStatus=").append(preStatus);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", keywords=").append(keywords);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", description=").append(description);
        sb.append(", detail=").append(detail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}