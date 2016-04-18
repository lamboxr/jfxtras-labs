package jfxtras.labs.icalendarfx.properties.component.time;

import java.time.ZonedDateTime;

import jfxtras.labs.icalendarfx.components.VTodoInt;
import jfxtras.labs.icalendarfx.properties.PropertyBaseUTC;

/**
 * COMPLETED
 * Date-Time Completed
 * RFC 5545, 3.8.2.1, page 94
 * 
 * This property defines the date and time that a to-do was actually completed.
 * The value MUST be specified as a date with UTC time.
 * 
 * Example:
 * COMPLETED:19960401T150000Z
 * 
 * @author David Bal
 *
 * The property can be specified in following components:
 * @see VTodoInt
 */
public class DateTimeCompleted extends PropertyBaseUTC<DateTimeCompleted>
{
    public DateTimeCompleted(ZonedDateTime temporal)
    {
        super(temporal);
    }

    public DateTimeCompleted(CharSequence contentLine)
    {
        super(contentLine);
    }
    
    public DateTimeCompleted(DateTimeCompleted source)
    {
        super(source);
    }
}
