package com.educibertec.sofiaproject.dto;

import com.educibertec.sofiaproject.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthUserDTO {
    private Long id;
    private String fullName;
    private String username;
    private Boolean status;
    private String token;
    private Rol rol;
}
