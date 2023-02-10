package com.codestates.culinari.customercenter.constant;

import lombok.Getter;

public enum ProcessStatus {
    STAND_BY("답변 대기중"),
    COMPLETE("답변 완료");

    @Getter
    private final String status;

    ProcessStatus(String status) {
        this.status = status;
    }
}
