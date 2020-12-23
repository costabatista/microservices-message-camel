package br.com.paulobc.microservices.manager.processor;

public enum TelegramOption {
    SELECT_SERVICE_TYPE(1), INSTALL_SOFTWARE_TYPE(2), UPDATE_CONTRACT_TYPE(3), SEND_WARNING_TYPE(4),
    GET_CONTRACT_TYPE(5), SOFTWARE_TO_INSTALL_TYPE(6);

    private int type;

    private TelegramOption(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
