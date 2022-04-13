package com.holep.readingisgood.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    final InfoLogRepository infoLogRepository;
    final ErrorLogRepository errorLogRepository;

    @Override
    public void info(InfoLog infoLog) {

        infoLog.setId(UUID.randomUUID());
        infoLog.setCreationDate(new Date());

        infoLogRepository.save(infoLog);
    }

    @Override
    public void error(ErrorLog errorLog) {

        errorLog.setId(UUID.randomUUID());
        errorLog.setCreationDate(new Date());

        errorLogRepository.save(errorLog);
    }
}
