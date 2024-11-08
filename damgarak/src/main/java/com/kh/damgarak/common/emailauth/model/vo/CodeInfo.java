package com.kh.damgarak.common.emailauth.model.vo;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Data는 기본생성자만 제공을 한다. 
public class CodeInfo {
	
	private String code;
	private LocalDateTime createTime;
	
}
