package com.pytap.generator.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EsSkuProduct implements Serializable {
    private Long id;

    private Long productId;

    private Long productSpecDetailId;

    private String name;

    private String showImage;

    private Integer stock;

    private BigDecimal price;

    private Integer sale;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductSpecDetailId() {
        return productSpecDetailId;
    }

    public void setProductSpecDetailId(Long productSpecDetailId) {
        this.productSpecDetailId = productSpecDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
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
        sb.append(", productId=").append(productId);
        sb.append(", productSpecDetailId=").append(productSpecDetailId);
        sb.append(", name=").append(name);
        sb.append(", showImage=").append(showImage);
        sb.append(", stock=").append(stock);
        sb.append(", price=").append(price);
        sb.append(", sale=").append(sale);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}