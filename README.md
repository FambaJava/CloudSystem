# CloudSystem by Mika

This CloudSystem is based on a Wrapper and Master, which workes with our TeamSpeak Bot.

## Installation

You can [download](https://github.com/FambaJava/CloudSystem) the CloudSystem [here](https://github.com/FambaJava/CloudSystem).


```bash
java -jar Master.jar            - Start first the Master
java -jar Wrapper.jar           - Then start the Wrapper
```

## SetUp (how to start and edit)

You can setup all datas for your database at the first time you start the application.
Then there will be a command line, which asks you to type in your H2 Connection url, your host, your port, your username and your password.

##### If you want to edit your datas, go up into the application folder then go to /Cloud/Master/Configs/Config.json
##### There you will find the sub-item ´H2DB´, then you can change there what ever you want. Remember that the application only starts with a H2DB connection! Don´t delete important source.

##### preset datas:
##### url: jdbc:h2:tcp://localhost/~/test
##### host: localhost
##### port: 8082
##### user: la
##### password: leave this empty

## Important Commands

las | listallservers
```
All TeamSpeak server...

NAME - ID | IP-Adress:Port | Password

test1 - 1 | 127.0.0.1:2004 | asufb
test2 - 2 | 127.0.0.1:2511 | alskjfjaoisf4

All Minecraft server...

NAME - ID | IP-Adress:Port | ServerType

test1 - 1 | 127.0.0.1:2004 | STATIC
test2 - 2 | 127.0.0.1:2511 | DYNAMIC
```

restart | reload | rl

```
Restart the cloud...
0 plugins disabled.
Loading Library´s...
FileSystem in loading...
Files to load: 4
All files are loaded.
0 plugins loaded.
Connecting to DB...
Connected
Created tables
SetUp is loaded
Wrapper is offline.

You can now use commands...

Cloud is successfully restarted.

```

## IMPORTANT Software
### You have to and install H2 DataBase     

#### Windows:                          [download](https://h2database.com/h2-setup-2019-10-14.exe)
#### MacOS:                              [install directly](http://macappstore.org/h2/)
#### Linux (All OS can use that):        [Platform Indipendet](http://macappstore.org/h2/)


## License
You could only read and use it.
