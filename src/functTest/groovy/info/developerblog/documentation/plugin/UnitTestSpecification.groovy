package info.developerblog.documentation.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
/**
 * Created by aleksandr on 23.04.16.
 */
class UnitTestSpecification extends Specification {
    Project project

    def setup() {
        project = new ProjectBuilder().build()
        project.buildscript.repositories {
            jcenter()
        }

        project.buildscript.dependencies {
            classpath 'org.asciidoctor:asciidoctor-gradle-plugin:1.5.3'
        }

        project.plugins.apply('org.asciidoctor.convert')
        project.plugins.apply(DocumentationPlugin.class)

        project.dependencies {
            asciidoctor 'org.asciidoctor:asciidoctorj:1.5.4'
            docs 'org.slf4j:slf4j-api:1.7.2'
        }
    }

    def cleanup() {
        project.projectDir.deleteDir()
    }

    def "execution of documentation distribution task is success"() {
        when:
        project

        then:
        project.getTasksByName('documentationDistZip', true).size() == 1
    }
}
