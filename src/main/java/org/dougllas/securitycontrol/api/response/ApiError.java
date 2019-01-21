package org.dougllas.securitycontrol.api.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiError {

    private List<String> messages;

    public ApiError() {}

    public ApiError(String message) {
        this();
        addError(message);
    }

    public ApiError(List<String> messages) {
        this.messages = messages;
    }

    public void addError(String message){
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public List<String> getMessages() {
        if(messages == null){
            messages = new ArrayList<>();
        }
        return Collections.unmodifiableList(messages);
    }
}
