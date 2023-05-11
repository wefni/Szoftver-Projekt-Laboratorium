@echo off
REM cd parancs utan sajat mappatok ahol van a log fajl, FIGYELJETEK HOGY A '.' ELBASSZA
powershell.exe -noexit -Command "$PSDefaultParameterValues['*:Encoding'] = 'utf8';cd D:\BME\Projlab\Projlab4;Get-Content log.log -Wait"
