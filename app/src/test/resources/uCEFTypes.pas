unit uCEFTypes;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

interface

uses
  {$IFDEF DELPHI16_UP}
    {$IFDEF MSWINDOWS}
      WinApi.Windows,
    {$ELSE}
      System.Types, {$IFDEF LINUX}uCEFLinuxTypes,{$ENDIF}
    {$ENDIF}
    System.Math;
  {$ELSE}
    {$IFDEF FPC}{$IFDEF LINUX}xlib, ctypes,{$ENDIF}{$ENDIF}
    {$IFDEF MSWINDOWS}Windows,{$ENDIF} Math;
  {$ENDIF}

type
  PCefStringWide = ^TCefStringWide;