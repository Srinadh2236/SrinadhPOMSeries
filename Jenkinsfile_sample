pipeline{
    
    agent any
    
    stages{
        stage("build"){
            steps{
                echo("Build the project")
            }
        }
        stage("Run Unit Test"){
            steps{
                echo("Run UTs")
            }
        }
        stage("Run integration test"){
            steps{
                echo("Run IT's")
            }
        }
        stage("Deploy to DEV"){
            steps{
                echo("deploy to dev")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("deploy to QA")
            }
        }
        stage("Run regression test cases on QA"){
            steps{
                echo("run test cases on QA")
            }
        }
        stage("Deploy to Stage"){
            steps{
                echo("deploy to stage")
            }
        }
        stage("Run sanity test cases on QA"){
            steps{
                echo("run sanity test case on QA")
            }
        }
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
    
}