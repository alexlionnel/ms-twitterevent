package io.demobrains.twitterevent.elastic.query.service.business.impl;

import io.demobrains.twitterevent.elastic.query.service.business.QueryUserService;
import io.demobrains.twitterevent.elastic.query.service.dataaccess.entity.UserPermission;
import io.demobrains.twitterevent.elastic.query.service.dataaccess.repository.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwitterQueryUserService implements QueryUserService {

    private final UserPermissionRepository userPermissionRepository;

    @Override
    public Optional<List<UserPermission>> findAllPermissionsByUsername(String username) {
        log.info("Finding permissions by username {}", username);
        return userPermissionRepository.findPermissionsByUsername(username);
    }
}