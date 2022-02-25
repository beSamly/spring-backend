package com.backendapi.socket_message;

import com.backendapi.custom_annotation.IgnoreProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSocketPayload {

    @IgnoreProperty
    private List<Integer> receiverIds = new ArrayList<Integer>();

    abstract public String toString();
}
