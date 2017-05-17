package persistencedemo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageClickInfo {

	Long id;
	
	String permanentId;
	
	Integer custId;
	
	String currentPage;
	
	Integer eventId;
	
	String nextPage;
	
	Integer productId;
	
	String module;
	
	String pit;
	
	String frame;
	
	Date actionDate;
	
	Integer fromPlatform;
}
