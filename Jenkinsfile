node{
   properties(
      [
         buildDiscarder(
            logRotator(
               artifactDaysToKeepStr: '14', 
               artifactNumToKeepStr: '3', 
               daysToKeepStr: '4', 
               numToKeepStr: '3'
            )
         ), 
         disableConcurrentBuilds(), 
         githubProjectProperty(
            displayName: 'quarkus-dev-consumer', 
            projectUrlStr: 'https://github.com/jimboowens/quarkus-dev-consumer/'
         ), 
         rateLimitBuilds(
            [
               count: 1, 
               durationName: 'second', 
               userBoost: true
            ]
         )
      ]
   )
   stages{
      stage('Stage 1'){
         steps{
            echo 'Hello world!'
         }
      }
   }
}