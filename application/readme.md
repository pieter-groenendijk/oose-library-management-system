(This file expects people to execute the commands in bash, it's simply what I know. It's most likely a little different 
on a Windows machine)

# Prerequisites
## Docker

## Docker Compose
Usually included with docker itself.

## .env
The application requires some configuration to be put in this file to run. Use the template to
use the default settings. 

**DO NOT PUBLISH USING THE DEFAULT SETTINGS, SECRETS WILL BE EXPOSED!**

### Creation
Create a copy of the template using:

```bash
cp .env.template .env
```

You're required to replace any missing values (usually secrets). You may also optionally change the default settings to your liking.


# Startup
All system services (application, database, etc.) can be started with one command if one is in the correct directory.

_Docker itself is expected to be already running_

Set working directory to application directory (The folder this readme is located in)
```bash
cd path/to/application
```

Start the actual application
```bash
sudo docker compose up --build --watch
```

`--build`: Rebuild the containers if their configuration has changed.
`--watch`: Will watch for source code changes to sync and restart services. 

## More automation
I made a simple script to make life easier. It automatically starts and cleans up
the application. When using linux I would advise adding it as a run configuration in your 
IDE. The script is located in the `project-root/scripts` directory.

# Shutdown
The following command will stop and remove the services.

```bash
sudo docker compose down 
```