package in.ranjitkokare.expensetrackerapi.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseDTO {
	
	private String expenseId;
	private String name;
	private String description;
	private BigDecimal amount;
	private Date date;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String categoryId;
	private CategoryDTO categoryDTO;
	private UserDTO userDTO;
	
}
