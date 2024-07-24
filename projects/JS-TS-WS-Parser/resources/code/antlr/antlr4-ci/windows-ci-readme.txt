The windows-ci dockerfile installs all prerequisites for a full antlr CI on windows, except the github runner itself.
This is because the github runner installer is interactive, and needs a recent token
To instantiate a runner, you need to:
 - build and run the windows-ci dockerfile (may take a while the first time)
 - initiate a runner agent in GitHub (project -> settings -> actions)
 - open a console to the docker instance (via Docker desktop)
 - cd actions-runner
 - powershell ./config.cmd --url https://github.com/antlr/antlr4 --token <SOME_TOKEN_HERE>
Install as a service, it will run automatically

