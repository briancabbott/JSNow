Invoke-WebRequest -Uri https://github.com/actions/runner/releases/download/v2.289.2/actions-runner-win-x64-2.289.2.zip -OutFile actions-runner-win-x64-2.289.2.zip
mkdir actions-runner
Add-Type -AssemblyName System.IO.Compression.FileSystem ; [System.IO.Compression.ZipFile]::ExtractToDirectory("$PWD/actions-runner-win-x64-2.289.2.zip", "$PWD/actions-runner")
del $PWD/actions-runner-win-x64-2.289.2.zip