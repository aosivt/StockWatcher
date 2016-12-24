package aosivt.client;


import aosivt.shared.FieldValidator;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
//import com.vaadin.polymer.paper.widget.PaperButton;
//import com.vaadin.polymer.paper.widget.PaperToast;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialToast;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcherEntryPoint implements EntryPoint {

  @UiField
  MaterialButton btnClick, btnHover, btnDoubleClick;

//  @UiField
//  PaperButton testbutton = new PaperButton();
//  @UiField
//  PaperToast toast1 = new PaperToast("Ну Ебано ВРОТ)))");

  final Button confirmButton = new Button("Confirm");
  final TextBox nameField = new TextBox();
  final Label errorLabel = new Label();
  final Label helloLabel = new Label();

  VerticalPanel dialogVPanel = new VerticalPanel();
  final DialogBox dialogBox = new DialogBox();
  final HTML serverResponseHtml = new HTML();
  final Label sendToServerLabel = new Label();
  final Button closeButton = new Button("Close");
  private final GwtAppServiceIntfAsync gwtAppServiceImpl = GWT.create(GwtAppServiceIntf.class);
  @Override
  public void onModuleLoad() {
    helloLabel.setText("GwtApp Application hello world");

    final Label usernameLabel = new Label();
    usernameLabel.setText("Username: ");
        /*Связываем id='' на html странице с компонентами */
    RootPanel.get("helloId").add(helloLabel);

    RootPanel.get("usernameLabelId").add(usernameLabel);
    RootPanel.get("usernameId").add(nameField);

    RootPanel.get("confirmButtonId").add(confirmButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

//    testbutton.setName("Polymer");
//    testbutton.setRaised(true);
//
//    testbutton.addClickHandler(new ClickHandler() {
//      public void onClick(ClickEvent event) {
//toast1.open();
//      }
//    });
//    RootPanel.get().add(testbutton);
    // Create the popup dialog box
    dialogBox.setText("Remote procedure call from server");
    dialogBox.setAnimationEnabled(true);

    closeButton.getElement().setId("closeButtonId");

    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Отправленные поля на сервер:</b>"));
    dialogVPanel.add(sendToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Ответ сервера:</b>"));
    dialogVPanel.add(serverResponseHtml);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    //обработчик для клика по кнопке 'Confirm'
    confirmButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        confirmButton.setEnabled(false);
        sendInfoToServer();
      }
    });

    //обработчик по нажатию enter в текстовом поле
    nameField.addKeyUpHandler(new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent event) {
        if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendInfoToServer();
        }
      }
    });
    //обработчик по клику на кнопку 'Close' в диалоговом окне
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        confirmButton.setEnabled(true);
        confirmButton.setFocus(true);
      }
    });
  }

  private void sendInfoToServer() {
    //validate input text
    errorLabel.setText("");
    String nameToServer = nameField.getText();
    if (!FieldValidator.isValidData(nameToServer)) { //отобразить ошибку на html странице
      errorLabel.setText("Имя должно содержать больше трех символов111");
      return;
    }
    sendToServerLabel.setText(nameToServer);
    confirmButton.setEnabled(false);
    serverResponseHtml.setText("");
    gwtAppServiceImpl.gwtAppCallServer(nameToServer, new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {

        dialogBox.setText("Remote Procedure Call - Failure");
        serverResponseHtml.addStyleName("serverResponseLabelError");
        serverResponseHtml.setHTML(caught.getMessage());
        dialogBox.center();
        closeButton.setFocus(true);
      }
      public void onSuccess(String result) {
        dialogBox.setText("Remote Procedure Call");
        serverResponseHtml.removeStyleName("serverResponseLabelError");
        serverResponseHtml.setHTML(result);
        dialogBox.center();
        closeButton.setFocus(true);
      }
    });

  }

}
