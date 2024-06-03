package model.entities;

import java.util.Date;

public abstract class LogProdutoTest {
    private Date dataProduto;
    private int idLog;
    private String codigoId;

    public Date getDataProduto() {
        return dataProduto;
    }

    public int getIdLog() {
        return idLog;
    }

    public String getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(String codigoId) {
        this.codigoId = codigoId;
    }
}
