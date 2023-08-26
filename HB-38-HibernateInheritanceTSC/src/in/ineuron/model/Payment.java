package in.ineuron.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "PAYMENT_TBSC")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "payment_mode",discriminatorType = DiscriminatorType.STRING)
public abstract class Payment implements Serializable{

	@Id
	private Integer pid;
	private float ammount;
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public float getammount() {
		return ammount;
	}
	public void setammount(float f) {
		this.ammount = f;
	}
	
	@Override
	public String toString() {
		return "Payment [pid=" + pid + ", ammount=" + ammount + "]";
	}
	
	
}
