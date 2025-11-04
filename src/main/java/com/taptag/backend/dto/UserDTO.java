package com.taptag.backend.dto;

import java.util.UUID;

public record UserDTO(UUID id, String email, String name) {
}
