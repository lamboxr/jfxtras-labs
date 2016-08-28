package jfxtras.labs.icalendarfx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.labs.icalendarfx.components.VComponentBase;
import jfxtras.labs.icalendarfx.parameters.ParameterType;
import jfxtras.labs.icalendarfx.properties.calendar.CalendarScale;
import jfxtras.labs.icalendarfx.properties.calendar.Method;
import jfxtras.labs.icalendarfx.properties.calendar.ProductIdentifier;
import jfxtras.labs.icalendarfx.properties.calendar.Version;
import jfxtras.labs.icalendarfx.properties.component.misc.IANAProperty;
import jfxtras.labs.icalendarfx.properties.component.misc.NonStandardProperty;

public enum CalendarProperty
{
    CALENDAR_SCALE ("CALSCALE",
            Arrays.asList(ParameterType.VALUE_DATA_TYPES, ParameterType.OTHER),
             CalendarScale.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            CalendarScale property = CalendarScale.parse(contentLine);
            vCalendar.setCalendarScale(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            CalendarScale calendarScale = (CalendarScale) child;
            destination.setCalendarScale(calendarScale);
        }
    },
    // Miscellaneous
    IANA_PROPERTY (IANAProperty.REGISTERED_IANA_PROPERTY_NAMES.get(0), /** property name (one in list of valid names at {@link #IANAProperty} */
            Arrays.asList(ParameterType.values()), // all parameters allowed
            IANAProperty.class) // property class
    {

        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
            final ObservableList<IANAProperty> list;
            if (vCalendar.getIana() == null)
            {
                list = FXCollections.observableArrayList();
                vCalendar.setIana(list);
            } else
            {
                list = vCalendar.getIana();
            }
            IANAProperty child = IANAProperty.parse(contentLine);
            list.add(child);
            return child;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            final ObservableList<IANAProperty> list;
            if (destination.getIana() == null)
            {
                list = FXCollections.observableArrayList();
                destination.setIana(list);
            } else
            {
                list = destination.getIana();
            }
            list.add(new IANAProperty((IANAProperty) child));
        }

    },
    METHOD ("METHOD",
            Arrays.asList(ParameterType.VALUE_DATA_TYPES, ParameterType.OTHER),
            Method.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            Method property = Method.parse(contentLine);
            vCalendar.setMethod(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            Method method = (Method) child;
            destination.setMethod(method);
        }
    },
    // Miscellaneous
    NON_STANDARD ("X-", // property name (begins with X- prefix)
            Arrays.asList(ParameterType.values()), // all parameters allowed
            NonStandardProperty.class) // property class
    {

        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
            final ObservableList<NonStandardProperty> list;
            if (vCalendar.getNonStandard() == null)
            {
                list = FXCollections.observableArrayList();
                vCalendar.setNonStandard(list);
            } else
            {
                list = vCalendar.getNonStandard();
            }
            NonStandardProperty child = NonStandardProperty.parse(contentLine);
            list.add(child);
            return child;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            final ObservableList<NonStandardProperty> list;
            if (destination.getNonStandard() == null)
            {
                list = FXCollections.observableArrayList();
                destination.setNonStandard(list);
            } else
            {
                list = destination.getNonStandard();
            }
            list.add(new NonStandardProperty((NonStandardProperty) child));
        }

    },
    
    PRODUCT_IDENTIFIER ("PRODID",
            Arrays.asList(ParameterType.VALUE_DATA_TYPES, ParameterType.OTHER),
            ProductIdentifier.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            ProductIdentifier property = ProductIdentifier.parse(contentLine);
            vCalendar.setProductIdentifier(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            ProductIdentifier productIdentifier = (ProductIdentifier) child;
            destination.setProductIdentifier(productIdentifier);
        }
    },
    VERSION ("VERSION",
            Arrays.asList(ParameterType.VALUE_DATA_TYPES, ParameterType.OTHER),
            Version.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                return PropertyType.VERSION.parse(vCalendar, contentLines.get(0));
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            Version property = Version.parse(contentLine);
            vCalendar.setVersion(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            Version version = (Version) child;
            destination.setVersion(version);
        }
    };
    
    // Map to match up name to enum
    private static Map<String, CalendarProperty> enumFromNameMap = makeEnumFromNameMap();
    private static Map<String, CalendarProperty> makeEnumFromNameMap()
    {
        Map<String, CalendarProperty> map = new HashMap<>();
        CalendarProperty[] values = CalendarProperty.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].toString(), values[i]);
        }
        return map;
    }
    public static CalendarProperty enumFromName(String propertyName)
    {
        return enumFromNameMap.get(propertyName.toUpperCase());
    }
    
    // Map to match up class to enum
    private static Map<Class<? extends VElement>, CalendarProperty> enumFromClassMap = makeEnumFromClassMap();
    private static Map<Class<? extends VElement>, CalendarProperty> makeEnumFromClassMap()
    {
        Map<Class<? extends VElement>, CalendarProperty> map = new HashMap<>();
        CalendarProperty[] values = CalendarProperty.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].myClass, values[i]);
        }
        return map;
    }
    /** get enum from map */
    public static CalendarProperty enumFromClass(Class<? extends VElement> myClass)
    {
        return enumFromClassMap.get(myClass);
    }
    
    private Class<? extends VElement> myClass;
    public Class<? extends VElement> getElementClass() { return myClass; }
    
    private String name;
    @Override
    public String toString() { return name; }
    
    private List<ParameterType> allowedParameters;
    public List<ParameterType> allowedParameters() { return allowedParameters; }

    CalendarProperty(String name, List<ParameterType> allowedParameters, Class<? extends VElement> myClass)
    {
        this.name = name;
        this.allowedParameters = allowedParameters;
        this.myClass = myClass;
    }

    /** Parses string and sets property.  Called by {@link VComponentBase#parseContent()} */
    abstract public VChild parse(VCalendar vCalendar, String contentLine);
    
    abstract public void copyChild(VChild child, VCalendar destination);
}
