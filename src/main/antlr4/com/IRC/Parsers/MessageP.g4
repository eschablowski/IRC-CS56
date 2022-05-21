grammar MessageP;

lines: line EOF | line lines;
line: message CRLF | reply CRLF;
message: Command ' ' params;
reply: ReplyCode ' ' params;
Command: LETTER+;
ReplyCode: DIGIT DIGIT DIGIT;
params: middleParam;// | params ' ' middleParam | ':' EndParam;
middleParam: ~('\u0000' | ' ' | '\n' | '\r' | ':') (~('\u0000' | ' ' | '\n' | '\r'))+;
EndParam: LETTER+;//~('\u0000' | ' ' | '\n' | '\r')+;
CRLF: '\n';

fragment LETTER : 'a' .. 'z' | 'A' .. 'Z';
fragment DIGIT : '0' .. '9';
