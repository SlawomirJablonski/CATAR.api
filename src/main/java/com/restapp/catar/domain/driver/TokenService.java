package com.restapp.catar.domain.driver;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    private LoadingCache<Driver, Long> driverCache;

    public TokenService() {
        this.driverCache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterAccess(5, TimeUnit.MINUTES) //Narazie do testow
                .build(
                        new CacheLoader<Driver, Long>() {
                            @Override
                            public Long load(Driver key) throws Exception {
                                return generateToken();
                            }
                        }
                );
    }

    private Long generateToken() {
        return UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE;
    }

    public LoadingCache<Driver, Long> getDriverCache() {
        return driverCache;
    }
}
