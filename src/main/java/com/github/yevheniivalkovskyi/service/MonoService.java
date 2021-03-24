package com.github.yevheniivalkovskyi.service;

import com.github.yevheniivalkovskyi.dto.StatementItem;
import com.github.yevheniivalkovskyi.dto.UserInfoDto;

import java.time.LocalDate;
import java.util.Optional;

public interface MonoService
{
    Optional<UserInfoDto> getUserInfo();

    Optional<StatementItem[]> getUserStatement( String accountId, LocalDate from, LocalDate to );
}
