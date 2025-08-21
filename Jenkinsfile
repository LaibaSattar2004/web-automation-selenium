pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   // Configure Maven in Jenkins (Manage Jenkins > Global Tool Config)
        jdk 'JAVA_HOME'      // Configure JDK in Jenkins
    }

    environment {
        BROWSER = "chrome"   // default browser
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean compile -Dbrowser=%BROWSER%"
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -Dbrowser=%BROWSER%"
            }
        }

        stage('Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
        failure {
            mail to: 'your_email@example.com',
                 subject: "❌ Test Automation Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins for details: ${env.BUILD_URL}"
        }
        success {
            mail to: 'your_email@example.com',
                 subject: "✅ Test Automation Build Passed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "All tests executed successfully. See reports at: ${env.BUILD_URL}"
        }
    }
}
