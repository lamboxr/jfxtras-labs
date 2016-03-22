package jfxtras.labs.icalendar.properties.component.descriptive;

import jfxtras.labs.icalendar.properties.PropertyTextBase3;
import jfxtras.labs.icalendar.properties.PropertyType;

/**
 * COMMENT: RFC 5545 iCalendar 3.8.1.4. page 83
 * This property specifies non-processing information intended
 * to provide a comment to the calendar user
 * Example:
 * COMMENT:The meeting really needs to include both ourselves
     and the customer. We can't hold this meeting without them.
     As a matter of fact\, the venue for the meeting ought to be at
     their site. - - John
 */
public class Comment extends PropertyTextBase3<Comment>
{
    private final static String NAME = PropertyType.COMMENT.toString();

    public Comment(String propertyString)
    {
        super(NAME, propertyString);
    }
    
    public Comment(Comment comment)
    {
        super(comment);
    }
    
    public Comment() { super(NAME); }
}