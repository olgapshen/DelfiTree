unit uCEFSchemeRegistrar;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

interface

uses
  uCEFBaseScopedWrapper, uCEFTypes;

type
  TCefSchemeRegistrarRef = class(TCEFBaseScopedWrapperRef)
    public
      function AddCustomScheme(const schemeName: ustring; options : TCefSchemeOptions): Boolean;
  end;

implementation

uses
  uCEFMiscFunctions;

end.