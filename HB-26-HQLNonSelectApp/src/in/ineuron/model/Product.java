package in.ineuron.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name="Product")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pid;
	
	@Column(length = 20)
	private String pname;
	private Integer pprice;
	private Integer pquantity;
	public Integer getPid() {
		return pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPprice() {
		return pprice;
	}
	public void setPprice(Integer pprice) {
		this.pprice = pprice;
	}
	public Integer getPquantity() {
		return pquantity;
	}
	public void setPquantity(Integer pquantity) {
		this.pquantity = pquantity;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", pquantity=" + pquantity + "]";
	}
	
	
    
}
