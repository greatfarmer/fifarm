pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo "Testing!"
            }
        }
        stage('Deliver') {
            steps {
                pwd
                sh 'scp -i ${FIFARM_BUILD_KEY} ${FIFARM_JAR_FILE} ${FIFARM_BUILD_HOST}:${FIFARM_DIR}'
                sh 'ssh -i ${FIFARM_BUILD_KEY} ${FIFARM_BUILD_HOST} "cd ${FIFARM_DIR};./fifarm.sh restart"'
            }
        }
    }
}