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
		def String password = "1q2w3e"
		def String afterPassword = "c0c4a69b17a7955ac230bfc8db4a123eaa956ccf3c0022e68b8d4e2f5b699d1f"
		def String encryptionPassword = sha.encryption(password, Algorithm.SHA256.stringValue())
		expect:
		Assert.isTrue(afterPassword.equals(encryptionPassword))
	}
}