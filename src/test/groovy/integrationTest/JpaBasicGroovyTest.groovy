package integrationTest

import com.job.BootApplication
import com.job.member.Grade
import com.job.member.domain.Member
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
        def Member member = new Member()
        member.setEmail("testEmail_2@test.com")
        member.setPassword("testPassword_2")
        member.setName("testName_2")
        member.setNickName("testNickName_2")
        member.setGrade(Grade.GOLD)
        member.setUpdatedAt(new DateTime())
        member.setRegistedAt(new DateTime())

        when:
        def resultMemberSaved = memberRepository.save(member)
        then:
        Assert.notNull(resultMemberSaved)
        expect:
        memberRepository.delete(resultMemberSaved.getId())
    }
}