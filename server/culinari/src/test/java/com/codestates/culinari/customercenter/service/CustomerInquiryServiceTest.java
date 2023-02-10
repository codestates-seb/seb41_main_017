package com.codestates.culinari.customercenter.service;

import com.codestates.culinari.config.security.dto.CustomPrincipal;
import com.codestates.culinari.customercenter.dto.request.CsInquiryPost;
import com.codestates.culinari.customercenter.entity.CsInquiry;
import com.codestates.culinari.customercenter.repository.CsInquiryRepository;
import com.codestates.culinari.customercenter.service.impl.CustomerInquiryServiceImpl;
import com.codestates.culinari.user.constant.GenderType;
import com.codestates.culinari.user.entitiy.Profile;
import com.codestates.culinari.user.repository.ProfileRepository;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.codestates.culinari.order.Stub.Stub.createPrincipal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 고객센터_1:1문의")
@ExtendWith(MockitoExtension.class)
class CustomerInquiryServiceTest {

    @InjectMocks
    private CustomerInquiryServiceImpl customerInquiryService;
    @Mock
    private CsInquiryRepository csInquiryRepository;
    @Mock
    private ProfileRepository profileRepository;

    @DisplayName("1:1문의를 양식대로 작성하면 , 문의를 등록한다.")
    @Test
    void givenCsInquiryInfo_whenSavingCsInquiry_thenCsInquiry() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Profile profile = createProfile(1L);
        CsInquiryPost csInquiryPost = createCsInquiryRequest();

        given(profileRepository.getReferenceById(anyLong()))
                .willReturn(profile);
        given(csInquiryRepository.save(any(CsInquiry.class)))
                .willReturn(null);

        //when
        customerInquiryService.createEnquire(principal, csInquiryPost);

        //then
        then(profileRepository).should().getReferenceById(anyLong());
        then(csInquiryRepository).should().save(any(CsInquiry.class));
    }

    @DisplayName("내가 문의한 내용들을 모두 가져온다.")
    @Test
    void givenNothing_whenRequesting_thenCsInquiryPage() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Pageable pageable = PageRequest.of(0, 10);
        CsInquiry csInquiry = createCsInquiry();
        Page<CsInquiry> page = new PageImpl<>(List.of(csInquiry));

        given(csInquiryRepository.findAll(any(Pageable.class)))
                .willReturn(page);

        //when
        customerInquiryService.readInquiriePage(principal, pageable);

        //then
        then(csInquiryRepository).should().findAll(any(Pageable.class));
    }

    @DisplayName("내가 작성한 1:1 문의를 삭제한다.")
    @Test
    void givenCsInquiryId_whenDeletingCsInquiry_thenNothing() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Long inquiryId = 1L;
        CsInquiry csInquiry = createCsInquiry(1L);

        willDoNothing().given(csInquiryRepository).deleteById(anyLong());
        given(csInquiryRepository.findById(anyLong()))
                .willReturn(Optional.of(csInquiry));

        //when
        customerInquiryService.deleteEnquire(principal, inquiryId);

        //then
        then(csInquiryRepository).should().deleteById(anyLong());
        then(csInquiryRepository).should().findById(anyLong());
    }

    @DisplayName("내가 작성한 1:1 문의를 수정한다.")
    @Test
    void givenCsInquiryId_whenUpdatingCsInquiry_thenCsInquiry() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Long inquiryId = 1L;
        CsInquiryPost csInquiryPost = createCsInquiryRequest();
        CsInquiry csInquiry = createCsInquiry(1L);

        given(csInquiryRepository.findById(anyLong()))
                .willReturn(Optional.of(csInquiry));

        //when
        customerInquiryService.updateEnquire(principal, inquiryId, csInquiryPost);

        //then
        then(csInquiryRepository).should().findById(anyLong());
    }

    @DisplayName("내가 선택한 1:1 문의를 읽는다.")
    @Test
    void givenCsInquiryId_whenRequesting_thenCsInquiry() {
        //given
        CustomPrincipal principal = createPrincipal("사용자 명", 1L, 1L);
        Long inquiryId = 1L;
        CsInquiry csInquiry = createCsInquiry(1L);

        given(csInquiryRepository.findById(anyLong()))
                .willReturn(Optional.of(csInquiry));

        //when
        customerInquiryService.readInquire(principal, inquiryId);

        //then
        then(csInquiryRepository).should().findById(anyLong());
    }

    private static Profile createProfile(Long profileId) {
        Profile profile = Profile.of(
                "사용자 명",
                "email@email.com",
                "010-0000-0000",
                BigDecimal.valueOf(0L),
                "주소",
                GenderType.MAN,
                LocalDate.now()
        );
        ReflectionTestUtils.setField(profile, "id", profileId);

        return profile;
    }

    private static CsInquiryPost createCsInquiryRequest() {
        return null;
//        return CsInquiryPost.of(
//                "test title",
//                "test content"
//        );
    }

    private static CsInquiry createCsInquiry(Long id){
        return null;
//        return CsInquiry.of(
//                id,
//                "test title",
//                "test content",
//                createProfile(id),
//                ProcessStatus.STAND_BY
//        );
    }

    private static CsInquiry createCsInquiry() {
        return createCsInquiryRequest().toDto(null).toEntity();
    }

}
