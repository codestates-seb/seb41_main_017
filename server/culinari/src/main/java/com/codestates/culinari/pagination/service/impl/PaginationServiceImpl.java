package com.codestates.culinari.pagination.service.impl;

import com.codestates.culinari.pagination.service.PaginationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Transactional
@Service
public class PaginationServiceImpl implements PaginationService {

    private static final int BAR_LENGTH = 5;

    @Override
    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0 );
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    @Override
    public int currentBarLength(){ return BAR_LENGTH; }

}
