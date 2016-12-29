package aosivt.client;


import aosivt.client.UI.TablePac.TableDataBankList;
import aosivt.shared.FieldValidator;
import aosivt.shared.ReferencesClientServer.BankListRef;
import aosivt.shared.ReferencesClientServer.OptionRequest;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.DOM;
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


  final Button confirmButton = new Button("Поиск");
  final TextBox nameField = new TextBox();
  final TextBox optionTimer = new TextBox();

  final Label errorLabel = new Label();
  final Label helloLabel = new Label();
  final CellTable<BankListRef> cellTableBank = new TableDataBankList<BankListRef>();

  VerticalPanel dialogVPanel = new VerticalPanel();
  final DialogBox dialogBox = new DialogBox();
  final HTML serverResponseHtml = new HTML();
  final Label sendToServerLabel = new Label();
  final Button closeButton = new Button("Close");
  private final GwtAppServiceIntfAsync gwtAppServiceImpl = GWT.create(GwtAppServiceIntf.class);


  @Override
  public void onModuleLoad() {
    helloLabel.setText("Поиск расположение и рабочее время банка по его наименованию");

    RootPanel.get("helloId").add(helloLabel);

    RootPanel.get("searchValueId").add(nameField);
    nameField.getElement().setAttribute("class","mdl-textfield__input");
    nameField.getElement().setAttribute("id","searchValueIdDesign");


    RootPanel.get("timerOptionId").add(optionTimer);
    optionTimer.getElement().setAttribute("class","mdl-textfield__input");
    optionTimer.getElement().setAttribute("id","timerOptionIdDesign");


    RootPanel.get("confirmButtonId").add(confirmButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

//    RootPanel.get("resultTable").add(cellTableBank);

     confirmButton.getElement().setAttribute("class",
            "mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent");
    confirmButton.setText("Поиск");

    confirmButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {

        sendInfoToServer();
      }
    });



    nameField.addKeyUpHandler(new KeyUpHandler() {
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendInfoToServer();
        }
      }
    });

      }

  private void sendInfoToServer() {

    optionTimer.setText(((InputElement)(Element) DOM.getElementById("timerOptionId")).getValue());
    nameField.setText(((InputElement)(Element) DOM.getElementById("searchValueId")).getValue());


    errorLabel.setText("");

    //validate input text
    if (!FieldValidator.isValidData(optionTimer.getText())) {

      errorLabel.setText("Укажите целое чилсло" );
      return;
    }
    else if (FieldValidator.isValidMinMax(optionTimer.getText()))
    {

      errorLabel.setText("Целое число должно быть в диапозоне от 10 до 100 минут");
      return;
    }


    OptionRequest optionRequest = new OptionRequest();
    optionRequest.setNameBank(nameField.getText());
    optionRequest.setEnterMinutWorkTimer(Integer.parseInt(optionTimer.getText()));

    confirmButton.setEnabled(false);


    gwtAppServiceImpl.gwtAppCallServer(optionRequest, new AsyncCallback<List<BankListRef>>() {
      @Override
      public void onFailure(Throwable throwable) {
        errorLabel.setText("Ошибка соедения с сервером: " + throwable.getLocalizedMessage());
      }

      @Override
      public void onSuccess(List<BankListRef> bankListRefs) {

        RootPanel.get("resultTable").clear();
        RootPanel.get("resultTable").setWidth("500");
        RootPanel.get("resultTable").add(new TableDataBankList(bankListRefs));

        confirmButton.setEnabled(true);
      }

    });
    }

}
