package com.qccr.sh.domain;

import java.util.ArrayList;
import java.util.List;

public class ConfigExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public ConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
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

        public Criteria andCkIsNull() {
            addCriterion("ck is null");
            return (Criteria) this;
        }

        public Criteria andCkIsNotNull() {
            addCriterion("ck is not null");
            return (Criteria) this;
        }

        public Criteria andCkEqualTo(String value) {
            addCriterion("ck =", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkNotEqualTo(String value) {
            addCriterion("ck <>", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkGreaterThan(String value) {
            addCriterion("ck >", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkGreaterThanOrEqualTo(String value) {
            addCriterion("ck >=", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkLessThan(String value) {
            addCriterion("ck <", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkLessThanOrEqualTo(String value) {
            addCriterion("ck <=", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkLike(String value) {
            addCriterion("ck like", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkNotLike(String value) {
            addCriterion("ck not like", value, "ck");
            return (Criteria) this;
        }

        public Criteria andCkIn(List<String> values) {
            addCriterion("ck in", values, "ck");
            return (Criteria) this;
        }

        public Criteria andCkNotIn(List<String> values) {
            addCriterion("ck not in", values, "ck");
            return (Criteria) this;
        }

        public Criteria andCkBetween(String value1, String value2) {
            addCriterion("ck between", value1, value2, "ck");
            return (Criteria) this;
        }

        public Criteria andCkNotBetween(String value1, String value2) {
            addCriterion("ck not between", value1, value2, "ck");
            return (Criteria) this;
        }

        public Criteria andCvIsNull() {
            addCriterion("cv is null");
            return (Criteria) this;
        }

        public Criteria andCvIsNotNull() {
            addCriterion("cv is not null");
            return (Criteria) this;
        }

        public Criteria andCvEqualTo(String value) {
            addCriterion("cv =", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvNotEqualTo(String value) {
            addCriterion("cv <>", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvGreaterThan(String value) {
            addCriterion("cv >", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvGreaterThanOrEqualTo(String value) {
            addCriterion("cv >=", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvLessThan(String value) {
            addCriterion("cv <", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvLessThanOrEqualTo(String value) {
            addCriterion("cv <=", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvLike(String value) {
            addCriterion("cv like", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvNotLike(String value) {
            addCriterion("cv not like", value, "cv");
            return (Criteria) this;
        }

        public Criteria andCvIn(List<String> values) {
            addCriterion("cv in", values, "cv");
            return (Criteria) this;
        }

        public Criteria andCvNotIn(List<String> values) {
            addCriterion("cv not in", values, "cv");
            return (Criteria) this;
        }

        public Criteria andCvBetween(String value1, String value2) {
            addCriterion("cv between", value1, value2, "cv");
            return (Criteria) this;
        }

        public Criteria andCvNotBetween(String value1, String value2) {
            addCriterion("cv not between", value1, value2, "cv");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sh_conf
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sh_conf
     *
     * @mbggenerated
     */
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