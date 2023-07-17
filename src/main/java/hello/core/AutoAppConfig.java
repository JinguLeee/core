package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정
        // basePackages = "hello.core.member",
        // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        // basePackageClasses = AutoAppConfig.class,
        // 기존 예제 코드 제외
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈이 우선권을 가진다. (자동 빈을 오버라이딩 해버린다.) application.properties에서 오버라이딩을 막을 수 있다. 막혀 있는게 기본 값
    //    @Bean(name = "memoryMemberRepository")
    //    MemberRepository memberRepository() {
    //        return new MemoryMemberRepository();
    //    }
}
