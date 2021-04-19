package com.api.PagosPSE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public class DataResponse {
    private boolean success;
    private String title_response, text_response, last_action;
    private List<Data> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTitle_response() {
        return title_response;
    }

    public void setTitle_response(String title_response) {
        this.title_response = title_response;
    }

    public String getText_response() {
        return text_response;
    }

    public void setText_response(String text_response) {
        this.text_response = text_response;
    }

    public String getLast_action() {
        return last_action;
    }

    public void setLast_action(String last_action) {
        this.last_action = last_action;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
