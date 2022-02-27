pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                
                git url: 'https://github.com/Kanth81/jgsu-spring-petclinic.git', branch: 'main'
                sh './mvnw package'
              
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
