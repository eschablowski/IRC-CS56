package com.IRC.Communication.Reply;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashMap;
import java.util.Set;

import com.IRC.Communication.Message;
import com.IRC.Communication.Prefix;
import com.IRC.Communication.Reply.Exceptions.UnknownReplyException;
import java.lang.reflect.InvocationTargetException;

public abstract class Reply extends Message {
    protected Reply(Prefix prefix) {
        super(prefix);
    }
    private static HashMap<Integer, Class<Reply>> orderedRepliesCache;

    protected static HashMap<Integer, Class<Reply>> orderedReplies() {
        if (Reply.orderedRepliesCache != null)
            return Reply.orderedRepliesCache;
        final Set<Class<?>> replies = (new Reflections("com.IRC.Communication.Reply"))
                .get(Scanners.SubTypes.of(Reply.class).asClass());
        for (Class<?> reply : replies) {
            try {
                Integer replyNumber = (int) reply.getMethod("replyNumber").invoke(reply);
                if (!Reply.class.isAssignableFrom(reply))
                    break; // Skip any non-subclass subclasses
                @SuppressWarnings("unchecked")
                Class<Reply> castReply = (Class<Reply>) reply; // Convert our generic Class to Class<Reply>, since we
                                                               // only look for subtypes of Reply this is OK.
                orderedRepliesCache.put(replyNumber, castReply);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                // Basically Gloss Over all Errors, as they cannot happen
            }
        }
        return Reply.orderedRepliesCache;
    }

    public static Reply parse(String replyString) throws UnknownReplyException {
        int code = Integer.parseInt(replyString.split(" ", 2)[0]);
        if (!Reply.orderedReplies().containsKey(code))
            throw new UnknownReplyException();
        Class<Reply> replyClass = Reply.orderedReplies().get(code);
        if (replyClass == null)
            throw new UnknownReplyException();
        Reply reply;
        try {
            reply = (Reply) replyClass.getMethod("parse", String.class).invoke(replyClass, replyString.split(" ", 2)[1]);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            throw new UnknownReplyException();
        }
        return reply;
    }

    public abstract int replyNumber();
    public String toString() {
        return super.toString() + this.replyNumber();
    }
    public boolean isLastReply() {
        return true; // Assume this is the last as only list replies need this method
    }
}
