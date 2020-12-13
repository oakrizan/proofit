## Prerequisites

Agent username and password should be exported to environment 
variables before launching tests:

```
export AGENT_USERNAME=<provided-username>
export AGENT_PASSWORD=<provided-password>
```
Current version of tests is running on Chrome version 86.

**NB!** Make sure there is no active leave apply for dates from 22.12.2020
to 01.01.2021. In case it exists, please cancel it (Leave->My Leave) before running tests.


## Launching tests
Execute command in the same console, where previous 2 export commands were executed:

```
./gradlew test
```   
   
## Test results

Test results are available in the console (Gradle Test Logger)
