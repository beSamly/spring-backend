package com.backendapi.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGroupDTO {

    @NotBlank
    @NotNull
    private String groupName;

    @NotNull
    private Boolean isPrivate;

    @Size(min = 3, max = 5)
    @NotNull
    private ArrayList<String> tags;
}
