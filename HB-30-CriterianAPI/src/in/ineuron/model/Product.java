package in.ineuron.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private Integer pid;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", pquantity=" + pquantity + "]";
	}



}
