package in.ineuron.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="CHEQUE_PAYMENT_TBSC")
@DiscriminatorValue("CHEQUE")
@PrimaryKeyJoinColumn(name = "payment_id",referencedColumnName = "pid")
public class ChequePayment extends Payment implements Serializable {

	private Long chequeNo;
	private String chequeType;
	private LocalDate expiryDate;
	
	public Long getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(Long chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeType() {
		return chequeType;
	}
	public void setChequeType(String chequeType) {
		this.chequeType = chequeType;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public String toString() {
		return "ChequePayment [chequeNo=" + chequeNo + ", chequeType=" + chequeType + ", expiryDate=" + expiryDate
				+ "]";
	}
}
