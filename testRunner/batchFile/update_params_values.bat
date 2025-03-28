@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

cd /d D:\QA\orangehrm-automation

SET configFile=src\test\resources\configurations\config.properties

SET pomFile=pom.xml

REM Replace values in config.properties
(for /F "tokens=1,* delims==" %%A in ('type "!configFile!"') do (
    if /I "%%A"=="BROWSER" (echo BROWSER=%BROWSER%)
    if /I "%%A"=="ENV" (echo ENV=%SERVER%)
    if /I "%%A"=="PARALLEL_MODE" (echo PARALLEL_MODE=%PARALLEL_MODE%)
    if /I "%%A"=="MAX_PARALLEL_TESTS" (echo MAX_PARALLEL_TESTS=%MAX_PARALLEL_TESTS%)
    if /I "%%A"=="MAX_RETRIES" (echo MAX_RETRIES=%MAX_RETRIES%)
    if not "%%A"=="BROWSER" if not "%%A"=="ENV" if not "%%A"=="PARALLEL_MODE" if not "%%A"=="MAX_PARALLEL_TESTS" if not "%%A"=="MAX_RETRIES" (echo %%A=%%B)
)) > "!configFile!.tmp"

move /Y "!configFile!.tmp" "!configFile!"

(for /F "tokens=*" %%A in ('type "!pomFile!"') do (
    set "line=%%A"
    if "!line!"=="<suiteXmlFile>sanity</suiteXmlFile>" (set "line=<suiteXmlFile>%SUITE%</suiteXmlFile>")
    if "!line!"=="<suiteXmlFile>regression</suiteXmlFile>" (set "line=<suiteXmlFile>%SUITE%</suiteXmlFile>")
    echo !line!
)) > "!pomFile!.tmp"

move /Y "!pomFile!.tmp" "!pomFile!"

mvn clean test

ENDLOCAL