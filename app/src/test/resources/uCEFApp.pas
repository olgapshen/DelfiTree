unit uCEFApp;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

interface

uses
  {$IFDEF DELPHI16_UP}
  System.Classes, System.UITypes,
  {$ELSE}
  Classes,
  {$ENDIF}
  uCEFTypes, uCEFInterfaces, uCEFBaseRefCounted, uCEFSchemeRegistrar, uCEFApplicationCore;

type
  TCefAppOwn = class(TCefBaseRefCountedOwn, ICefApp)
    protected
      procedure OnBeforeCommandLineProcessing(const processType: ustring; const commandLine: ICefCommandLine); virtual; abstract;
      procedure OnRegisterCustomSchemes(const registrar: TCefSchemeRegistrarRef); virtual; abstract;
      procedure GetResourceBundleHandler(var aHandler : ICefResourceBundleHandler); virtual; abstract;
      procedure GetBrowserProcessHandler(var aHandler : ICefBrowserProcessHandler); virtual; abstract;
      procedure GetRenderProcessHandler(var aHandler : ICefRenderProcessHandler); virtual; abstract;

      procedure RemoveReferences; virtual; abstract;

    public
      constructor Create; virtual;
  end;