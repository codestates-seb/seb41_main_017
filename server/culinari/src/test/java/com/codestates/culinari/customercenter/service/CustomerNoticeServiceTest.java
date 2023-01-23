package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.customercenter.dto.request.CsNoticePost;
import com.codestates.culinari.customercenter.dto.response.CsNoticeResponse;
import com.codestates.culinari.customercenter.entity.CsNotice;
import com.codestates.culinari.customercenter.repository.CsNoticeRepository;
import com.codestates.culinari.customercenter.service.impl.CustomerNoticeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 고객센터_공지사항")
@ExtendWith(MockitoExtension.class)
class CustomerNoticeServiceTest {

    @InjectMocks
    private CustomerNoticeServiceImpl customerNoticeService;
    @Mock
    private CsNoticeRepository csNoticeRepository;

    @DisplayName("공지사항폼을 양식대로 작성하면 , 공지사항을 작성한다.")
    @Test
    void givenCsNoticeInfo_whenSavingCsNotice_thenCsNotice() {
        //given
        CsNoticePost csNoticePost = createCsNoticePost();

        given(csNoticeRepository.save(any(CsNotice.class)))
                .willReturn(null);

        //when
        customerNoticeService.createNotice(csNoticePost);

        //then
        then(csNoticeRepository).should().save(any(CsNotice.class));
    }

    @DisplayName("공지사항 리스트를(페이지) 열람할 수 있다.")
    @Test
    void givenNothing_whenRequesting_thenCsNoticePageResponse() {
        //given
        Pageable pageable = PageRequest.of(0,10);
        CsNotice csNotice = createCsNotice();
        Page page = new PageImpl(List.of(csNotice));

        given(csNoticeRepository.findAll(any(Pageable.class)))
                .willReturn(page);

        //when
        customerNoticeService.readNoticePage(pageable);

        //then
        then(csNoticeRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("공지사항을(단일건) 열람할 수 있다.")
    @Test
    void givenCsNoticeId_whenRequesting_thenCsNotice() {
        //given
        Long noticeId = 1L;
        CsNotice csNotice = createCsNotice();

        given(csNoticeRepository.findById(anyLong()))
                .willReturn(Optional.of(csNotice));

        //when
        CsNoticeResponse csNoticeResponse = customerNoticeService.readNotice(noticeId);

        //then
        then(csNoticeRepository).should().findById(anyLong());
        assertThat(csNoticeResponse)
                .hasFieldOrPropertyWithValue("title", csNotice.getTitle())
                .hasFieldOrPropertyWithValue("content", csNotice.getContent());
    }

    private CsNoticePost createCsNoticePost() {
        return CsNoticePost.of(
                "test title",
                "test content"
        );
    }

    private CsNotice createCsNotice(){
        return createCsNoticePost().toDto().toEntity();
    }
}
