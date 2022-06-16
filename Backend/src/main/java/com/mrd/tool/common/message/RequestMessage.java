package com.mrd.tool.common.message;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class RequestMessage {

    private String requestMethod;
    private String requestPath;
    private String urlParam;
    private String pathParam;
    private Map<String, Object> bodyParam;
    private Map<String, String> headerParam;


    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * vd: /v1.0/user/10  -  /v1.0/user là requestPath
     *
     * @return
     */
    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    /**
     * vd: /v1.0/user?id=10&name=hanm   -  urlParam là id=10&name=hanm
     *
     * @return
     */
    public String getUrlParam() {
        return urlParam;
    }

    public void setUrlParam(String urlParam) {
        this.urlParam = urlParam;
    }

    /**
     * vd: /v1.0/user/10  -  pathParam là 10
     *
     * @return
     */
    public String getPathParam() {
        return pathParam;
    }

    public void setPathParam(String pathParam) {
        this.pathParam = pathParam;
    }

    /**
     * Payload/body content của hàm POST, PUT, PATCH
     *
     * @return
     */
    public Map<String, Object> getBodyParam() {
        return bodyParam;
    }

    public void setBodyParam(Map<String, Object> bodyParam) {
        this.bodyParam = bodyParam;
    }

    public Map<String, String> getHeaderParam() {
        return headerParam;
    }

    public void setHeaderParam(Map<String, String> headerParam) {
        this.headerParam = headerParam;
    }

    public String toJsonString() {
        return new Gson().toJson(this);
    }
}