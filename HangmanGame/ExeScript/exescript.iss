; Script generated by the Inno Script Studio Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{076C3A49-48C6-4908-9698-530D2F094490}
AppName=HangmanGame
AppVersion=1.0
;AppVerName=HangmanGame 1.0
AppPublisher=SmallGame Inc.
AppPublisherURL=https://github.com/moutyque/HangmanGame
AppSupportURL=https://github.com/moutyque/HangmanGame
AppUpdatesURL=https://github.com/moutyque/HangmanGame
DefaultDirName={pf}\HangmanGame
DefaultGroupName=HangmanGame
AllowNoIcons=yes
OutputDir=C:\Users\Quentin\git\HangmanGame\HangmanGame\ExeScript
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Files]
Source: "C:\Users\Quentin\git\HangmanGame\HangmanGame\HangmanGameFXViews\build\deploy\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{cm:UninstallProgram,HangmanGame}"; Filename: "{uninstallexe}"
