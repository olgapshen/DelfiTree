unit uCEFBaseScopedWrapper;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

interface

type
  TCEFBaseScopedWrapperRef = class
    protected
      FData: Pointer;

    public
      constructor Create(data: Pointer); virtual;
      function    Wrap: Pointer;
  end;

implementation