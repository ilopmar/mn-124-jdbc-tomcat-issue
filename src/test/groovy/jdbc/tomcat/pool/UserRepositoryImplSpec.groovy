package jdbc.tomcat.pool

import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class UserRepositoryImplSpec extends Specification {

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.build()
        .packages('jdbc.tomcat.pool')
        .start()

    void 'test list'() {
        given:
            UserRepository userRepository = applicationContext.getBean(UserRepository)

        expect:
            userRepository.findAll() == []
    }

    void 'test polling conditions'() {
        given:
            UserRepository userRepository = applicationContext.getBean(UserRepository)

        expect:
            new PollingConditions(timeout: 20).eventually {
                assert userRepository.findAll().size() > 0
            }
    }
}
