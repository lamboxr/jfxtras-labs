package jfxtras.labs.icalendarfx.property;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.Test;

import jfxtras.labs.icalendarfx.parameters.CalendarUser.CalendarUserType;
import jfxtras.labs.icalendarfx.parameters.GroupMembership;
import jfxtras.labs.icalendarfx.parameters.ParameterEnum;
import jfxtras.labs.icalendarfx.parameters.Participation.ParticipationStatus;
import jfxtras.labs.icalendarfx.parameters.ParticipationRole.ParticipationRoleType;
import jfxtras.labs.icalendarfx.properties.component.relationship.Attendee;

public class AttendeeTest
{
    @Test
    public void canParseAttendee1() throws URISyntaxException
    {
        String content = "ATTENDEE;MEMBER=\"mailto:DEV-GROUP@example.com\":mailto:joecool@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:joecool@example.com").withGroupMembership("\"mailto:DEV-GROUP@example.com\"");
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }

    @Test
    public void canParseAttendee2() throws URISyntaxException
    {
        String content = "ATTENDEE;MEMBER=\"mailto:projectA@example.com\",\"mailto:projectB@example.com\":mailto:joecool@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:joecool@example.com")
                .withGroupMembership(
                        new GroupMembership(
                                Arrays.asList(new URI("mailto:projectA@example.com"), new URI("mailto:projectB@example.com"))));
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee3() throws URISyntaxException
    {
        String content = "ATTENDEE;CN=\"John Smith\":mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withCommonName("John Smith");
        assertEquals(expectedProperty, madeProperty);
        assertEquals("ATTENDEE;CN=John Smith:mailto:jsmith@example.com", expectedProperty.toContentLine()); // quotes should be removed from common name
    }
    
    @Test
    public void canParseAttendee4() throws URISyntaxException
    {
        String content = "ATTENDEE;CUTYPE=GROUP:mailto:ietf-calsch@example.org";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:ietf-calsch@example.org")
                .withCalendarUser(CalendarUserType.GROUP);
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee5() throws URISyntaxException
    {
        String content = "ATTENDEE;DELEGATED-FROM=\"mailto:jsmith@example.com\":mailto:jdoe@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jdoe@example.com")
                .withDelegators("mailto:jsmith@example.com");
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee6() throws URISyntaxException
    {
        String content = "ATTENDEE;DELEGATED-TO=\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\":mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withDelegatees("\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\"");
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee7() throws URISyntaxException
    {
        String content = "ATTENDEE;RSVP=TRUE:mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withRSVP(true);
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee8() throws URISyntaxException
    {
        String content = "ATTENDEE;ROLE=CHAIR:mailto:mrbig@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:mrbig@example.com")
                .withParticipationRole(ParticipationRoleType.CHAIR);
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee9() throws URISyntaxException
    {
        String content = "ATTENDEE;PARTSTAT=DECLINED:mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withParticipation(ParticipationStatus.DECLINED);
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee10() throws URISyntaxException
    {
        String content = "ATTENDEE;SENT-BY=\"mailto:sray@example.com\":mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withSentBy("mailto:sray@example.com");
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test
    public void canParseAttendee11() throws URISyntaxException
    {
        String content = "ATTENDEE;CN=John Smith;CUTYPE=GROUP;DELEGATED-TO=\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\";DELEGATED-FROM=\"mailto:jsmith@example.com\";MEMBER=\"mailto:projectA@example.com\",\"mailto:projectB@example.com\";ROLE=CHAIR;PARTSTAT=DECLINED;RSVP=TRUE;SENT-BY=\"mailto:sray@example.com\":mailto:jsmith@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:jsmith@example.com")
                .withGroupMembership(Arrays.asList(new URI("mailto:projectA@example.com"), new URI("mailto:projectB@example.com")))
                .withCommonName("John Smith")
                .withCalendarUser(CalendarUserType.GROUP)
                .withDelegators("mailto:jsmith@example.com")
                .withDelegatees("\"mailto:jdoe@example.com\",\"mailto:jqpublic@example.com\"")
                .withRSVP(true)
                .withParticipationRole(ParticipationRoleType.CHAIR)
                .withParticipation(ParticipationStatus.DECLINED)
                .withSentBy("mailto:sray@example.com");
        expectedProperty.parameterSortOrder().put(ParameterEnum.COMMON_NAME, 0);
        expectedProperty.parameterSortOrder().put(ParameterEnum.CALENDAR_USER_TYPE, 1);
        expectedProperty.parameterSortOrder().put(ParameterEnum.DELEGATEES, 2);
        expectedProperty.parameterSortOrder().put(ParameterEnum.DELEGATORS, 3);
        expectedProperty.parameterSortOrder().put(ParameterEnum.GROUP_OR_LIST_MEMBERSHIP, 4);
        expectedProperty.parameterSortOrder().put(ParameterEnum.PARTICIPATION_ROLE, 5);
        expectedProperty.parameterSortOrder().put(ParameterEnum.PARTICIPATION_STATUS, 6);
        expectedProperty.parameterSortOrder().put(ParameterEnum.RSVP_EXPECTATION, 7);
        expectedProperty.parameterSortOrder().put(ParameterEnum.SENT_BY, 8);
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
    }
    
    @Test // test non-standard ROLE
    public void canParseAttendee12() throws URISyntaxException
    {
        String content = "ATTENDEE;ROLE=GRAND POOBAH:mailto:mrbig@example.com";
        Attendee madeProperty = Attendee.parse(content);
        Attendee expectedProperty = Attendee.parse("mailto:mrbig@example.com")
                .withParticipationRole("GRAND POOBAH");
        assertEquals(expectedProperty, madeProperty);
        assertEquals(content, expectedProperty.toContentLine());
        assertEquals(madeProperty.getParticipationRole().getValue(), ParticipationRoleType.UNKNOWN);
    }
}