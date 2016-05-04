package info.developerblog.documentation.plugin

import groovy.util.logging.Slf4j
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
/**
 * Created by aleksandr on 16.04.16.
 */
@Slf4j
class TestSpecification extends Specification {
  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()

  def buildFile

  def setup() {
    buildFile = testProjectDir.newFile('build.gradle')
  }

  def "execution of documentation distribution task is up to date"() {
    given:
    buildFile << """
              buildscript {
                repositories { jcenter() }
                dependencies {
                  classpath 'org.asciidoctor:asciidoctor-gradle-plugin:1.5.3'
                }
              }

              plugins {
                  id 'ru.jpoint.documentation'
              }

              apply plugin: 'org.asciidoctor.convert'

              docs {
                debug = true
              }

              dependencies {
                asciidoctor 'org.asciidoctor:asciidoctorj:1.5.4'
                docs 'org.slf4j:slf4j-api:1.7.2'
              }
          """
    when:
    def result = GradleRunner.create()
        .withProjectDir(testProjectDir.root)
        .withArguments('documentationDistZip')
        .withPluginClasspath()
        .build()

    then:
    result.task(":documentationDistZip").outcome == TaskOutcome.UP_TO_DATE
  }
}