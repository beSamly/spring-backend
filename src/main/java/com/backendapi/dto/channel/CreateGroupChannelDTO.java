package com.backendapi.dto.channel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupChannelDTO {

    @NotNull
    private String channelName;

    @NotNull
    private Boolean isPrivate;

    @NotNull
    private Long groupId;
}
