package com.autrui.gateway.service;

import org.springframework.stereotype.Service;

/**
 * 鉴权服务
 *
 * @author Autrui
 * @date 2025/4/1
 */
@Service
public class AuthService {
    public boolean validateToken(String token) {
        // 调用 Dubbo 用户服务验证 token
        return true;
    }
}
