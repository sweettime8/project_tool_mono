package com.mrd.tool.common.message;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageContent {

    private Object data;
    private Long total;

    public MessageContent(Object data) {
        this.data = data;
    }

    public MessageContent(Object data, Long total) {
        this.data = data;
        this.total = total;
    }

    public String toJsonString(){
        return new Gson().toJson(this);
    }

}
