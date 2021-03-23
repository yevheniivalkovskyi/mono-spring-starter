package com.github.yevheniivalkovskyi.service;

import com.github.yevheniivalkovskyi.dto.StatementItem;
import com.github.yevheniivalkovskyi.dto.UserInfoDto;

import java.time.LocalDate;
import java.util.Optional;

public interface MonoService
{
    Optional<UserInfoDto> getUserInfo( String privateToken);

    Optional<StatementItem[]> getUserStatement( String privateToken, String accountId, LocalDate from, LocalDate to );
}
