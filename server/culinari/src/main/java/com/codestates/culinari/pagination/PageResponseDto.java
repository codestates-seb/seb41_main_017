package com.codestates.culinari.pagination;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class PageResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;
    private Page<T> pageData;
    private Pageable pageable;
    private List<Integer> barNumber;
                                          ///int page, size
    public PageResponseDto(List<T> data, Page page, List<Integer> barNumber) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.barNumber = barNumber;
    }

    public PageResponseDto(List<T> data, Page page){
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    //페이지네이션의 호출 방식 변경 시 활용
    public PageResponseDto(List<T> data, Pageable pageable, List<Integer> barNumber){
        this.data = data;
        this.pageable = pageable;
        this.barNumber = barNumber;
    }
}
