package com.codestates.culinari.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;
    private List<Integer> barNumber;

    public MultiResponseDto(List<T> data, Page page, List<Integer> barNumber) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
        this.barNumber = barNumber;
    }

    public MultiResponseDto(List<T> data, Page page){
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() ,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

}

