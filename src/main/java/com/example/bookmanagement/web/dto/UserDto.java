package com.example.bookmanagement.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long id;
    private String userId;
    private String email;
    private String userPw;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String userStatus;
}
