package io.demobrains.twitterevent.elastic.query.service.security;

import io.demobrains.twitterevent.dataconfig.config.ElasticQueryServiceConfigData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Qualifier("elastic-query-service-audience-validator")
@RequiredArgsConstructor
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final ElasticQueryServiceConfigData elasticQueryServiceConfigData;

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if(token.getAudience().contains(elasticQueryServiceConfigData.getCustomAudience())) {
            return OAuth2TokenValidatorResult.success();
        } else {
            OAuth2Error oAuth2Error = new OAuth2Error("invalid_token", "The required " +
                    "audience " + elasticQueryServiceConfigData.getCustomAudience() + " is missing", null);
            return OAuth2TokenValidatorResult.failure(oAuth2Error);
        }
    }
}
