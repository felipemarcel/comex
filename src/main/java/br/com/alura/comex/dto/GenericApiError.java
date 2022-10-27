package br.com.alura.comex.dto;

public class GenericApiError {

    private String campo;
    private String erro;
    private String message;

    private GenericApiError(Builder builder) {
        setCampo(builder.campo);
        setErro(builder.erro);
        setMessage(builder.message);
    }

    public static Builder Builder() {
        return new Builder();
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public static final class Builder {
        private String campo;
        private String erro;
        private String message;

        private Builder() {
        }

        public Builder campo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder erro(String erro) {
            this.erro = erro;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public GenericApiError build() {
            return new GenericApiError(this);
        }
    }
}
