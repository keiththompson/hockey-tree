#!/bin/sh

function copyEnvVarsToUserProperties {
    USER_PROPERTIES=$TRAVIS_BUILD_DIR"/user.properties"
    export USER_PROPERTIES
    if [ ! -f "$USER_PROPERTIES" ]; then
        touch $USER_PROPERTIES
        echo "sdk.dir=$ANDROID_HOME" >> $USER_PROPERTIES

        echo "bintray_username=$BINTRAY_USERNAME" >> $USER_PROPERTIES
        echo "bintray_key=$BINTRAY_KEY" >> $USER_PROPERTIES
        echo "repo_name=$REPO_NAME" >> $USER_PROPERTIES
        echo "group_id=$GROUP_ID" >> $USER_PROPERTIES
        echo "artifact_id=$ARTIFACT_ID" >> $USER_PROPERTIES
    fi
}
