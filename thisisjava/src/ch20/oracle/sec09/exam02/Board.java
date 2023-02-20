package ch20.oracle.sec09.exam02;

import java.sql.Blob;
import java.util.Date;
import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String bContent;
	private String bWriter;
	private Date bdate;
	private String bfilename;
	private Blob bfileData;
}
