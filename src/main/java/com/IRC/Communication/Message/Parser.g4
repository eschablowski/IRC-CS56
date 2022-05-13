grammar Message;
message: (':' prefix ' ')? Command params CRLF;
prefix: servername | nick ('!' User)? ('@' Host)?;
Command: [a-zA-Z]+ | [0-9]{3};
params: ' ' (':' Trailing | Middle params)?;
Middle: [^ \n\r]+;
Trailing: [^ \n\r]*;
CRLF: [\n\r];

target: to | to ',' target;
to : channel | User '@' servername | nick | mask;
channel: '#' ChString | '&' ChString;
servername: Host;
Host: [a-Z0-9.]; /// TODO: add real hostname recognition per RFC 952
nick: Letter | Letter (Letter | Number | Special)*;
mask: '#' ChString | '$' ChString;
ChString: [^ \u0007\u0000\r\n,]+;

User: NonWhite+;
Letter: [a-Z];
Number: [0-9];
Special: '-' | '[' | ']' | '\\' | '`' | '^' | '{' | '}';
NonWhite: [^ \u0000\n\r];
