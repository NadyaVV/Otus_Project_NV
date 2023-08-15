timeout(10) {
    node('maven') {
        timestamp {
            wrap([$class: 'BuildUser']) {
                summary = """|<b>Owner:</b> ${env.BUILD_USER}
                            |<b>Branch:</b> ${BRANCH}""".stripMargin()
                currentBuild.description = summary.replaceAll("\n", "<br>")
                owner_user_id = "${env.BUILD_USER_ID}"
            }
            yaml_object = readYaml $YAML_CONFIG
            for (key in params.keySet()) {
                {
                    System.setProperty(key, params[key])
                }
            }
            stage('Checkout') {
                checkout scm
            }
            stage("Run tests") {
                def exitCode = sh(
                        returnStatus: true,
                        script: """
                    mvn test -Dbrowser=$BROWSER -Dbrowser.version=$BROWSER_VERSION -Dwebdriver.base.url=$BASE_URL -Dwebdriver.remote.url=$GRID_URL
                    """
                )
                if(exitCode != 0) {
                    currentBuild.result = 'UNSTABLE'
                }
            }
            stage('Publish artifacts') {
                allure([results          : [[
                                                path: 'allure-results'
                                            ]],
                        disabled         : false,
                        reportBuildPolicy: 'ALWAYS'])
            }
        }
    }
}