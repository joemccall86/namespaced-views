import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification

@Integration
class RestSpec extends Specification {

    @Shared RestBuilder rest = new RestBuilder()
    @Shared String urlBase

    def setup() {
        urlBase = "http://localhost:${serverPort}"
    }

    def 'view is rendered from namespace convention'() {
        when: 'the index action is called'
        def response = rest.get("${urlBase}/foo/application/index") {
            accept 'application/json'
        }

        then: 'the response contains the right parameter'
        response.json.fromAbsolutePath == false


    }

    def 'view is rendered from absolute view path'() {
        when: 'the other action is called'
        def response = rest.get("${urlBase}/foo/application/indexAbsolutePath") {
            accept 'application/json'
        }

        then: 'the response contains the right parameter'
        response.json.fromAbsolutePath == true
    }
}
