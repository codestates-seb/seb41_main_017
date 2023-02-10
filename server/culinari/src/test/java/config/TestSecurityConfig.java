package config;

import com.codestates.culinari.config.SecurityConfig;
import com.codestates.culinari.config.security.jwt.JwtTokenizer;
import org.springframework.context.annotation.Import;

@Import({SecurityConfig.class, JwtTokenizer.class/*, CustomUserDetailsService.class*/})

public class TestSecurityConfig {
//    @MockBean private UserRepository userRepository;
//
//    @BeforeTestMethod
//    public void securitySetUp() {
//        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(createUser()));
//    }
//
//    private Users createUser() {
//        Profile profile = Profile.of(
//                "사용자 명",
//                "email@email.com",
//                "010-0000-0000",
//                BigDecimal.valueOf(0L),
//                "머시깽시 거시기동 00동 000호",
//                GenderType.MAN,
//                LocalDate.now()
//        );
//        ReflectionTestUtils.setField(profile, "id", 1L);
//
//        Users user = Users.of(
//                1L,
//                "id",
//                "pw",
//                new ArrayList<>(),
//                profile
//        );
//        UserRole userRole = UserRole.of(RoleType.USER, user);
//        ReflectionTestUtils.setField(userRole, "id", 1L);
//        user.addUserRole(userRole);
//
//        return user;
//    }
}
