package com.backendapi.dto.channel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUsersToChannelDTO {

    @NotEmpty
    @NotNull
    private List<Long> userIds;

    @NotNull
    private Long channelId;
}
