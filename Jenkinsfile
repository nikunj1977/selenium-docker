pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                echo 'STEP_1--> BUILDING JAR FILES...'
                //sh
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                echo 'STEP_2--> BUILDING IMAGE...'
                //sh
                bat "docker build -t nbtest011/seleniumdocker ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //sh
                    echo 'STEP_3-->PUSHING IMAGE TO DOCKERHUB...'
			        bat "docker login --username=nbtest011 --password=nick@7528"
			        bat "docker push nbtest011/seleniumdocker:latest"
			    }                           
            }
        }
    }
}