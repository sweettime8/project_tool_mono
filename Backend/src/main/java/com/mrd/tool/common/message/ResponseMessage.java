package com.mrd.tool.common.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private int status;
    private String message;
    private MessageContent data;

    public ResponseMessage(MessageContent data) {
        this.status = data != null ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value();
        this.message = data != null ? HttpStatus.OK.toString() : HttpStatus.NOT_FOUND.toString();
        this.data = data;
    }

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}