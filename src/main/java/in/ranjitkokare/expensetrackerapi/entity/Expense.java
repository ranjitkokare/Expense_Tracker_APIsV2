package in.ranjitkokare.expensetrackerapi.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
@Builder
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String expenseId;
	
	@Column(name = "expense_name")
	private String name;
	
	private String description;
	
	@Column(name = "expense_amount")
	private BigDecimal amount;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id",nullable = false)//many expense are mapped to a single category
	@OnDelete(action = OnDeleteAction.RESTRICT) //throw exception that some of the expenses are mapped to the category
	//first delete that expenses and then you can delete the category
	private CategoryEntity category;
	
	private Date date;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp//Hibernate Annotaion
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id",nullable = false)//many expense are mapped to a single user
	@OnDelete(action = OnDeleteAction.CASCADE) //on delete of user all expenses should be deleted
	@JsonIgnore //whenever we fetch expense we ignore user we hide user
	private User user;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
