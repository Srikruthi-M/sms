pipeline {
    agent any

    environment {
        IMAGE_NAME = "smsapplication:v1"          // Docker image name (without username, username added in commands)
        DEPLOYMENT_NAME = "sms-deployment"         // Kubernetes deployment name
        NAMESPACE = "default"                        // Kubernetes namespace
    }

    stages {

        stage('Docker Build & Push') {
            steps {
                // DockerHub credentials
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds', 
                    usernameVariable: 'DOCKER_USER', 
                    passwordVariable: 'DOCKER_PASS')]) {

                    // Login to DockerHub
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'

                    // Build Docker image with DockerHub username
                    bat 'docker build -t %DOCKER_USER%/%IMAGE_NAME% .'

                    // Push Docker image to DockerHub
                    bat 'docker push %DOCKER_USER%/%IMAGE_NAME%'
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                // Use kubeconfig stored as Jenkins secret file
                withCredentials([file(credentialsId: 'kubeconfig-file', variable: 'KUBECONFIG')]) {

                    // Set KUBECONFIG environment variable
                    bat 'set KUBECONFIG=%KUBECONFIG%'

                    // Apply deployment (deployment.yaml should reference the image)
                    bat 'kubectl apply -f deployment.yaml'

                    // Check rollout status
                    bat 'kubectl rollout status deployment/%DEPLOYMENT_NAME% -n %NAMESPACE%'

                    // List pods in namespace
                    bat 'kubectl get pods -n %NAMESPACE%'
                }
            }
        }

    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
