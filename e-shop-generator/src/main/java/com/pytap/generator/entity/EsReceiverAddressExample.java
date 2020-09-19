package com.pytap.generator.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EsReceiverAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EsReceiverAddressExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("receiver_name =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("receiver_name <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("receiver_name >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_name >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("receiver_name <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("receiver_name <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("receiver_name like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("receiver_name not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("receiver_name in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("receiver_name not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("receiver_name between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("receiver_name not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNull() {
            addCriterion("receiver_tel is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIsNotNull() {
            addCriterion("receiver_tel is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTelEqualTo(String value) {
            addCriterion("receiver_tel =", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotEqualTo(String value) {
            addCriterion("receiver_tel <>", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThan(String value) {
            addCriterion("receiver_tel >", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_tel >=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThan(String value) {
            addCriterion("receiver_tel <", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLessThanOrEqualTo(String value) {
            addCriterion("receiver_tel <=", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelLike(String value) {
            addCriterion("receiver_tel like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotLike(String value) {
            addCriterion("receiver_tel not like", value, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelIn(List<String> values) {
            addCriterion("receiver_tel in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotIn(List<String> values) {
            addCriterion("receiver_tel not in", values, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelBetween(String value1, String value2) {
            addCriterion("receiver_tel between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverTelNotBetween(String value1, String value2) {
            addCriterion("receiver_tel not between", value1, value2, "receiverTel");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeIsNull() {
            addCriterion("receiver_postcode is null");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeIsNotNull() {
            addCriterion("receiver_postcode is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeEqualTo(String value) {
            addCriterion("receiver_postcode =", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeNotEqualTo(String value) {
            addCriterion("receiver_postcode <>", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeGreaterThan(String value) {
            addCriterion("receiver_postcode >", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_postcode >=", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeLessThan(String value) {
            addCriterion("receiver_postcode <", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeLessThanOrEqualTo(String value) {
            addCriterion("receiver_postcode <=", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeLike(String value) {
            addCriterion("receiver_postcode like", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeNotLike(String value) {
            addCriterion("receiver_postcode not like", value, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeIn(List<String> values) {
            addCriterion("receiver_postcode in", values, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeNotIn(List<String> values) {
            addCriterion("receiver_postcode not in", values, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeBetween(String value1, String value2) {
            addCriterion("receiver_postcode between", value1, value2, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverPostcodeNotBetween(String value1, String value2) {
            addCriterion("receiver_postcode not between", value1, value2, "receiverPostcode");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNull() {
            addCriterion("receiver_province is null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNotNull() {
            addCriterion("receiver_province is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceEqualTo(String value) {
            addCriterion("receiver_province =", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotEqualTo(String value) {
            addCriterion("receiver_province <>", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThan(String value) {
            addCriterion("receiver_province >", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_province >=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThan(String value) {
            addCriterion("receiver_province <", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThanOrEqualTo(String value) {
            addCriterion("receiver_province <=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLike(String value) {
            addCriterion("receiver_province like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotLike(String value) {
            addCriterion("receiver_province not like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIn(List<String> values) {
            addCriterion("receiver_province in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotIn(List<String> values) {
            addCriterion("receiver_province not in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceBetween(String value1, String value2) {
            addCriterion("receiver_province between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotBetween(String value1, String value2) {
            addCriterion("receiver_province not between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNull() {
            addCriterion("receiver_city is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNotNull() {
            addCriterion("receiver_city is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityEqualTo(String value) {
            addCriterion("receiver_city =", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotEqualTo(String value) {
            addCriterion("receiver_city <>", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThan(String value) {
            addCriterion("receiver_city >", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_city >=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThan(String value) {
            addCriterion("receiver_city <", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThanOrEqualTo(String value) {
            addCriterion("receiver_city <=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLike(String value) {
            addCriterion("receiver_city like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotLike(String value) {
            addCriterion("receiver_city not like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIn(List<String> values) {
            addCriterion("receiver_city in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotIn(List<String> values) {
            addCriterion("receiver_city not in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityBetween(String value1, String value2) {
            addCriterion("receiver_city between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotBetween(String value1, String value2) {
            addCriterion("receiver_city not between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIsNull() {
            addCriterion("receiver_region is null");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIsNotNull() {
            addCriterion("receiver_region is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionEqualTo(String value) {
            addCriterion("receiver_region =", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotEqualTo(String value) {
            addCriterion("receiver_region <>", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionGreaterThan(String value) {
            addCriterion("receiver_region >", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_region >=", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLessThan(String value) {
            addCriterion("receiver_region <", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLessThanOrEqualTo(String value) {
            addCriterion("receiver_region <=", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLike(String value) {
            addCriterion("receiver_region like", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotLike(String value) {
            addCriterion("receiver_region not like", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIn(List<String> values) {
            addCriterion("receiver_region in", values, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotIn(List<String> values) {
            addCriterion("receiver_region not in", values, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionBetween(String value1, String value2) {
            addCriterion("receiver_region between", value1, value2, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotBetween(String value1, String value2) {
            addCriterion("receiver_region not between", value1, value2, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIsNull() {
            addCriterion("receiver_detail_address is null");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIsNotNull() {
            addCriterion("receiver_detail_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressEqualTo(String value) {
            addCriterion("receiver_detail_address =", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotEqualTo(String value) {
            addCriterion("receiver_detail_address <>", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressGreaterThan(String value) {
            addCriterion("receiver_detail_address >", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_detail_address >=", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLessThan(String value) {
            addCriterion("receiver_detail_address <", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLessThanOrEqualTo(String value) {
            addCriterion("receiver_detail_address <=", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLike(String value) {
            addCriterion("receiver_detail_address like", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotLike(String value) {
            addCriterion("receiver_detail_address not like", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIn(List<String> values) {
            addCriterion("receiver_detail_address in", values, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotIn(List<String> values) {
            addCriterion("receiver_detail_address not in", values, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressBetween(String value1, String value2) {
            addCriterion("receiver_detail_address between", value1, value2, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotBetween(String value1, String value2) {
            addCriterion("receiver_detail_address not between", value1, value2, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusIsNull() {
            addCriterion("default_status is null");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusIsNotNull() {
            addCriterion("default_status is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusEqualTo(Integer value) {
            addCriterion("default_status =", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusNotEqualTo(Integer value) {
            addCriterion("default_status <>", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusGreaterThan(Integer value) {
            addCriterion("default_status >", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("default_status >=", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusLessThan(Integer value) {
            addCriterion("default_status <", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusLessThanOrEqualTo(Integer value) {
            addCriterion("default_status <=", value, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusIn(List<Integer> values) {
            addCriterion("default_status in", values, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusNotIn(List<Integer> values) {
            addCriterion("default_status not in", values, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusBetween(Integer value1, Integer value2) {
            addCriterion("default_status between", value1, value2, "defaultStatus");
            return (Criteria) this;
        }

        public Criteria andDefaultStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("default_status not between", value1, value2, "defaultStatus");
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