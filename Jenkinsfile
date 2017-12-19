node {
   def mvnHome
   stage('Preparation') {
      // Get some code from a GitHub repository
      git 'https://github.com/PatrickStoffel/CompositeBuilder.git'
      // ** NOTE: This 'Maven 3' Maven tool must be configured in the global configuration.
      mvnHome = tool 'Maven 3'
   }
   stage('Build') {
     sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean org.jacoco:jacoco-maven-plugin:prepare-agent package"
   }
   stage('Results') {
      junit 'target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
   stage('SonarQube analysis') {
       def scannerHome = tool 'SonarQube Scanner 3';
       withSonarQubeEnv('local sonar qube') {
         sh "${scannerHome}/bin/sonar-scanner"
       }
     }
}
