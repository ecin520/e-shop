package com.pytap.generator.entity;

import java.io.Serializable;
import java.util.Date;

public class EsOrderSetting implements Serializable {
    private Long id;

    private Integer seckillOvertime;

    private Integer normalOrderOvertime;

    private Integer confirmOvertime;

    private Integer returnOvertime;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeckillOvertime() {
        return seckillOvertime;
    }

    public void setSeckillOvertime(Integer seckillOvertime) {
        this.seckillOvertime = seckillOvertime;
    }

    public Integer getNormalOrderOvertime() {
        return normalOrderOvertime;
    }

    public void setNormalOrderOvertime(Integer normalOrderOvertime) {
        this.normalOrderOvertime = normalOrderOvertime;
    }

    public Integer getConfirmOvertime() {
        return confirmOvertime;
    }

    public void setConfirmOvertime(Integer confirmOvertime) {
        this.confirmOvertime = confirmOvertime;
    }

    public Integer getReturnOvertime() {
        return returnOvertime;
    }

    public void setReturnOvertime(Integer returnOvertime) {
        this.returnOvertime = returnOvertime;
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
        sb.append(", seckillOvertime=").append(seckillOvertime);
        sb.append(", normalOrderOvertime=").append(normalOrderOvertime);
        sb.append(", confirmOvertime=").append(confirmOvertime);
        sb.append(", returnOvertime=").append(returnOvertime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EsOrderSetting other = (EsOrderSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSeckillOvertime() == null ? other.getSeckillOvertime() == null : this.getSeckillOvertime().equals(other.getSeckillOvertime()))
            && (this.getNormalOrderOvertime() == null ? other.getNormalOrderOvertime() == null : this.getNormalOrderOvertime().equals(other.getNormalOrderOvertime()))
            && (this.getConfirmOvertime() == null ? other.getConfirmOvertime() == null : this.getConfirmOvertime().equals(other.getConfirmOvertime()))
            && (this.getReturnOvertime() == null ? other.getReturnOvertime() == null : this.getReturnOvertime().equals(other.getReturnOvertime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSeckillOvertime() == null) ? 0 : getSeckillOvertime().hashCode());
        result = prime * result + ((getNormalOrderOvertime() == null) ? 0 : getNormalOrderOvertime().hashCode());
        result = prime * result + ((getConfirmOvertime() == null) ? 0 : getConfirmOvertime().hashCode());
        result = prime * result + ((getReturnOvertime() == null) ? 0 : getReturnOvertime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}