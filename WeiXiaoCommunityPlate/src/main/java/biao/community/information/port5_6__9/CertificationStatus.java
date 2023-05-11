package biao.community.information.port5_6__9;

public class CertificationStatus {
    //状态码
    String auditStatus;

    //状态说明
    String statements;

    public String getAuditStatus() {
        return auditStatus;
    }

    public String getStatements() {
        return statements;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public void setStatements(String statements) {
        this.statements = statements;
    }
}
