grammar Message;
message: (':' prefix ' ')? Command params CRLF;
prefix: Servername | Nick ('!' User)? ('@' Servername)?;
Command: [a-zA-Z]+ | [0-9][0-9][0-9];
params: ' ' (':'  Param params)?;
Param: [^ \n\r]+;
CRLF: [\n\r];

target: to | to ',' target;
to : channel | User '@' Servername | Nick | mask;
channel: '#' ChString | '&' ChString;
Servername: [a-zA-Z0-9.]; /// TODO: add real hostname recognition per RFC 952
Nick: [a-zA-Z]([a-zA-Z0-9]|'-'|'['|'\\'|'`'|'^'|'{'|'}')*;
mask: '#' ChString | '$' ChString;
ChString: [^ \u0007\u0000\r\n,]+;

User: [^ \u0000\n\r]+;
