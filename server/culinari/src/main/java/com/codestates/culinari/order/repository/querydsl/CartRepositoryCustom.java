package com.codestates.culinari.order.repository.querydsl;

import java.util.List;

public interface CartRepositoryCustom {
    void deleteAllByIdsAndProfile_Id(List<Long> cartIds, Long profileId);
    void deleteAllByOrderId(String orderId);
}
