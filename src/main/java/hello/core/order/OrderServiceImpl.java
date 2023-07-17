package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /*
    * 1. 생성자 주입 -> 생성자 호출 시점에 딱 1번만 호출되는 것이 보장. 불변, 필수일 때
    * 생성자가 하나만 있을 때는 @Autowired 생략 가능 (스프링 빈에만 해당)
    * */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
     * 2. 수정자 주입(setter 주입) -> 선택, 변경일 때
     * 선택적 : @Autowired(required = false) 로 지정
     * */
    //        private MemberRepository memberRepository;
    //        private DiscountPolicy discountPolicy;
    //
    //        @Autowired
    //        public void setMemberRepository(MemberRepository memberRepository) {
    //            this.memberRepository = memberRepository;
    //        }
    //
    //        @Autowired
    //        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    //            this.discountPolicy = discountPolicy;
    //        }

    /*
     * 3. 필드 주입 -> 권장하지 않음. 외부에서 변경이 불가능해서 테스트 하기 힘듦
     * */
    // @Autowired private final MemberRepository memberRepository;

    /*
     * 4. 일반 메서드 주입 -> 잘 사용하지 않음
     * */
    //    private MemberRepository memberRepository;
    //    @Autowired
    //    public void init(MemberRepository memberRepository) {
    //        this.memberRepository = memberRepository;
    //    }

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
