package edu.muniz.askalien.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound
import java.lang.Boolean
import java.util.*

@SpringBootApplication
class AdminApplication

fun main(args: Array<String>) {

	if (Objects.nonNull(System.getenv("BLOCKHOUND")) && Boolean.valueOf(System.getenv("BLOCKHOUND"))) {
		BlockHound.install()
	}

	runApplication<AdminApplication>(*args)
}
