package com.github.yevheniivalkovskyi.service;

import com.github.yevheniivalkovskyi.dto.StatementItem;
import com.github.yevheniivalkovskyi.dto.UserInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

import static com.github.yevheniivalkovskyi.util.Constants.GET_PERSONAL_CLIENT_INFO;
import static com.github.yevheniivalkovskyi.util.Constants.GET_TRANSACTION_HISTORY;


@Service
public class MonoServiceImpl implements MonoService
{
    private MonoRequestService monoRequestService;

    public MonoServiceImpl( RestTemplate restTemplate )
    {
        this.monoRequestService = new MonoRequestService(restTemplate);
    }

    @Override
    public Optional<UserInfoDto> getUserInfo( String privateToken )
    {
        return monoRequestService.request(GET_PERSONAL_CLIENT_INFO, UserInfoDto.class);
    }

    @Override
    public Optional<StatementItem[]> getUserStatement( String privateToken, String accountId, LocalDate from, LocalDate to )
    {
        LocalDateTime fromTime =  from.atTime(LocalTime.MIN);
        LocalDateTime toTime = to.atTime(LocalTime.MAX);
        return monoRequestService.request(String.join("/", GET_TRANSACTION_HISTORY, accountId,
                        Long.toString(fromTime.atZone(ZoneId.systemDefault()).toEpochSecond()),
                        Long.toString(toTime.atZone(ZoneId.systemDefault()).toEpochSecond())),
                        StatementItem[].class);
    }
}
