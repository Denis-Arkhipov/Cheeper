package com.denar.cheeper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String messageDate;
//    @NotEmpty
//    @Size(max = 2000, message = "Message must not exceed 2000 characters")
    private String textMessage;
//    @NotBlank(message="Name must contain at least 3 characters")
    private String username;
}
