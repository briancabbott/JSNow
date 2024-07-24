FROM mcr.microsoft.com/windows:20H2
RUN @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" Set-ExecutionPolicy Unrestricted
# install chocolatey
RUN @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "[System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
RUN choco install -y git.install
# --version=2.7.18
RUN choco install -y python2
# --version=3.10.4
RUN choco install -y python3
# --version=2.16.2
RUN choco install -y dart-sdk
RUN choco install -y kb3118401
RUN choco install -y powershell-core
RUN choco install -y pwsh
RUN choco install -y php
ADD ./php.ini "C:\tools\php81\php.ini"
ADD download-action-runner.ps1 ./download-action-runner.ps1
RUN pwsh ./download-action-runner.ps1
