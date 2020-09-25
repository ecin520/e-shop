package com.pytap.generator.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EsOrderSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EsOrderSettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeIsNull() {
            addCriterion("seckill_overtime is null");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeIsNotNull() {
            addCriterion("seckill_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeEqualTo(Integer value) {
            addCriterion("seckill_overtime =", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeNotEqualTo(Integer value) {
            addCriterion("seckill_overtime <>", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeGreaterThan(Integer value) {
            addCriterion("seckill_overtime >", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("seckill_overtime >=", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeLessThan(Integer value) {
            addCriterion("seckill_overtime <", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("seckill_overtime <=", value, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeIn(List<Integer> values) {
            addCriterion("seckill_overtime in", values, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeNotIn(List<Integer> values) {
            addCriterion("seckill_overtime not in", values, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("seckill_overtime between", value1, value2, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andSeckillOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("seckill_overtime not between", value1, value2, "seckillOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIsNull() {
            addCriterion("normal_order_overtime is null");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIsNotNull() {
            addCriterion("normal_order_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeEqualTo(Integer value) {
            addCriterion("normal_order_overtime =", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotEqualTo(Integer value) {
            addCriterion("normal_order_overtime <>", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeGreaterThan(Integer value) {
            addCriterion("normal_order_overtime >", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_order_overtime >=", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeLessThan(Integer value) {
            addCriterion("normal_order_overtime <", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("normal_order_overtime <=", value, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeIn(List<Integer> values) {
            addCriterion("normal_order_overtime in", values, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotIn(List<Integer> values) {
            addCriterion("normal_order_overtime not in", values, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("normal_order_overtime between", value1, value2, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andNormalOrderOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_order_overtime not between", value1, value2, "normalOrderOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIsNull() {
            addCriterion("confirm_overtime is null");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIsNotNull() {
            addCriterion("confirm_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeEqualTo(Integer value) {
            addCriterion("confirm_overtime =", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotEqualTo(Integer value) {
            addCriterion("confirm_overtime <>", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeGreaterThan(Integer value) {
            addCriterion("confirm_overtime >", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_overtime >=", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeLessThan(Integer value) {
            addCriterion("confirm_overtime <", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_overtime <=", value, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeIn(List<Integer> values) {
            addCriterion("confirm_overtime in", values, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotIn(List<Integer> values) {
            addCriterion("confirm_overtime not in", values, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("confirm_overtime between", value1, value2, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andConfirmOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_overtime not between", value1, value2, "confirmOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeIsNull() {
            addCriterion("return_overtime is null");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeIsNotNull() {
            addCriterion("return_overtime is not null");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeEqualTo(Integer value) {
            addCriterion("return_overtime =", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeNotEqualTo(Integer value) {
            addCriterion("return_overtime <>", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeGreaterThan(Integer value) {
            addCriterion("return_overtime >", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_overtime >=", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeLessThan(Integer value) {
            addCriterion("return_overtime <", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeLessThanOrEqualTo(Integer value) {
            addCriterion("return_overtime <=", value, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeIn(List<Integer> values) {
            addCriterion("return_overtime in", values, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeNotIn(List<Integer> values) {
            addCriterion("return_overtime not in", values, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeBetween(Integer value1, Integer value2) {
            addCriterion("return_overtime between", value1, value2, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andReturnOvertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("return_overtime not between", value1, value2, "returnOvertime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}