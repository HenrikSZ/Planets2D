[Setup]
AppName="Planets2D"
AppVerName="Planets2D 1.0"
DefaultDirName="{pf}\planets2D\1.0"
OutputBaseFilename="Planets2D-1.0"
OutputDir="target\inno"
DefaultGroupName=Planets2D

[Files]
Source: "target\launch4j\bin\*"; DestDir: "{app}\bin"; Flags: recursesubdirs
Source: "target\launch4j\planets2D.exe"; DestDir: "{app}"

[Icons]
Name: "{group}\Planets2D"; Filename: "{app}\planets2D.exe"; WorkingDir: "{app}"
Name: "{userdesktop}\Planets2D"; Filename: "{app}\planets2D.exe"; WorkingDir: "{app}"