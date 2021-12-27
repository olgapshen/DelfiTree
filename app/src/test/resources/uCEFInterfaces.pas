unit uCEFInterfaces;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

interface

uses
  {$IFDEF DELPHI16_UP}
    {$IFDEF MSWINDOWS}WinApi.Windows,{$ENDIF} System.Classes,
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows,{$ENDIF} Classes,
  {$ENDIF}
  uCEFTypes, uCEFSchemeRegistrar, uCEFConstants;

type
  ICefBrowser = interface;