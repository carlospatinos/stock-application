Feature: Version
    As a user
    I want to request app version
    So that I know which features are available

    Scenario: Retrieving app versionn
        Given the app is up and running version "123"
        When I request app version
        Then the version "123" should be returned