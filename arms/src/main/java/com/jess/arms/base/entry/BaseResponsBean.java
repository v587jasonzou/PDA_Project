package com.jess.arms.base.entry;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseResponsBean<T> implements Serializable {
    private Boolean success;
    private T data;
    private T entity;
    private Integer totalProperty;
    private String id;
    private String message;
    private Map<String,String> iamges;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getIamges() {
        return iamges;
    }

    public void setIamges(Map<String, String> iamges) {
        this.iamges = iamges;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(Integer totalProperty) {
        this.totalProperty = totalProperty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
