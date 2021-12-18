unit uCEFMiscFunctions;

{$I cef.inc}

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
  {$IFDEF MACOSX}
    {$ModeSwitch objectivec1}
  {$ENDIF}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$IFNDEF FPC}{$IFNDEF DELPHI12_UP}
  // Workaround for "Internal error" in old Delphi versions caused by uint64 handling
  {$R-}
{$ENDIF}{$ENDIF}

interface

uses
  {$IFDEF DELPHI16_UP}
    {$IFDEF MSWINDOWS}
      WinApi.Windows, WinApi.ActiveX, Winapi.ShellApi,
    {$ELSE}
      {$IFDEF MACOSX}Macapi.Foundation, FMX.Helpers.Mac, Macapi.AppKit,{$ENDIF}
    {$ENDIF}
    {$IFDEF FMX}FMX.Types, FMX.Platform,{$ENDIF} System.Types, System.IOUtils,
    System.Classes, System.SysUtils, System.UITypes, System.Math,
  {$ELSE}
    {$IFDEF MSWINDOWS}Windows, ActiveX, ShellApi,{$ENDIF}
    {$IFDEF DELPHI14_UP}Types, IOUtils,{$ENDIF} Classes, SysUtils, Math,
    {$IFDEF FPC}LCLType, LazFileUtils,{$IFNDEF MSWINDOWS}InterfaceBase, Forms,{$ENDIF}{$ENDIF}
    {$IFDEF LINUX}{$IFDEF FPC}
      ctypes, keysym, xf86keysym, x, xlib,
      {$IFDEF LCLGTK2}gtk2, glib2, gdk2, gtk2proc, gtk2int, Gtk2Def, gdk2x, Gtk2Extra,{$ENDIF}
    {$ENDIF}{$ENDIF}
  {$ENDIF}
  uCEFTypes, uCEFInterfaces, uCEFLibFunctions, uCEFResourceHandler, uCEFConstants;

const
  Kernel32DLL = 'kernel32.dll';