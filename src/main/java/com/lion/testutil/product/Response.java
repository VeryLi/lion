package com.lion.testutil.product;import com.alibaba.fastjson.JSON;import com.lion.testutil.product.model.NullPOJO;import org.slf4j.Logger;import org.slf4j.LoggerFactory;/** * 封装API接口返回响应的类 * */public class Response {    private static final Logger LOG = LoggerFactory.getLogger(Response.class);    private String merchPrivate;    private String orderId;    private String retCode;    private String retDesc;    private String retInfo;    private String POJOClassName;    private Object POJOData;    @SuppressWarnings("unchecked")    public Object getPOJOData(ProductType type){        if(this.retInfo == null || "".equalsIgnoreCase(this.retInfo)){            return new NullPOJO(type);        }        if(POJOData != null){            return POJOData;        }        Class clazz = null;        if(this.POJOClassName == null || "".equalsIgnoreCase(this.POJOClassName)){            this.POJOClassName = type.getPOJOClassName();        }        try {            clazz = Class.forName(this.POJOClassName);        } catch (ClassNotFoundException e) {            LOG.error(e.getMessage(), e);        }        this.POJOData = JSON.parseObject(this.retInfo, clazz);        return  this.POJOData;    }    public String getThirdClassNamn() {        return POJOClassName;    }    public void setThirdClassNamn(String thirdClassNamn) {        this.POJOClassName = thirdClassNamn;    }    public String getMerchPrivate() {        return merchPrivate;    }    public void setMerchPrivate(String merchPrivate) {        this.merchPrivate = merchPrivate;    }    public String getOrderId() {        return orderId;    }    public void setOrderId(String orderId) {        this.orderId = orderId;    }    public String getRetCode() {        return retCode;    }    public void setRetCode(String retCode) {        this.retCode = retCode;    }    public String getRetDesc() {        return retDesc;    }    public void setRetDesc(String retDesc) {        this.retDesc = retDesc;    }    public String getRetInfo() {        return retInfo;    }    public void setRetInfo(String retInfo) {        this.retInfo = retInfo;    }}