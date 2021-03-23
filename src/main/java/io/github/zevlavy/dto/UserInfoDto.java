package io.github.zevlavy.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto
{
    private String id;
    private String name;
    private String webHookUrl;
    private List<AccountDto> accounts;
}
