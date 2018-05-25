package BL.BL_W.Entities_W;

public class BankAccount {

    private String bankCode;
    private String accountNumber;
    private String branchNumber;

    public BankAccount(String bankCode, String accountNum, String branchNum) {
        this.bankCode = bankCode;
        this.accountNumber = accountNum;
        this.branchNumber = branchNum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankAccount))
            return false;
        BankAccount bankAccount = (BankAccount) obj;
        return (this.bankCode.equals(bankAccount.bankCode) && this.accountNumber.equals(bankAccount.accountNumber) &&
        this.branchNumber.equals(bankAccount.branchNumber));
    }

    @Override
    public String toString() {
        return "bankCode="+bankCode+"\t"+"accountNumber="+accountNumber+"\t"+"branchNumber="+branchNumber;
    }
}
