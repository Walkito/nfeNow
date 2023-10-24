package br.com.nfenow.geradorNFe.model;

public class ErrorResponse {
    private int status;
    private String mensagem;
    private String detalhes;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String mensagem, String detalhes) {
        this.status = status;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
