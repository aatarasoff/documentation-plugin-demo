package info.developerblog.documentation.plugin

import org.gradle.model.Managed
/**
 * Created by aleksandr on 16.04.16.
 */
@Managed
interface Documentation {
    void setName(String name)
    String getName()
}