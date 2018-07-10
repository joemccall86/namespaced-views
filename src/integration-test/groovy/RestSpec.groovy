import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Integration
class RestSpec extends Specification {

    @Shared RestBuilder rest = new RestBuilder()
    @Shared String urlBase

    def setup() {
        urlBase = "http://localhost:${serverPort}"
    }

    @Unroll
    def 'view is rendered from outside the namespace path when the namespaced view does not exist - using #action'() {
        when: 'the other action is called'
        def response = rest.get("${urlBase}/foo/application/${action}") {
            accept 'application/json'
        }

        then: 'the response contains the right parameter'
        response.json.viewOutsideOfNamespacePath == true

        where:
        action << ['indexRespondNamespaceFallback', 'indexRenderNamespaceFallback']
    }
}
