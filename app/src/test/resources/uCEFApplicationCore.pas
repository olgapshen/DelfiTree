unit uCEFApplicationCore;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

{$IFNDEF FPC}{$IFNDEF DELPHI12_UP}
  // Workaround for "Internal error" in old Delphi versions caused by uint64 handling
  {$R-}
{$ENDIF}{$ENDIF}

interface

uses
  {$IFDEF DELPHI16_UP}
    {$IFDEF MSWINDOWS}WinApi.Windows,{$ENDIF} System.Classes, System.UITypes,
    {$IFDEF FMX}uCEFLinuxTypes,{$ENDIF}
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows,{$ENDIF} Classes, {$IFDEF FPC}dynlibs,{$ENDIF}
  {$ENDIF}
  {$IFDEF LINUX}{$IFDEF FPC}xlib,{$ENDIF}{$ENDIF}
  uCEFTypes, uCEFInterfaces, uCEFBaseRefCounted, uCEFSchemeRegistrar;

const
  CEF_SUPPORTED_VERSION_MAJOR   = 96;