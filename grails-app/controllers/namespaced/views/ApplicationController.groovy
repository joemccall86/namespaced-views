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

    def indexAbsolutePath() {
        respond([grailsApplication: grailsApplication, pluginManager: pluginManager], view: '/absolute/path/index')
    }

    def indexAbsolutePathWithRender() {
        render(model: [grailsApplication: grailsApplication, pluginManager: pluginManager], view: '/absolute/path/index')
    }
}
