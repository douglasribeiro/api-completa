package com.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Integer id;
    private String nome;
    private String email;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;

}
