package integrationTest.com.job.common.util.encryption

import com.job.BootApplication
import com.job.common.util.encryption.Algorithm
import com.job.common.util.encryption.SHA
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
class encryptionTest extends Specification {
	@Autowired
	private SHA sha;

	def "SHA256 encryptionTest"() {
		setup:
		def String password = "testPassword"
		def String afterPassword = "fd5cb51bafd60f6fdbedde6e62c473da6f247db271633e15919bab78a02ee9eb"
		def String encryptionPassword = sha.encryption(password, Algorithm.SHA256.stringValue())
		expect:
		Assert.isTrue(afterPassword.equals(encryptionPassword))
	}
}