package jfxtras.labs.icalendarfx.properties.component.relationship;

import jfxtras.labs.icalendarfx.components.VEventNewInt;
import jfxtras.labs.icalendarfx.components.VFreeBusy;
import jfxtras.labs.icalendarfx.components.VJournalInt;
import jfxtras.labs.icalendarfx.components.VTodoInt;
import jfxtras.labs.icalendarfx.properties.PropertyBaseAltText;

/**
 * CONTACT
 * RFC 5545 iCalendar 3.8.4.2. page 109
 * 
 * This property is used to represent contact information or
 * alternately a reference to contact information associated with the
 * calendar component.
 * 
 * Example:
 * CONTACT:Jim Dolittle\, ABC Industries\, +1-919-555-1234
 * 
 * @author David Bal
 * @see VEventNewInt
 * @see VTodoInt
 * @see VJournalInt
 * @see VFreeBusy
 */
public class Contact extends PropertyBaseAltText<String, Contact>
{   
    public Contact(CharSequence contentLine)
    {
        super(contentLine);
    }
    
    public Contact(Contact source)
    {
        super(source);
    }
}
