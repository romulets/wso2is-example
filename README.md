# Wso2 IS Example
A simple[Wso2 Identity Server v5.30](https://docs.wso2.com/display/IS530/) example

- [Introduction](#introduction)
- [Setting up and Running](#setting-up-and-running)
- [Developing](#developing)
- [Contributing](docs/CONTRIBUTING.md)
- [Contact](#contact)

## Introduction

This project helps to understand how the communication flows shall happen and provides an example for further projects using java and the wso2 identity server.

This project has three entities which communicates among themselves:
- [Web Client (React)](src/main/resources/web-client)
- [Service Provider (Jersey)](src/main/java/romulets/wso2/rest)
- Wso2 Identity Server

## Setting up and Running

1. Set up the [playground example](https://docs.wso2.com/display/IS530/Basic+Client+Profile+with+Playground) given by wso2

2. `git clone` this project wherever you want on your computer

3. Copy [conf.prp](src/main/resources/conf.prp) to `${tomcat user's home path}/.wso2Example/` or update the file's location in  [AuthProperties.java](src/main/java/romulets/wso2/rest/util/AuthProperties.java)

4. Update the copied conf.prp with the correct values

5. Update [pom.xml](pom.xml) `<outputDirectory>` to matches with your webapps location (or remove it if you don't mind copying the .war after every mvn install)

6. `mvn install`

7. If you removed the `<outputDirectory>` deploy it manually to tomcat and open `http://localhost:8080/wso2Example/`


## Developing

The java API has nothing tricky to develop, just go on.

The react client is normal too, although you must copy the builded js and html files to the [webapp path](src/main/webapp). I've built some [scripts](scritps/) to make the developement and build easier, it works only for linux though. Feel free to build the windows' scripts and contribute to project!

_Obs: the web client built in react isn't the most wonderful code in the world. Feel free to improve it :smiley:_

## Contact

Any problems, critics, sugestions, warnings, messages or whatever please message me at `romulodefarias@gmail.com`
