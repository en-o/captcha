@echo off
rem terminal encoding
chcp 65001
rem terminal title
title run java

rem Ensure that the assignment succeeds
setlocal enabledelayedexpansion

rem info
echo  (bat file path %0)
echo ....................................
echo ...... -f spring.profiles.active ...
echo ...... -p server.port            ...
echo ...... -h help                   ...
echo ....................................

set port_key=-p
set profiles_key=-f
set hepl_key=-h

set value_1=%~2
set value_2=%~4
set key_1=%~1
set key_2=%~3

SET PROJECT_PROPERTIES=
SET PROJECT_PORT=

set jarName=captcha-0.0.1-SNAPSHOT.jar
set port=



rem run java
rem param %1 %2 %3 %4
if "!profiles_key!"=="!key_1!" (
	set PROJECT_PROPERTIES=--spring.profiles.active=!value_1!
)
if "!port_key!"=="!key_1!" (
	set PROJECT_PORT=--server.port=!value_1!
	set port=!value_1!
)
if "!hepl_key!"=="!key_1!" (
	echo "[Usage]  -f <ÅäÖÃÎÄ¼þ> -p <¶Ë¿ÚºÅ>\n"
	goto finish

)


if "!profiles_key!"=="!key_2!" (
	set PROJECT_PROPERTIES=--spring.profiles.active=!value_2!
)

if "!port_key!"=="!key_2!" (
	set PROJECT_PORT=--server.port=!value_2!
	set port=!value_2!
)

rem dispose empty
if [!PROJECT_PROPERTIES!]==[] set PROJECT_PROPERTIES=--spring.profiles.active=prod
if [!PROJECT_PORT!]==[] set PROJECT_PORT=--server.port=9009

rem kill java
for /f "tokens=5" %%a in ('netstat /ano ^| findstr !port!') do taskkill /F /pid %%a
echo !jarName! stop ok


rem setting jvm
set JAVA_OPTS=-Xms512m
rem setting gc -XX:+UseConcMarkSweepGC -XX:+UseParNewGC || -XX:+UseParallelGC -XX:+UseParallelOldGC
set JAVA_OPTS=!JAVA_OPTS! -Xmx1024m -Xmn1024m -XX:+UseParallelGC -XX:+UseParallelOldGC
rem setting encoding
set JAVA_OPTS=!JAVA_OPTS! -Dfile.encoding=UTF-8



echo start activity-api
set curdir=%~dp0
cd /d !curdir!
echo !curdir!
java -jar !JAVA_OPTS!  !jarName! !PROJECT_PORT! !PROJECT_PROPERTIES!

echo !jarName! run ok

:finish
pause




