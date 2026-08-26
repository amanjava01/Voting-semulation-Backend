package com.voting_semulation;

import org.springframework.boot.SpringApplication;

public class TestVotingSemulationApplication {

	public static void main(String[] args) {
		SpringApplication.from(VotingSemulationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
