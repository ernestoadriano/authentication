package com.eddy.authentication.dto;

import com.eddy.authentication.models.enums.Role;

public record RegisterDTO(String username, String password, Role role) {
}
