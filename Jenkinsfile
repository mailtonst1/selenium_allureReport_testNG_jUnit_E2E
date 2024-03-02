pipeline {
   agent any

   stages {
       stage('Checkout') {
           steps {
               script {
                   checkout scm
               }
           }
       }
       stage('Test') {
           steps {
               script {
                   bat 'mvn -e clean test -Dmaven.test.failure.ignore=true'
               }
           }
       }
       stage('Publish Allure Report') {
           steps {
               script {
                   bat 'allure generate target/allure-results -o target/allure-report'


                   archiveArtifacts 'target/allure-report/**'
               }
           }
       }
   }


   post {
       always {
           allure(
               includeProperties: false,
               jdk: '',
               results: [[path: 'allure-results']]
           )
           script {
               def buildUrl = env.BUILD_URL
               def buildResult = currentBuild.currentResult
               def branchName = env.BRANCH_NAME
               def buildNumber = env.BUILD_NUMBER

               def printAllure = bat(script: "cd C:\\Users\\rapha && node capture.js ${env.BUILD_NUMBER}", returnStdout: true).trim()
               def link = "abc"
               try {
                   def matcher = (printAllure =~ /https?:\/\/[^\s]+/)
                   link = matcher.find() ? matcher.group() : "Link não encontrado"
               } catch (Exception e) {
                   echo "Erro ao extrair o link da saída do comando: ${e.message}"
               }


               def message = "# Relatorio de Testes/API Chronos\n"
               message += "**Branch:** MAIN\n"
               message += "**Build:** ${buildNumber}\n"
               message += "**Status:** ${buildResult}\n"


               discordSend description: message,
                   image: "${link}",
                   webhookURL: "https://discord.com/api/webhooks/1213534267217416192/mkVDIcII8NCp1POBeFUA593L1Lin3v2Y7liKoJDfl_UMHaVMWcnLsn07-RQMPsismeXo"
           }
       }
   }
}
