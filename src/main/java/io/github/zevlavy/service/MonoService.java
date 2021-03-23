package io.github.zevlavy.service;

import io.github.zevlavy.dto.StatementItem;
import io.github.zevlavy.dto.UserInfoDto;

import java.time.LocalDate;
import java.util.Optional;

public interface MonoService
{
    Optional<UserInfoDto> getUserInfo( String privateToken);

    Optional<StatementItem[]> getUserStatement( String privateToken, String accountId, LocalDate from, LocalDate to );
}
