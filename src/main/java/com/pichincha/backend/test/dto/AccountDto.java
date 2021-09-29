package com.pichincha.backend.test.dto;

import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Builder
public class AccountDto {

	private final String number;

	private final String type;

	private final LocalDateTime creationDate;

}
