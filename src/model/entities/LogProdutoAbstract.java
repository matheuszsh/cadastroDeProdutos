package model.entities;

import java.util.Date;

public abstract class LogProdutoAbstract {
    protected Date dataProduto;
    protected Integer idLog;
    protected String codigoId;

    public LogProdutoAbstract() {
        this.dataProduto = new Date();
        this.idLog = null;
        this.codigoId = codigoId;
    }

    public Date getDataProduto() {
        return dataProduto;
    }

    public int getIdLog() {
        return idLog;
    }

    public String getCodigoId() {
        return codigoId;
    }
}
