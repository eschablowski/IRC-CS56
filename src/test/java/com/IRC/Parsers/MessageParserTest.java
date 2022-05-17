import com.IRC.Parsers.MessageLexer;
import com.IRC.Parsers.MessageParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.List;

public class MessageParserTest {
    @Test
    public void testExampleMessages() throws Exception {
        MessageLexer l = new MessageLexer(new ANTLRInputStream(getClass().getResourceAsStream("/rfc.txt")));
        MessageParser p = new MessageParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                    int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        ParseTree line = p.lines();
        System.out.println(line.toStringTree());
        System.out.println("Test");
    }
}
