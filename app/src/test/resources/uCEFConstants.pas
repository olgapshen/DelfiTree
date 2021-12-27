unit uCEFConstants;

{$IFDEF FPC}
  {$MODE OBJFPC}{$H+}
{$ENDIF}

{$IFNDEF CPUX64}{$ALIGN ON}{$ENDIF}
{$MINENUMSIZE 4}

{$I cef.inc}

interface

{$IFDEF MSWINDOWS}
uses
  {$IFDEF DELPHI16_UP}
  Winapi.Messages;
  {$ELSE}
  Messages;
  {$ENDIF}
{$ENDIF}

const
  // compare the values in the right Chromium branch.
  ERR_NONE                                            = 0;

implementation

end.