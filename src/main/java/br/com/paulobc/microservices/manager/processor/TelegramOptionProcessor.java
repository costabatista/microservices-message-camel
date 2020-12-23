package br.com.paulobc.microservices.manager.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

public class TelegramOptionProcessor implements Processor {
    private String headerMessage;
    private String installSoftwareOptionMessage;
    private String updateContractOptionMessage;
    private String sendWarningOptionMessage;
    private String getContratcOptionMessage;
    private String softwareOptionHeaderMessage;

    public TelegramOptionProcessor() {
        this.loadButtonsTexts();
    }

    public String getSoftwareOptionHeaderMessage() {
        return softwareOptionHeaderMessage;
    }

    public void setSoftwareOptionHeaderMessage(String softwareOptionHeaderMessage) {
        this.softwareOptionHeaderMessage = softwareOptionHeaderMessage;
    }

    private void loadButtonsTexts() {
        String headerMessageText = "Escolha uma opção de atendimento";
        String installSoftwareMessageText = "Instalação de software";
        String updateContractMessageText = "Atualização de contrato";
        String getContractMessageText = "Obtenção de cópia de contrato";
        String sendWarningMessageText = "Enviar aviso em broadcast";
        String softwareOptionHeaderMessage = "Escolha um software para Instação";

        this.setHeaderMessage(headerMessageText);
        this.setInstallSoftwareOptionMessage(installSoftwareMessageText);
        this.setUpdateContractOptionMessage(updateContractMessageText);
        this.setGetContratcOptionMessage(getContractMessageText);
        this.setSendWarningOptionMessage(sendWarningMessageText);
        this.setSoftwareOptionHeaderMessage(softwareOptionHeaderMessage);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        int optionType = TelegramOption.SELECT_SERVICE_TYPE.getType();
        OutgoingTextMessage message = new OutgoingTextMessage();
        ReplyKeyboardMarkup reply = null;
        boolean installRequest = false;
        String positiveInstall = "O software será instalado! Por segurança, o trâmite de atendimento foi enviado por e-mail e publicado no twitter";
        String negativeInstall = "O software não pode ser instalado, pois não está incluso em contrato";
        String telegramResponseMessage = null;
        message.setDisableNotification(false);

        String messageStr = (String) exchange.getMessage().getBody(String.class);
    
    

        optionType = this.getMessageType(messageStr);
        switch (optionType) {
            case 1:
                reply = this.getFirstReply(message);
                break;
            case 2:
                reply = this.getSecondReply(message);
                break;
            case 6: // software será instalado
                installRequest = this.verifyMockInstallSoftwarePermission(messageStr);
                if (installRequest == true)
                    telegramResponseMessage = positiveInstall;
                else
                    telegramResponseMessage = negativeInstall;
                exchange.getMessage().setBody(telegramResponseMessage);
                break;
            default:

                break;

        }
        if (reply != null) {
            message.setReplyMarkup(reply);
            exchange.getMessage().setBody(message);
        }

    }

    private ReplyKeyboardMarkup getFirstReply(OutgoingTextMessage message) {
        ReplyKeyboardMarkup reply;
        message.setText(this.getHeaderMessage());

        InlineKeyboardButton buttonOne = InlineKeyboardButton.builder().text(this.getInstallSoftwareOptionMessage())
                .build();

        InlineKeyboardButton buttonTwo = InlineKeyboardButton.builder().text(this.getUpdateContractOptionMessage())
                .build();

        InlineKeyboardButton buttonThree = InlineKeyboardButton.builder().text(this.getSendWarningOptionMessage())
                .build();

        InlineKeyboardButton buttonFour = InlineKeyboardButton.builder().text(this.getGetContratcOptionMessage())
                .build();

        reply = ReplyKeyboardMarkup.builder().keyboard().addRow(Arrays.asList(buttonOne))
                .addRow(Arrays.asList(buttonTwo)).addRow(Arrays.asList(buttonThree)).addRow(Arrays.asList(buttonFour))
                .close().oneTimeKeyboard(true).build();
        return reply;
    }

    private ReplyKeyboardMarkup getSecondReply(OutgoingTextMessage message) {
        ReplyKeyboardMarkup reply;
        List<String> mockSoftwareList = this.getMockSoftwareList();
        message.setText(this.getSoftwareOptionHeaderMessage());
        InlineKeyboardButton buttonSoftwareOne = InlineKeyboardButton.builder().text(mockSoftwareList.get(0)).build();
        InlineKeyboardButton buttonSoftwareTwo = InlineKeyboardButton.builder().text(mockSoftwareList.get(1)).build();
        InlineKeyboardButton buttonSoftwareThree = InlineKeyboardButton.builder().text(mockSoftwareList.get(2)).build();

        reply = ReplyKeyboardMarkup.builder().keyboard().addRow(Arrays.asList(buttonSoftwareOne, buttonSoftwareTwo))
                .addRow(Arrays.asList(buttonSoftwareThree)).close().oneTimeKeyboard(true).build();
        return reply;
    }

    private boolean verifyMockInstallSoftwarePermission(String message) {
        if (message.trim().equals("Adobe Photoshop") || message.trim().equals("Office 365")) {
            System.out.println("caiu aqui essa merda");
            return false;
        }
            
        else
            return true;
    }

    private int getMessageType(String message) {
        List<String> mockSoftwareList = this.getMockSoftwareList();

        if (message.equals(this.getInstallSoftwareOptionMessage())) {
            return TelegramOption.INSTALL_SOFTWARE_TYPE.getType();
        } else if (message.equals(this.getUpdateContractOptionMessage())) {
            return TelegramOption.UPDATE_CONTRACT_TYPE.getType();
        } else if (message.equals(this.getSendWarningOptionMessage())) {
            return TelegramOption.SEND_WARNING_TYPE.getType();
        } else if (message.equals(this.getGetContratcOptionMessage())) {
            return TelegramOption.GET_CONTRACT_TYPE.getType();
        } else if (mockSoftwareList.contains(message)) {
            return TelegramOption.SOFTWARE_TO_INSTALL_TYPE.getType();
        }

        return 1;
    }

    public String getGetContratcOptionMessage() {
        return this.getContratcOptionMessage;
    }

    public void setGetContratcOptionMessage(String getContratcOptionMessage) {
        this.getContratcOptionMessage = getContratcOptionMessage;
    }

    public String getSendWarningOptionMessage() {
        return this.sendWarningOptionMessage;
    }

    public void setSendWarningOptionMessage(String sendWarningOptionMessage) {
        this.sendWarningOptionMessage = sendWarningOptionMessage;
    }

    public String getUpdateContractOptionMessage() {
        return this.updateContractOptionMessage;
    }

    public void setUpdateContractOptionMessage(String updateContractOptionMessage) {
        this.updateContractOptionMessage = updateContractOptionMessage;
    }

    public String getInstallSoftwareOptionMessage() {
        return this.installSoftwareOptionMessage;
    }

    public void setInstallSoftwareOptionMessage(String installSoftwareOptionMessage) {
        this.installSoftwareOptionMessage = installSoftwareOptionMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public String getHeaderMessage() {
        return this.headerMessage;
    }

    private List<String> getMockSoftwareList() {
        List<String> softwareList = new ArrayList<>();
        softwareList.add("Adobe Photoshop");
        softwareList.add("GIMP");
        softwareList.add("Office 365");

        return softwareList;
    }

}
