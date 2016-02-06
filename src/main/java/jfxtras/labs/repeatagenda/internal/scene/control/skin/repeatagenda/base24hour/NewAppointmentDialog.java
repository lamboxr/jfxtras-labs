package jfxtras.labs.repeatagenda.internal.scene.control.skin.repeatagenda.base24hour;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;

/**
 * Produces new appointment dialog
 * allows changing summary and appointmentGroup (category)
 * has buttons to create event, cancel, or do advanced edit
 * 
 * @author David Bal
 *
 */
public class NewAppointmentDialog extends Dialog<ButtonData>
{
    /**
     * 
     * @param appointment : new appointment made by Agenda
     * @param appointmentGroups : categories
     * @param resources : bundle with language-specific strings
     */
    public NewAppointmentDialog(
            Appointment appointment
          , ObservableList<AppointmentGroup> appointmentGroups
          , Callback<Appointment, Void> iCalendarEditPopupCallback
          , ResourceBundle resources)
    {
        initModality(Modality.APPLICATION_MODAL);
        setTitle(resources.getString("dialog.event.new.title"));
        DateTimeFormatter startFormatter = DateTimeFormatter.ofPattern(resources.getString("date.format.agenda.start"));
        DateTimeFormatter endFormatter = DateTimeFormatter.ofPattern(resources.getString("date.format.agenda.end"));
        String start = startFormatter.format(appointment.getStartLocalDateTime());
        String end = endFormatter.format(appointment.getEndLocalDateTime());
        String appointmentTime = start + end + " ";
        setHeaderText(appointmentTime);
        
        // Buttons
        ButtonType createButton = new ButtonType(resources.getString("dialog.event.new.create"), ButtonData.OK_DONE);
        ButtonType editButton = new ButtonType(resources.getString("dialog.event.new.edit"), ButtonData.OTHER);
        getDialogPane().getButtonTypes().addAll(createButton, editButton, ButtonType.CANCEL);
        getDialogPane().lookupButton(createButton).setId("createButton");
        getDialogPane().lookupButton(editButton).setId("editButton");
        getDialogPane().lookupButton(ButtonType.CANCEL).setId("cancelButton");
        
        // Edit Summary and appointmentGroup
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField summaryTextField = new TextField(appointment.getSummary());
        summaryTextField.setId("summaryTextField");
        summaryTextField.setPromptText(resources.getString("new.event"));
        summaryTextField.textProperty().addListener((obs, oldValue, newValue) -> appointment.setSummary(newValue));
        
        ComboBox<AppointmentGroup> appointmentGroupComboBox = new ComboBox<>();
        appointmentGroupComboBox.setId("appointmentGroupComboBox");
        appointmentGroupComboBox.setItems(appointmentGroups);
        appointmentGroupComboBox.getSelectionModel().select(appointment.getAppointmentGroup());
        appointmentGroupComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                appointment.setAppointmentGroup(newSelection)); 
        
        // Can't use below map because it results in an error: after clicking on a selection the graphic disappears.
        Map<AppointmentGroup, Node> iconMap = appointmentGroups.stream()
                .collect(Collectors.toMap(
                        g -> g
                      , g -> 
                        {
                            Node icon = new Rectangle(20,20);
                            icon.getStyleClass().add(g.getStyleClass());
                            return icon;
                        }));
        
        Callback<ListView<AppointmentGroup>, ListCell<AppointmentGroup>> cellFactory = p -> new ListCell<AppointmentGroup>()
        {
            @Override protected void updateItem(AppointmentGroup item, boolean empty)
            {
                super.updateItem(item, empty);                
                if (item == null || empty)
                {
                    setGraphic(null);
                    setText(null);
                } else
                {
                    Rectangle icon = new Rectangle(20,20);
                    icon.setArcWidth(6);
                    icon.setArcHeight(6);
                    icon.getStyleClass().add(item.getStyleClass());
                    setGraphic(icon);
                    setText(item.getDescription());
                }
            }
        };
        appointmentGroupComboBox.setCellFactory(cellFactory);
        appointmentGroupComboBox.setButtonCell(cellFactory.call(null));

        grid.add(new Label(resources.getString("summary")), 0, 0);
        grid.add(summaryTextField, 1, 0);
        grid.add(new Label(resources.getString("category")), 0, 1);
        grid.add(appointmentGroupComboBox, 1, 1);
        
        getDialogPane().setContent(grid);

        Platform.runLater(() -> summaryTextField.requestFocus()); // Request focus on the summary field by default.
        
        setResultConverter(dialogButton -> dialogButton.getButtonData());
    }
}
