package com.spring.messagingwithactivemq.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not empty")
    private String source;
    @NotNull(message = "Must not null")
    @NotBlank(message = "Must not empty")
    private String message;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "source='" + source + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
