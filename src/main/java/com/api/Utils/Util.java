package com.api.Utils;

import com.api.ModelVO.UsuarioVO;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Util {
    private static JSONObject json = null;

    public static String errorsJson(BindingResult result) {
        json = new JSONObject();
        for (FieldError error : result.getFieldErrors()) {
            json.put(error.getField(), error.getDefaultMessage());
        }
        return json.toString();
    }

    public static String messageJson(String value) {
        json = new JSONObject();
        return json.put("message", value).toString();
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        // headers.set("Authorization", token);
        return headers;
    }

    // ====== Metodos HTTP
    public static HttpEntity getHttpEntity() {
        return new HttpEntity(getHttpHeaders());
    }

    // == HttpEntity para enviar objeto en formato json
    public static HttpEntity getHttpEntity(UsuarioVO usvo) {
        JSONObject json = new JSONObject(usvo);
        return new HttpEntity(json.toString(), getHttpHeaders());
    }


    // == HttpEntity para enviar objeto json
    public static HttpEntity getHttpEntity(JSONObject json) {
        return new HttpEntity(json.toString(), getHttpHeaders());
    }
}
