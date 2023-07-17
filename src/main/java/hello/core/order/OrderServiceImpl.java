package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 조회 빈이 2개 이상 - 문제

    /*
     * 1. @Autowired 필드 명 매칭
     * 타입 매칭 -> 결과가 2개 이상일 때, 필드 명, 파라미터 명으로 빈 이름 매칭
     */
    // @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = rateDiscountPolicy;
    // }

    /*
     * 2. 추가 구분자 - @Qualifier 사용
     * @Qualifier끼리 매칭 -> 없으면 빈 이름 매칭 -> 없으면 NoSuchBeanDefinitionException 예외 발생
     */
    // @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    /*
     * 3. 우선순위를 - @Primary
     * 우선순위를 가질 구현체에 @Primary를 붙임
     */
    // @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
