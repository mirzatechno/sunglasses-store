pipeline {

    agent any

    environment {

        AWS_REGION = 'ap-south-1'

        ECR_REPO = '607709788195.dkr.ecr.ap-south-1.amazonaws.com/sunglasses-store'

        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    tools {

        maven 'maven3.9.12'
    }

    stages {

        stage('Checkout-Code') {

            steps {

                git 'https://github.com/mirzatechno/sunglasses-store.git'
            }
        }

        stage('Build-Package') {

            steps {

                sh 'mvn clean package'
            }
        }

        stage('Code-Analysis') {

            steps {

                sh '''
                mvn sonar:sonar \
                -Dsonar.projectKey=sunglasses-store \
                -Dsonar.host.url=http://SONARQUBE-SERVER:9000 \
                -Dsonar.login=YOUR_SONAR_TOKEN
                '''
            }
        }

        stage('Build-Docker-Image') {

            steps {

                sh 'docker build -t $ECR_REPO:$IMAGE_TAG .'
            }
        }

        stage('ECR-Login') {

            steps {

                sh '''
                aws ecr get-login-password --region $AWS_REGION \
                | docker login --username AWS --password-stdin 607709788195.dkr.ecr.ap-south-1.amazonaws.com
                '''
            }
        }

        stage('Push-Docker-Image') {

            steps {

                sh 'docker push $ECR_REPO:$IMAGE_TAG'
            }
        }

        stage('Deploy-To-EKS') {

            steps {

                sh '''

                aws eks update-kubeconfig \
                --region $AWS_REGION \
                --name prod-eks

                sed -i "s|IMAGE_PLACEHOLDER|$ECR_REPO:$IMAGE_TAG|g" k8s/sunglasses-store-deployment.yaml

                kubectl apply -f k8s/sunglasses-store-deployment.yaml

                kubectl apply -f k8s/sunglasses-store-hpa.yaml

                kubectl apply -f k8s/sunglasses-store-ingress.yaml

                kubectl rollout status deployment/sunglasses-store-deployment -n production
                '''
            }
        }
    }
}
