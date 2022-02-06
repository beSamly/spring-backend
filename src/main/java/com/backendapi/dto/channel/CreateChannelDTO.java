package com.backendapi.dto.channel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChannelDTO {

    @NotBlank
    @NotNull
    private String channelName;

    @NotNull
    private Boolean isPrivate;
}
