node {
   def mvnHome
   stage('Preparation') {
      // Get some code from a GitHub repository
      git 'https://github.com/PatrickStoffel/CompositeBuilder.git'
      // ** NOTE: This 'Maven 3' Maven tool must be configured in the global configuration.
      mvnHome = tool 'Maven 3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit 'target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}
