package com.yeadun.testutil.product.model;import com.yeadun.testutil.product.ProductType;public class NullPOJO {    private ProductType type;    public NullPOJO(ProductType type){        this.type = type;    }    public String toString(){        return "{ " + type.getProdCode() + " (" + type.getProdName() + ")接口}, 无返回结果。";    }}