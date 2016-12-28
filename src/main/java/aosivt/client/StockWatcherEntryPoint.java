package aosivt.client;


import aosivt.client.UI.TablePac.TableDataBankList;
import aosivt.shared.FieldValidator;
import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.shared.ReferencesClientServer.OptionRequest;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;

//import com.vaadin.polymer.paper.widget.PaperButton;
//import com.vaadin.polymer.paper.widget.PaperToast;
//import gwt.material.design.client.ui.MaterialToast;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockWatcherEntryPoint implements EntryPoint {

//  @UiField
//  MaterialButton btnClick, btnHover, btnDoubleClick;

//  @UiField
//  PaperButton testbutton = new PaperButton();
//  @UiField
//  PaperToast toast1 = new PaperToast("Ну Ебано ВРОТ)))");

  final Button confirmButton = new Button("Поиск");
  final TextBox nameField = new TextBox();
  final TextBox optionTimer = new TextBox();
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
    helloLabel.setText("Поиск расположение и рабочее время банка по его наименованию");

        /*Связываем id='' на html странице с компонентами */
    RootPanel.get("helloId").add(helloLabel);

//    RootPanel.get("usernameLabelId").add(usernameLabel);

    RootPanel.get("searchValueId").add(nameField);
    RootPanel.get("timerOptionId").add(optionTimer);

    RootPanel.get("confirmButtonId").add(confirmButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

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
//        confirmButton.setEnabled(false);
        sendInfoToServer();
      }
    });

    //обработчик по нажатию enter в текстовом поле
    nameField.addKeyUpHandler(new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
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
    OptionRequest optionRequest = new OptionRequest();

    optionRequest.setNameBank(nameField.getText());
    optionRequest.setEnterMinutWorkTimer(Integer.parseInt(optionTimer.getText()));

    if (!FieldValidator.isValidData(optionTimer.getText())) {
      errorLabel.setText("Укажите целое чилсло");
      return;
    }
    else if (!FieldValidator.isValidMinMax(optionTimer.getText()))
    {
      errorLabel.setText("Целое число должно быть в диапозоне от 10 до 100 минут");
      return;
    }
//    sendToServerLabel.setText(nameToServer);
    confirmButton.setEnabled(false);
//    serverResponseHtml.setText("");

    gwtAppServiceImpl.gwtAppCallServer(optionRequest, new AsyncCallback<List<BankListRef>>() {
      @Override
      public void onFailure(Throwable throwable) {
        dialogBox.setText("Remote Procedure Call - Failure");
        serverResponseHtml.addStyleName("serverResponseLabelError");
        serverResponseHtml.setHTML(throwable.getMessage());
        dialogBox.center();
        closeButton.setFocus(true);
      }

      @Override
      public void onSuccess(List<BankListRef> bankListRefs) {
        CellTable<BankListRef> cellTableBank
                = new TableDataBankList(bankListRefs);

        RootPanel.get().add(cellTableBank);


//        dialogBox.setText("Remote Procedure Call");
//        serverResponseHtml.removeStyleName("serverResponseLabelError");
//        serverResponseHtml.setHTML(bankListRefs.get(0).getAddress());
//        dialogBox.center();
        confirmButton.setEnabled(true);
//        closeButton.setFocus(true);

      }


    });

  }
}
