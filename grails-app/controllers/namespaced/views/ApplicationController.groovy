package namespaced.views

import grails.core.GrailsApplication
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    static namespace = 'foo'

    def index() {
        respond([grailsApplication: grailsApplication, pluginManager: pluginManager], view: 'index')
    }

    def indexRespondNamespaceFallback() {
        // The expected behavior is that this searches for index.gson first in
        // the namespace path (e.g., views/foo/application/index.gson), and
        // when it doesn't find it, look for views/application/index.gson).
        respond(model: [grailsApplication: grailsApplication, pluginManager: pluginManager], view: 'index')
    }

    def indexRenderNamespaceFallback() {
        // Behaves as expected.
        render(model: [grailsApplication: grailsApplication, pluginManager: pluginManager], view: 'index')
    }
}
