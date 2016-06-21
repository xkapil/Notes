/**
 * Created by kapil on 20/6/16.
 */

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MySqlTask extends DefaultTask {
    def hostname = 'localhost'
    def port = 3306
    def sql
    def username = 'root'
    def password = 'password'

    @TaskAction
    def runQuery() {
        def cmd = "mysql -u ${username} -p${password} -h ${hostname} -P ${port} -e "
        project.exec {
            println("Executing Command")
            commandLine = cmd.split().toList() + sql
        }
    }
}
