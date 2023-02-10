package com.codestates.culinari.user.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.user.dto.response.MyPageCountResponse;

public interface MyPageService {
    MyPageCountResponse getMyPageCountData(CustomPrincipal principal);
}
