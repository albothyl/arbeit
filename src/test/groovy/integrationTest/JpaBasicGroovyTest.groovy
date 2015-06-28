package integrationTest

import com.job.BootApplication
import com.job.member.MemberGrade
import com.job.member.domain.MemberEntity
import com.job.member.domain.MemberRepository
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.util.Assert
import spock.lang.Specification

@IntegrationTest
@WebAppConfiguration
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = BootApplication.class)
class JpaBasicGroovyTest extends Specification {

    @Autowired
    MemberRepository memberRepository

    def "JPA INSERT, DELETE TEST"() {
        setup:
        def MemberEntity member = new MemberEntity()
        member.setEmail("testEmail_2@test.com")
        member.setName("testName_2")
        member.setNickName("testNickName_2")
        member.setMemberGrade(MemberGrade.GOLD)
        member.setUpdatedAt(new DateTime())
        member.setRegistedAt(new DateTime())

        when:
        def resultMemberSaved = memberRepository.save(member)
        then:
        Assert.notNull(resultMemberSaved)
        expect:
        memberRepository.delete(resultMemberSaved.getId())
    }

    def "select test"() {
        setup:
        def String userEmail = "jjhwqqq@naver.com"
        def MemberEntity member = memberRepository.findByEmail(userEmail)
        expect:
        Assert.notNull(member)
    }
}