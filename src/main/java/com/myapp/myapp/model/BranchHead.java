package com.myapp.myapp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class BranchHead extends User {

	@OneToOne
	BranchHeadPolicy policy;
}
