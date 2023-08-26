package in.ineuron.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class JobSeeker implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer jsId;
	private String jsName;
	private String jsAddress;
	
	@Lob
	private byte[] jsPhoto;
	
	@Lob
	private char[] jsResume;

	public Integer getJsId() {
		return jsId;
	}

	public void setJsId(Integer jsId) {
		this.jsId = jsId;
	}

	public String getJsName() {
		return jsName;
	}

	public void setJsName(String jsName) {
		this.jsName = jsName;
	}

	public String getJsAddress() {
		return jsAddress;
	}

	public void setJsAddress(String jsAddress) {
		this.jsAddress = jsAddress;
	}

	public byte[] getJsPhoto() {
		return jsPhoto;
	}

	public void setJsPhoto(byte[] jsPhoto) {
		this.jsPhoto = jsPhoto;
	}

	public char[] getJsResume() {
		return jsResume;
	}

	public void setJsResume(char[] jsResume) {
		this.jsResume = jsResume;
	}

	@Override
	public String toString() {
		return "JobSeeker [jsId=" + jsId + ", jsName=" + jsName + ", jsAddress=" + jsAddress + ", jsPhoto="
				+ Arrays.toString(jsPhoto) + ", jsResume=" + Arrays.toString(jsResume) + "]";
	}
	
    
}
