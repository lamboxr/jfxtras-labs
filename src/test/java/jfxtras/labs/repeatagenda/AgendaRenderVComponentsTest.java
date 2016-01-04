package jfxtras.labs.repeatagenda;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import javafx.scene.Parent;
import jfxtras.test.TestUtil;

public class AgendaRenderVComponentsTest extends ICalendarTestAbstract
{
    public Parent getRootNode()
    {
        Parent p = super.getRootNode();
        return p;
    }
    
    @Test
    public void canRenderVComponents()
    {
        // Add VComponents, listener in ICalendarAgenda 
        TestUtil.runThenWaitForPaintPulse( () -> {
            agenda.vComponents().add(getDaily2());
            agenda.vComponents().add(getWeekly2());
            agenda.vComponents().add(getWholeDayDaily3());
            agenda.vComponents().add(getIndividual1());
            agenda.vComponents().add(getIndividual2());
        });

        List<LocalDateTime> startDates = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedStartDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 0, 0)
              , LocalDateTime.of(2015, 11, 9, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 0, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 30)
              , LocalDateTime.of(2015, 11, 12, 0, 0)
              , LocalDateTime.of(2015, 11, 12, 10, 0)
              , LocalDateTime.of(2015, 11, 13, 10, 0)
              , LocalDateTime.of(2015, 11, 15, 0, 0)
                ));
        assertEquals(expectedStartDates, startDates);

        List<LocalDateTime> endDates = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedEndDates = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 9, 11, 30)
              , LocalDateTime.of(2015, 11, 11, 0, 0)
              , LocalDateTime.of(2015, 11, 11, 10, 45)
              , LocalDateTime.of(2015, 11, 11, 11, 30)
              , LocalDateTime.of(2015, 11, 12, 0, 0)
              , LocalDateTime.of(2015, 11, 12, 11, 30)
              , LocalDateTime.of(2015, 11, 13, 10, 45)
              , LocalDateTime.of(2015, 11, 14, 0, 0)
              , LocalDateTime.of(2015, 11, 17, 0, 0)
                ));
        assertEquals(expectedEndDates, endDates);
        
        TestUtil.runThenWaitForPaintPulse( () ->
        { // advance one week
            LocalDateTime date = agenda.getDisplayedLocalDateTime().plus(1, ChronoUnit.WEEKS);
            agenda.setDisplayedLocalDateTime(date);
        });
        List<LocalDateTime> startDates2 = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedStartDates2 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 15, 0, 0)
              , LocalDateTime.of(2015, 11, 15, 10, 0)
              , LocalDateTime.of(2015, 11, 18, 0, 0)
              , LocalDateTime.of(2015, 11, 18, 10, 0)
              , LocalDateTime.of(2015, 11, 21, 0, 0)
              , LocalDateTime.of(2015, 11, 21, 10, 0)
                ));
        assertEquals(expectedStartDates2, startDates2);
        List<LocalDateTime> endDates2 = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedEndDates2 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 15, 11, 30)
              , LocalDateTime.of(2015, 11, 17, 0, 0)
              , LocalDateTime.of(2015, 11, 18, 11, 30)
              , LocalDateTime.of(2015, 11, 20, 0, 0)
              , LocalDateTime.of(2015, 11, 21, 11, 30)
              , LocalDateTime.of(2015, 11, 23, 0, 0)
                ));
        assertEquals(expectedEndDates2, endDates2);
        
        TestUtil.runThenWaitForPaintPulse( () ->
        { // advance one week
            LocalDateTime date = agenda.getDisplayedLocalDateTime().plus(1, ChronoUnit.WEEKS);
            agenda.setDisplayedLocalDateTime(date);
        });
        List<LocalDateTime> startDates3 = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedStartDates3 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 23, 10, 0)
              , LocalDateTime.of(2015, 11, 24, 0, 0)
              , LocalDateTime.of(2015, 11, 24, 10, 0)
              , LocalDateTime.of(2015, 11, 25, 10, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 0)
                ));
        assertEquals(expectedStartDates3, startDates3);
        List<LocalDateTime> endDates3 = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedEndDates3 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2015, 11, 23, 10, 45)
              , LocalDateTime.of(2015, 11, 24, 11, 30)
              , LocalDateTime.of(2015, 11, 25, 10, 45)
              , LocalDateTime.of(2015, 11, 26, 0, 0)
              , LocalDateTime.of(2015, 11, 27, 10, 45)
                ));
        assertEquals(expectedEndDates3, endDates3);

        TestUtil.runThenWaitForPaintPulse( () ->
        { // advance ten years + one week
            LocalDateTime date = agenda.getDisplayedLocalDateTime().plus(10, ChronoUnit.YEARS).plus(1, ChronoUnit.WEEKS);
            agenda.setDisplayedLocalDateTime(date);
        });
        List<LocalDateTime> startDates4 = agenda.appointments()
                .stream()
                .map(a -> a.getStartLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedStartDates4 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2025, 11, 24, 10, 0)
              , LocalDateTime.of(2025, 11, 26, 10, 0)
              , LocalDateTime.of(2025, 11, 28, 10, 0)
                ));
        assertEquals(expectedStartDates4, startDates4);
        List<LocalDateTime> endDates4 = agenda.appointments()
                .stream()
                .map(a -> a.getEndLocalDateTime())
                .sorted()
                .collect(Collectors.toList());
        List<LocalDateTime> expectedEndDates4 = new ArrayList<LocalDateTime>(Arrays.asList(
                LocalDateTime.of(2025, 11, 24, 10, 45)
              , LocalDateTime.of(2025, 11, 26, 10, 45)
              , LocalDateTime.of(2025, 11, 28, 10, 45)
                ));
        assertEquals(expectedEndDates4, endDates4);
//        TestUtil.sleep(3000);
    }
    
    @Test
    public void addVComponentListenerTest()
    {
        
    }

}
