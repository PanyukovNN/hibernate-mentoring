package ru.panyukovnn.hibernatecourse.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RetryService {

    @Retryable(backoff = @Backoff(delay = 100, multiplier = 2))
    public void runWithRetry(Runnable runnable) {
        // Для логгирования ошибок при повторной попытке
        if (RetrySynchronizationManager.getContext().getRetryCount() > 0) {
            Throwable lastThrowable = RetrySynchronizationManager.getContext().getLastThrowable();
            log.warn(lastThrowable.getMessage(), lastThrowable);
        }

        runnable.run();
    }
}
